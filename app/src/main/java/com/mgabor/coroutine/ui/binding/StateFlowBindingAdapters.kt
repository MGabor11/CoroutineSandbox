package com.mgabor.coroutine.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@BindingAdapter("stateValue")
@Suppress("UNCHECKED_CAST")
fun <T> TextView.bindStateValue(stateValue: Flow<T>?) {
    stateValue ?: return

    GlobalScope.launch(Dispatchers.Main) {
        stateValue.collect {
            text = it.toString()
        }
    }
}
