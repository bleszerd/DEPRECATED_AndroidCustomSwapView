package com.github.bleszerd.swipecomponentexample

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.github.bleszerd.swipecomponentexample.databinding.SwipeComponentBinding

/**
SwipeComponentExample
29/07/2021 - 16:53
Created by bleszerd.
@author alive2k@programmer.net
 */
class SwipeComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val binding = SwipeComponentBinding.inflate(LayoutInflater.from(context), this, true)

    companion object {
        private var swipeActives = arrayListOf<View>()

        fun resetOthers(view: View) {
            for (swipe in swipeActives) {
                if (swipe != view)
                    swipe.translationX = 0f
            }
        }
    }

    init {
        initComponent()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initComponent() {
        var widthV = 0f

        binding.buttonDelete.doOnLayout {
            widthV = it.measuredWidth.toFloat()
        }

        var startX = 0f
        var absoluteDragX = 0f
        val sensibility = 0.5f

        binding.swipeBox.setOnTouchListener { view, event ->
            swipeActives.add(view)

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    resetOthers(view)
                    startX = event.x
                }
                MotionEvent.ACTION_UP -> {
                    if (view.translationX > widthV * (widthV / widthV * sensibility) * 1.9) {
                        view.translationX =
                            (widthV * (widthV / widthV * sensibility) * 1.9).toFloat()
                    }

                    if (view.translationX < 0)
                        view.translationX = 0f
                }
                MotionEvent.ACTION_MOVE -> {
                    absoluteDragX = (event.x - startX) * sensibility

                    println(translationX)
                    println(widthV)

                    if (view.translationX > widthV * (widthV / widthV * sensibility) * 1.9) {
                        view.translationX =
                            (widthV * (widthV / widthV * sensibility) * 1.9).toFloat()
                    }

                    if (view.translationX < widthV * (widthV / widthV * sensibility) * 1.9 || absoluteDragX < 0) {
                        if (view.translationX > 0 || view.translationX == 0f)
                            view.translationX = view.translationX + absoluteDragX
                    }
                }
                else -> {
                    resetOthers(view)
                }
            }
            handlea(view, widthV, sensibility)
            true
        }
    }

    private fun handlea(view: View, widthV: Float, sensibility: Float) {
        if (view.translationX > 75)
            view.translationX =
                (widthV * (widthV / widthV * sensibility) * 1.9).toFloat()


        if (view.translationX > widthV * (widthV / widthV * sensibility) * 1.9) {
            view.translationX =
                (widthV * (widthV / widthV * sensibility) * 1.9).toFloat()
        }
    }
}