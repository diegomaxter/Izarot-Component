package com.example.nuevo

import android.annotation.SuppressLint
import android.content.Context
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

    init {
        val inflater = LayoutInflater.from(context)
        binding = ItemDeviceBinding.inflate(inflater, this, true)
        postInitialization(attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    fun postInitialization(attrs: AttributeSet?) {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ComponentItem)
        attributes.apply {
            binding.title.text = getString(R.styleable.ComponentItem_title)
            binding.startIcon.setImageResource(
                getResourceId(
                    R.styleable.ComponentItem_start_icon,
                    0
                )
            )
            binding.endIcon.setImageResource(getResourceId(R.styleable.ComponentItem_end_icon, 0))
            binding.subTitle.text = getString(R.styleable.ComponentItem_sub_title)
            binding.secondSubTitle.text = getString(R.styleable.ComponentItem_second_sub_title)
            setupItemStyle(ItemStyle.from(getInt(R.styleable.ComponentItem_item_style, 0)))
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
}
