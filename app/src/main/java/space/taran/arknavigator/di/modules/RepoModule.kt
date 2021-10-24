package space.taran.arknavigator.di.modules

import android.util.Log
import space.taran.arknavigator.mvp.model.dao.Database
import dagger.Module
import dagger.Provides
import space.taran.arknavigator.mvp.model.repo.FoldersRepo
import space.taran.arknavigator.mvp.model.repo.PreviewsRepo
import space.taran.arknavigator.mvp.model.repo.ResourcesIndexFactory
import space.taran.arknavigator.utils.MAIN
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun foldersRepo(database: Database): FoldersRepo {
        Log.d(MAIN, "creating FoldersRepo")
        return FoldersRepo(database.folderDao())
    }

    @Singleton
    @Provides
    fun resourcesIndexesRepo(database: Database, previewsRepo: PreviewsRepo): ResourcesIndexFactory {
        Log.d(MAIN, "creating ResourcesIndexesRepo")
        return ResourcesIndexFactory(database.resourceDao(), previewsRepo)
    }

    @Singleton
    @Provides
    fun previewRepo(): PreviewsRepo {
        Log.d(MAIN, "creating PreviewRepo")
        return PreviewsRepo()
    }
}