package com.vishal.cycleapp.custom_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class StabilityChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val bandPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#C5C0E0"); alpha = 120; style = Paint.Style.FILL
    }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#9B8EC4"); strokeWidth = 3f; style = Paint.Style.STROKE
    }
    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#9B8EC4"); style = Paint.Style.FILL
    }
    private val labels = listOf("Jan", "Feb", "Mar", "Apr")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat(); val h = height.toFloat()
        val padLeft = 80f; val padBottom = 40f; val padTop = 20f
        val chartW = w - padLeft - 20f; val chartH = h - padBottom - padTop

        val yLabels = listOf("32d", "28d", "24d")
        val yPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#9E9E9E"); textSize = 28f
        }
        yLabels.forEachIndexed { i, label ->
            canvas.drawText(label, 0f, padTop + i * (chartH / 2) + 10f, yPaint)
        }

        val points = listOf(0f, 0.05f, 0.5f, 0.85f)
        val upperBand = listOf(0f, 0.05f, 0.55f, 0.95f)
        val lowerBand = listOf(0f, 0.0f, 0.35f, 0.65f)
        val xStep = chartW / (points.size - 1)

        val bandPath = Path()
        upperBand.forEachIndexed { i, v ->
            val x = padLeft + i * xStep; val y = padTop + chartH - v * chartH
            if (i == 0) bandPath.moveTo(x, y) else bandPath.lineTo(x, y)
        }
        lowerBand.reversed().forEachIndexed { i, v ->
            val x = padLeft + (lowerBand.size - 1 - i) * xStep; val y = padTop + chartH - v * chartH
            bandPath.lineTo(x, y)
        }
        bandPath.close()
        canvas.drawPath(bandPath, bandPaint)

        val linePath = Path()
        points.forEachIndexed { i, v ->
            val x = padLeft + i * xStep; val y = padTop + chartH - v * chartH
            if (i == 0) linePath.moveTo(x, y) else linePath.lineTo(x, y)
        }
        canvas.drawPath(linePath, linePaint)

        val dotX = padLeft + 2 * xStep
        val dotY = padTop + chartH - points[2] * chartH
        canvas.drawCircle(dotX, dotY, 12f, dotPaint)

        val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#9B8EC4"); strokeWidth = 2f; style = Paint.Style.STROKE
            pathEffect = DashPathEffect(floatArrayOf(10f, 8f), 0f)
        }
        canvas.drawLine(dotX, dotY, dotX, padTop + chartH, dashPaint)

        val tooltipPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK }
        val tooltipW = 160f; val tooltipH = 70f
        val tooltipX = dotX - tooltipW / 2; val tooltipY = dotY - tooltipH - 20f
        canvas.drawRoundRect(RectF(tooltipX, tooltipY, tooltipX + tooltipW, tooltipY + tooltipH), 12f, 12f, tooltipPaint)
        val tPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE; textSize = 24f; textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Stability", tooltipX + tooltipW / 2, tooltipY + 26f, tPaint)
        canvas.drawText("Improving", tooltipX + tooltipW / 2, tooltipY + 56f, tPaint)

        labels.forEachIndexed { i, label ->
            val x = padLeft + i * xStep
            canvas.drawText(label, x, h - 2f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = if (label == "Mar") Color.BLACK else Color.parseColor("#9E9E9E")
                textSize = 28f; textAlign = Paint.Align.CENTER
                if (label == "Mar") typeface = Typeface.DEFAULT_BOLD
            })
        }
    }
}
