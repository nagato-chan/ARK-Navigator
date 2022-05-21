package space.taran.arknavigator.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding

class DefaultPopup(val binding: ViewBinding, @StyleRes val styleId: Int) {
    val popupWindow: PopupWindow

    init {
        binding.root.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        popupWindow = PopupWindow(binding.root.context)
        popupWindow.apply {
            contentView = binding.root
            width = LinearLayout.LayoutParams.WRAP_CONTENT
            height =
                View.MeasureSpec.makeMeasureSpec(
                    binding.root.measuredHeight,
                    View.MeasureSpec.UNSPECIFIED
                )
            isFocusable = true
            animationStyle = styleId
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun showAbove(targetView: View) {
        val xOffset = (targetView.width - binding.root.measuredWidth) / 2
        val yOffset = (-0.2 * targetView.height).toInt()
        popupWindow.showAsDropDown(
            targetView,
            xOffset,
            yOffset
        )
    }

    fun showBelow(targetView: View) {
        val xOffset = (targetView.width - binding.root.measuredWidth) / 2
        popupWindow.showAsDropDown(
            targetView,
            xOffset,
            0
        )
    }
}
