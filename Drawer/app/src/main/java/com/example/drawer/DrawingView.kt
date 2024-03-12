package com.example.drawer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View


class DrawingView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {
    private lateinit var drawPath: CustomPath
    private lateinit var canvasBitMap: Bitmap
    private var drawPaint: Paint = Paint()
    private var brushSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas: Canvas = Canvas()
    private var canvasPaint: Paint = Paint()
    private val paths = ArrayList<CustomPath>()

    init {
        setUpVars()
    }


    private fun setUpVars() {
        canvasBitMap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        drawPaint = Paint()
        drawPath = CustomPath(color, brushSize)

        drawPaint.color = color
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
        brushSize = 20F
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitMap, 0f, 0f, canvasPaint)

        for (path in paths) {

            drawPaint.strokeWidth = path.brushThickness
            drawPaint.color = path.color


            canvas.drawPath(path, drawPaint)
        }
        if (!drawPath.isEmpty) {
            drawPaint.strokeWidth = drawPath.brushThickness
            drawPaint.color = drawPath.color
            canvas.drawPath(drawPath, drawPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val touchX = event?.x
        val touchY = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.color = color
                drawPath.brushThickness = brushSize
                drawPath.reset()
                if (touchX != null && touchY != null) {
                    drawPath.moveTo(touchX, touchY)

                }


            }


            MotionEvent.ACTION_MOVE -> {
                if (touchX != null && touchY != null) {
                    drawPath.lineTo(touchX, touchY)
                }

            }

            MotionEvent.ACTION_UP -> {
                paths.add(drawPath)
                drawPath = CustomPath(color, brushSize)

            }

            else -> {
                return false
            }
        }
        invalidate()
        return true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitMap)
    }

    fun setSizeForBrush(newSize: Float) {
        brushSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            newSize,
            resources.displayMetrics
        )
        drawPaint.strokeWidth = brushSize
    }

    fun setColor(newColor: String) {
        color = Color.parseColor(newColor)
    }


    fun undo() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            invalidate()
        }
    }

    internal inner class CustomPath(
        var color: Int,
        var brushThickness: Float
    ) : Path()
}