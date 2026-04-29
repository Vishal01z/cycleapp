package com.vishal.cycleapp.custom_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CycleTrendsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val cycleLengths = listOf(28, 30, 28, 32, 28, 28)
    private val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
    private val purplePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#C5C0E0") }
    private val greenPaint  = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#8FAF9F") }
    private val redPaint    = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#E8A0A0") }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat(); val h = height.toFloat()
        val padBottom = 36f; val padTop = 36f
        val chartH = h - padBottom - padTop
        val barW = w / (cycleLengths.size * 2f)
        val maxLen = cycleLengths.max().toFloat()

        cycleLengths.forEachIndexed { i, len ->
            val x = (2 * i + 1) * barW - barW / 2
            val barH = (len / maxLen) * chartH * 0.9f
            val top = padTop + chartH - barH
            val barLeft = x - barW * 0.35f; val barRight = x + barW * 0.35f
            val r = (barRight - barLeft) / 2

            canvas.drawRoundRect(RectF(barLeft, top, barRight, padTop + chartH), r, r, purplePaint)
            val greenH = barH * 0.25f; val greenTop = top + barH * 0.3f
            canvas.drawRoundRect(RectF(barLeft, greenTop, barRight, greenTop + greenH), r, r, greenPaint)
            val redH = barH * 0.15f
            canvas.drawRoundRect(RectF(barLeft, padTop + chartH - redH, barRight, padTop + chartH), r, r, redPaint)

            canvas.drawText(len.toString(), x, padTop - 6f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#1A1A2E"); textSize = 28f
                textAlign = Paint.Align.CENTER; typeface = Typeface.DEFAULT_BOLD
            })
            canvas.drawText(months[i], x, h, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#9E9E9E"); textSize = 26f; textAlign = Paint.Align.CENTER
            })
        }
    }
}
