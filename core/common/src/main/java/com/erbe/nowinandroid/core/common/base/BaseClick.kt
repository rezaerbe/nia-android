package com.erbe.nowinandroid.core.common.base

import android.util.Log
import android.view.View

fun click(action: (View) -> Unit): View.OnClickListener {
    return View.OnClickListener { view ->
        view?.let { v ->
            try {
                val name = v.context.resources.getResourceEntryName(v.id)
                Log.d("TAG", "onClick: $name")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        action(view)
    }
}