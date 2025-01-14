package space.taran.arknavigator.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.nio.file.Path

@StateStrategyType(AddToEndSingleStrategy::class)
interface ResourcesView : MvpView {
    fun init()
    fun updateAdapter()
    fun setProgressVisibility(isVisible: Boolean, withText: String = "")
    fun setToolbarTitle(title: String)
    fun setKindTagsEnabled(enabled: Boolean)
    fun updateMenu()
    fun setTagsFilterEnabled(enabled: Boolean)
    fun setTagsFilterText(filter: String)
    fun drawTags()

    @StateStrategyType(SkipStrategy::class)
    fun toastResourcesSelected(selected: Int)
    @StateStrategyType(SkipStrategy::class)
    fun toastResourcesSelectedFocusMode(selected: Int, hidden: Int)
    @StateStrategyType(SkipStrategy::class)
    fun toastPathsFailed(failedPaths: List<Path>)
}
