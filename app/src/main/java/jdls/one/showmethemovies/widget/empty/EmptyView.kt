package jdls.one.showmethemovies.widget.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import jdls.one.showmethemovies.R

class EmptyView : RelativeLayout {

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
      super(context, attrs, defStyleAttr) {
    init()
  }

  private fun init() = LayoutInflater.from(context).inflate(R.layout.view_empty, this)
}