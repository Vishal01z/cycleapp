package com.vishal.cycleapp.custom_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class WeightChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val weights = listOf(0.1f, 0.35f, 0.3f, 0.95f, 0.75f, 0.55f)
    private val months  = listOf("Jan", "Feb", "Mar", "Apr", "May")
    private val yLabels = listOf("75", "50", "25")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat(); val h = height.toFloat()
        val padLeft = 60f; val padBottom = 36f; val padTop = 10f
        val chartW = w - padLeft - 16f; val chartH = h - padBottom - padTop
        val xStep = chartW / (weights.size - 1)

        val fillPath = Path()
        weights.forEachIndexed { i, v ->
            val x = padLeft + i * xStep; val y = padTop + chartH - v * chartH
            if (i == 0) fillPath.moveTo(x, y) else fillPath.lineTo(x, y)
        }
        fillPath.lineTo(padLeft + (weights.size - 1) * xStep, padTop + chartH)
        fillPath.lineTo(padLeft, padTop + chartH)
        fillPath.close()
        canvas.drawPath(fillPath, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(0f, 0f, 0f, h,
                Color.parseColor("#F2C4C4"), Color.parseColor("#FFF0F0"), Shader.TileMode.CLAMP)
            style = Paint.Style.FILL
        })

        val linePath = Path()
        weights.forEachIndexed { i, v ->
            val x = padLeft + i * xStep; val y = padTop + chartH - v * chartH
            if (i == 0) linePath.moveTo(x, y) else linePath.lineTo(x, y)
        }
        canvas.drawPath(linePath, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#D98080"); strokeWidth = 3f; style = Paint.Style.STROKE
        })

        val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#D98080") }
        listOf(1, 3, 4).forEach { i ->
            canvas.drawCircle(padLeft + i * xStep, padTop + chartH - weights[i] * chartH, 8f, dotPaint)
        }

        yLabels.forEachIndexed { i, label ->
            canvas.drawText(label, 0f, padTop + i * (chartH / 2) + 10f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#9E9E9E"); textSize = 26f
            })
        }
        months.forEachIndexed { i, label ->
            canvas.drawText(label, padLeft + i * xStep, h, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#9E9E9E"); textSize = 26f; textAlign = Paint.Align.CENTER
            })
        }
    }
}
