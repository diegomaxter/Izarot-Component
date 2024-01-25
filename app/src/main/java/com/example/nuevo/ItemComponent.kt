package com.example.nuevo

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.example.nuevo.databinding.ItemDeviceBinding

class ItemComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: ItemDeviceBinding
    private var backgroundItem = BackgroundItem(
        color = resources.getColor(R.color.lightGray, null),
        corners = 0f
    )

    init {
        val inflater = LayoutInflater.from(context)
        binding = ItemDeviceBinding.inflate(inflater, this, true)
        postInitialization(attrs)
    }


    private fun postInitialization(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemComponent)
        attributes.apply {
            binding.title.text = getString(R.styleable.ItemComponent_title)
            binding.startIcon.setImageResource(
                getResourceId(
                    R.styleable.ItemComponent_start_icon,
                    0
                )

            )
            binding.endIcon.setImageResource(getResourceId(R.styleable.ItemComponent_end_icon, 0))
            binding.subTitle.text = getString(R.styleable.ItemComponent_sub_title)
            binding.secondSubTitle.text = getString(R.styleable.ItemComponent_second_sub_title)
            setupItemStyle(ItemStyle.from(getInt(R.styleable.ItemComponent_item_style, 0)))
            setBackgroundComplete(
                BackgroundItem(
                    color = getColor(
                        R.styleable.ItemComponent_background_color,
                        resources.getColor(R.color.lightGray, null)
                    ),
                    corners = getDimension(R.styleable.ItemComponent_corners, 0f)
                )
            )
        }

        checkImages()
        attributes.recycle()
    }

    private fun setupItemStyle(itemStyle: ItemStyle?) {
        if (itemStyle == null) binding.root.setBackgroundResource(R.drawable.default_background)
        else {
            binding.root.setBackgroundResource(itemStyle.backgroundDrawable)
        }
    }

    private fun setBackgroundComplete(backgroundItem: BackgroundItem) {
        val drawable = GradientDrawable()
        drawable.cornerRadius = backgroundItem.corners
        drawable.setColor(backgroundItem.color)
        binding.backgroundView.background = drawable
    }

    private fun checkImages() {
        binding.startIcon.isVisible = binding.startIcon.drawable != null
        binding.endIcon.isVisible = binding.endIcon.drawable != null
    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun subTitle(subtitle: String) {
        binding.subTitle.text = subtitle
    }

    fun secondSubTitle(secondSubtitle: String) {
        binding.secondSubTitle.text = secondSubtitle
    }

    fun setItemStyle(itemStyle: ItemStyle) {
        setupItemStyle(itemStyle)
    }

    fun setStartIcon(@DrawableRes image: Int) {
        binding.startIcon.setImageResource(image)
        binding.startIcon.isVisible = true
    }

    fun setEndIcon(@DrawableRes image: Int) {
        binding.endIcon.setImageResource(image)
        binding.endIcon.isVisible = true
    }

    override fun setBackgroundColor(color: Int) {
        backgroundItem.color = color
        setBackgroundComplete(backgroundItem)
    }

    fun setCornerRadius(cornerRadius: Float) {
        backgroundItem.corners = cornerRadius
        setBackgroundComplete(backgroundItem)
    }

    data class BackgroundItem(
        var color: Int,
        var corners: Float,
    )
}
