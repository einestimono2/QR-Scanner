package com.example.tranning_qr_scanner.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class RecyclerViewItemSpacing(
    private val xSpace: Int,
    private val ySpace: Int
) : ItemDecoration() {
    constructor(space: Int) : this(space, space)

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.apply {
            bottom = ySpace

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) % 2 == 0) {
                left = xSpace
                right = xSpace
            } else {
                right = xSpace
            }
        }
    }
}