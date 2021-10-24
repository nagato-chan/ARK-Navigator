package space.taran.arknavigator.ui.fragments.utils

import java.nio.file.Path

data class Preview(
    val isFolder: Boolean? = null,
    val filePath: Path? = null,
    val fileExtension: String? = null
) {
    enum class ExtraInfoTag {
        MEDIA_RESOLUTION, MEDIA_DURATION
    }
}