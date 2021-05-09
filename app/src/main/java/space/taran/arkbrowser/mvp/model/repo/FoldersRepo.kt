package space.taran.arkbrowser.mvp.model.repo

import space.taran.arkbrowser.mvp.model.entity.room.FolderDao
import space.taran.arkbrowser.utils.PartialResult
import space.taran.arkbrowser.utils.folderExists
import java.nio.file.Path
import java.nio.file.Paths

import space.taran.arkbrowser.utils.fail
import space.taran.arkbrowser.utils.ok
import java.lang.AssertionError

typealias Folders = Map<Path, List<Path>>

class FoldersRepo(private val dao: FolderDao) {

    //todo: upon writing, canonicalize paths and maybe check as well?

    suspend fun query(): PartialResult<Folders, List<Path>> {
        val missingPaths = mutableListOf<Path>()

        val validPaths = dao.query()
            .flatMap {
                val root = Paths.get(it.root.path)

                if (!folderExists(root)) {
                    missingPaths.add(root)
                    fail()
                } else {
                    val favorites = it.favorites.flatMap { favorite ->
                        if (favorite.root != it.root.path) {
                            throw AssertionError("foreign key violation")
                        }

                        val folder = root.resolve(favorite.relative)

                        if (!folderExists(folder)) {
                            missingPaths.add(folder)
                            fail()
                        } else {
                            ok(Paths.get(favorite.relative))
                        }
                    }

                    ok(root to favorites)
                }
            }

        return PartialResult(
            validPaths.toMap(),
            missingPaths.toList())
    }
}