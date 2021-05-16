package space.taran.arkbrowser.mvp.view

import android.net.Uri
import space.taran.arkbrowser.mvp.model.entity.common.TagState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import space.taran.arkbrowser.utils.SortBy

@StateStrategyType(AddToEndSingleStrategy::class)
interface ResourcesView: MvpView {
    fun init()

    fun updateAdapter()

    @StateStrategyType(SkipStrategy::class)
    fun setTags(tags: List<TagState>)

    @StateStrategyType(SkipStrategy::class)
    fun clearTags()

    @StateStrategyType(SkipStrategy::class)
    fun openFile(uri: Uri, mimeType: String)

    fun setToolbarTitle(title: String)

    fun showSortByDialog(sortBy: SortBy, isReversedSort: Boolean)
    fun closeSortByDialog()

    fun setTagsLayoutVisibility(isVisible: Boolean)
}

//todo: what are these StateStrategyType and SkipStrategy and why are they necessary?