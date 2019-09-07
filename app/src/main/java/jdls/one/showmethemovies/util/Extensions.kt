package jdls.one.showmethemovies.util

import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.showSnackbar(
  @StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG,
  action: Snackbar.() -> Unit
) {
  val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
  snackBar.action()
  snackBar.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
  setAction(actionRes, listener)
  color?.let { setActionTextColor(color) }
}

fun ImageView.loadImage(url: String) {
  Glide.with(context)
    .load(url)
    .into(this)
}