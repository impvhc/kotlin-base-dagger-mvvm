package com.impvhc.core.util

import android.content.Intent
import com.impvhc.core.CoreActivity

import org.jetbrains.anko.browse
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.info
/**
 * Created by victor on 2/13/18.
 */
inline fun <reified T : CoreActivity> CoreActivity.goTo(vararg extras: Pair<String, Any>) {
    info { "Going to ${T::class.java.simpleName}" }

    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    startActivity(intent)
}

inline fun <reified T : CoreActivity> CoreActivity.goToForResult(requestCode: Int, vararg extras: Pair<String, Any>) {
    info { "Going to ${T::class.java.simpleName} for result" }

    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    startActivityForResult(intent, requestCode)
}

inline fun <reified T : CoreActivity> CoreActivity.resetTo(vararg extras: Pair<String, Any>) {
    info { "Resetting to ${T::class.java.simpleName}" }

    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

    startActivity(intent)
}

fun CoreActivity.browseTo(url: String) {
    info { "Browsing to $url" }

    browse(url)
}

fun CoreActivity.goBack() {
    info { "Going back" }

    this.finish()
}

fun CoreActivity.goBackWithResult(requestCode: Int, vararg data: Pair<String, Any>) {
    info { "Going back with result" }

    val dataIntent = Intent()

    dataIntent.putExtras(bundleOf(*data))

    setResult(requestCode, dataIntent)

    finish()
}