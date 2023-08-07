package com.victor.firetracker_app.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TemperatureCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val outlinePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 90f
        textAlign = Paint.Align.CENTER
    }

    private var temperature: String = "  "

    fun setTemperature(temperature: String) {
        this.temperature = temperature
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (Math.min(width, height) / 2f) - (outlinePaint.strokeWidth / 2f)

        // Draw the outlined circle
        canvas.drawCircle(centerX, centerY, radius, outlinePaint)

        // Draw the temperature text inside the circle
        canvas.drawText("${temperature.take(4)} °C", centerX, centerY, textPaint.apply{textSize = 90f})
        canvas.drawText("Temperatura", centerX, centerY+70, textPaint.apply { textSize = 40f })
    }
}
