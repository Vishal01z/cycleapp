package com.vishal.cycleapp.custom_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CorrelationGridView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val rows = listOf(
        Triple("Sleep",    7, Color.parseColor("#C5C0E0")),
        Triple("Hydrate",  3, Color.parseColor("#F2C4C4")),
        Triple("Caffeine", 5, Color.parseColor("#8FAF9F")),
        Triple("Exercise", 4, Color.parseColor("#F2C4C4"))
    )
    private val totalCells = 9

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rowH = height / rows.size.toFloat()
        val labelW = 160f
        val cellSize = (width - labelW - 20f) / totalCells
        val cellPad = 6f

        rows.forEachIndexed { ri, (label, filled, color) ->
            val y = ri * rowH + rowH / 2f
            canvas.drawText(label, 0f, y + 10f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                this.color = Color.parseColor("#1A1A2E"); textSize = 28f
            })
            for (ci in 0 until totalCells) {
                val cx = labelW + ci * (cellSize + cellPad)
                canvas.drawRoundRect(
                    RectF(cx, y - cellSize / 2, cx + cellSize, y + cellSize / 2),
                    8f, 8f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        this.color = if (ci < filled) color else Color.parseColor("#EBEBEB")
                        style = Paint.Style.FILL
                    })
            }
        }
    }
}
