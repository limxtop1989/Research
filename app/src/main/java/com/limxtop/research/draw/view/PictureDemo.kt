package com.limxtop.research.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Picture
import android.view.View
import com.limxtop.research.touch.LogUtils

class PictureDemo() : Picture() {

    val paint: Paint = Paint()

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.run {
            drawText("hello kitty", 0f, 100f, paint)
            drawText("hello kitty", 0f, 200f, paint)
        }
    }
}