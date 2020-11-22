package com.mgabor.coroutine.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["adapter", "layoutManager", "data"], requireAll = false)
@Suppress("UNCHECKED_CAST")
fun <T> RecyclerView.setRecyclerViewProperties(
    adapter: ListAdapter<T, *>?,
    layoutManager: RecyclerView.LayoutManager?,
    data: List<T>?
) {
    if (adapter != null) {
        this.adapter = adapter
    }
    if (layoutManager != null) {
        this.layoutManager = layoutManager
        if (data != null) {
            (this.adapter as ListAdapter<T, *>?)?.submitList(data)
        }
    }
}
