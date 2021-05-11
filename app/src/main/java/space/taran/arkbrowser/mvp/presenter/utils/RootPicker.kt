package space.taran.arkbrowser.mvp.presenter.utils

import android.view.View
import kotlinx.android.synthetic.main.dialog_roots_new.view.*
import space.taran.arkbrowser.mvp.model.entity.common.Icon
import space.taran.arkbrowser.mvp.model.entity.common.IconOrImage
import space.taran.arkbrowser.mvp.presenter.adapter.ReversibleItemGridPresenter
import space.taran.arkbrowser.mvp.view.item.FileItemView
import space.taran.arkbrowser.ui.adapter.ItemGridRVAdapter
import space.taran.arkbrowser.utils.ROOT_PATH
import space.taran.arkbrowser.utils.findLongestCommonPrefix
import java.nio.file.Files
import java.nio.file.Path

import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.listDirectoryEntries

typealias PathHandler = (Path) -> Unit

@OptIn(ExperimentalPathApi::class)
class RootPicker(
    paths: List<Path>,
    handler: PathHandler,
    private val view: View)
        : ItemGridRVAdapter<Path, Path>(InnerRootPicker(paths, handler)) {

    init {
        view.rv_roots_dialog.adapter = this
        view.tv_roots_dialog_path.text = super.getLabel().toString()
    }

    override fun backClicked(): Path? {
        val label = super.backClicked()
        if (label != null) {
            view.tv_roots_dialog_path.text = label.toString()
        }
        return label
    }

    fun updatePath(path: Path) {
        val children = path.listDirectoryEntries()
        this.updateItems(path, children)

        view.tv_roots_dialog_path.text = path.toString()
    }
}

class InnerRootPicker(paths: List<Path>, onClick: (Path) -> Unit):
    ReversibleItemGridPresenter<Path, Path>(
        findLongestCommonPrefix(paths).first,
        paths, onClick) {

    override fun bindView(view: FileItemView) {
        val path = items()[view.pos]
        view.setText(path.fileName.toString())

        if (Files.isDirectory(path)) {
            view.setIcon(IconOrImage(icon = Icon.FOLDER))
        } else {
            view.setIcon(IconOrImage(icon = Icon.FILE))
            //todo
//                if (path.isImage())
//                    view.setIcon(Icon.IMAGE, path.file)
//                else
//                    view.setIcon(Icon.FILE, path.file)
        }
    }
}