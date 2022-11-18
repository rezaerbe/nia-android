package com.erbe.nowinandroid.core.common.base

import android.util.Log
import android.view.View

fun click(action: (View) -> Unit): View.OnClickListener {
    return View.OnClickListener { view ->
        view?.let { v ->
            if (v.id > 0) {
                val name = v.context.resources.getResourceEntryName(v.id)
                Log.d("TAG", "onClick: $name")
            }
        }
        action(view)
    }
}