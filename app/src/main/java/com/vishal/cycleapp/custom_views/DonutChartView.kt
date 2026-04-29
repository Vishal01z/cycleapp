package com.vishal.cycleapp.custom_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DonutChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    data class Segment(val label: String, val percent: Int, val color: Int)

    private val segments = listOf(
        Segment("Mood",     30, Color.parseColor("#F2C4C4")),
        Segment("Bloating", 31, Color.parseColor("#C5C0E0")),
        Segment("Fatigue",  21, Color.parseColor("#D98080")),
        Segment("Acne",     17, Color.parseColor("#8FAF9F")),
        Segment("Other",     1, Color.parseColor("#E0E0E0"))
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cx = width / 2f; val cy = height / 2f
        val outerR = minOf(cx, cy) * 0.7f
        val innerR = outerR * 0.55f
        val rect = RectF(cx - outerR, cy - outerR, cx + outerR, cy + outerR)

        var startAngle = -90f
        segments.forEach { seg ->
            val sweep = seg.percent / 100f * 360f
            canvas.drawArc(rect, startAngle, sweep - 2f, false, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = seg.color; strokeWidth = outerR - innerR; style = Paint.Style.STROKE
            })
            startAngle += sweep
        }

        var sa = -90f
        segments.dropLast(1).forEach { seg ->
            val sweep = seg.percent / 100f * 360f
            val midAngle = sa + sweep / 2f
            val rad = Math.toRadians(midAngle.toDouble())
            val labelR = outerR + 80f
            val lx = cx + labelR * Math.cos(rad).toFloat()
            val ly = cy + labelR * Math.sin(rad).toFloat()

            canvas.drawRoundRect(RectF(lx - 70f, ly - 30f, lx + 70f, ly + 10f), 20f, 20f,
                Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; style = Paint.Style.FILL })
            canvas.drawText("${seg.percent}%", lx, ly - 10f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#1A1A2E"); textSize = 28f
                textAlign = Paint.Align.CENTER; typeface = Typeface.DEFAULT_BOLD
            })
            canvas.drawText(seg.label, lx, ly + 8f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#1A1A2E"); textSize = 22f; textAlign = Paint.Align.CENTER
            })
            sa += sweep
        }
    }
}
