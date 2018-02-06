package com.sertac.sertac.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import java.lang.reflect.Field;

public class CircleProgress extends ProgressBar {

    private static final float DEFAULT_START_DEGREE = -90.0f;
    private static final float DEFAULT_PROGRESS_LINE_WIDTH = 4.0f;
    private static final float DEFAULT_BACKGROUND_LINE_WIDTH = 5.0F;
    private static final float DEFAULT_PROGRESS_TEXT_SIZE = 11.0f;
    private static final String DEFAULT_PROGRESS_COLOR = "#2196F3";
    private static final String DEFAULT_TEXT_COLOR = "#000000";
    private static final String DEFAULT_BACKGROUND_COLOR = "#9E9E9E";

    private int progressColor;
    private int progressBackgroundColor;
    private int progressTextColor;
    private float progressLineWidth;
    private float progressTextSize;
    private float backgroundProgressLineWidth;
    private int progress=0;

    private Paint progressTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint progressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint progressBackgroundLinePaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF rectF=new RectF();
    private Rect progressTextRect=new Rect();

    private int centerX;
    private int centerY;
    private int radius;

    public CircleProgress(Context context) {
        super(context);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIndeterminate(false);

        initAttributes(context, attrs);
        initPaints();
        adjustIndeterminate();
    }


    //Set circle progress attributes

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgress);
        try {
            progressColor = typedArray.getColor(R.styleable.CircleProgress_progress_color, Color.parseColor(DEFAULT_PROGRESS_COLOR));
            progressLineWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgress_progress_line_width, dpToPx(DEFAULT_PROGRESS_LINE_WIDTH));
            progressBackgroundColor = typedArray.getColor(R.styleable.CircleProgress_background_progress_color, Color.parseColor(DEFAULT_BACKGROUND_COLOR));
            backgroundProgressLineWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleProgress_background_progress_line_width, dpToPx(DEFAULT_BACKGROUND_LINE_WIDTH));
            progressTextColor = typedArray.getColor(R.styleable.CircleProgress_text_color, dpToPx(Color.parseColor(DEFAULT_TEXT_COLOR)));
            progressTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgress_text_size, dpToPx(DEFAULT_PROGRESS_TEXT_SIZE));
            progress=typedArray.getInt(R.styleable.CircleProgress_progress,progress);
        } finally {
            typedArray.recycle();
        }

    }

    //set circle progress paints

    private void initPaints() {

        progressTextPaint.setTextAlign(Paint.Align.CENTER);
        progressTextPaint.setColor(progressTextColor);
        progressTextPaint.setTextSize(progressTextSize);

        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(progressLineWidth);
        progressPaint.setStyle(Paint.Style.STROKE);

        progressBackgroundLinePaint.setColor(progressBackgroundColor);
        progressBackgroundLinePaint.setStrokeWidth(backgroundProgressLineWidth);
        progressBackgroundLinePaint.setStyle(Paint.Style.STROKE);

    }

    private int dpToPx(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgroundProgressLine(canvas);
        drawProgressLine(canvas);
        drawProgressText(canvas);
    }

    //drawing background progress line

    private void drawBackgroundProgressLine(Canvas canvas) {
        canvas.drawArc(rectF,DEFAULT_START_DEGREE,360.0f,false,progressBackgroundLinePaint);
    }

    //drawing progress line

    private void drawProgressLine(Canvas canvas) {
        canvas.drawArc(rectF,DEFAULT_START_DEGREE,360.0f*progress/getMax(),false,progressPaint);
    }

    //drawing center text of circle progress

    private void drawProgressText(Canvas canvas) {
        String progressText=progress+"/"+getMax();
        progressTextPaint.getTextBounds(progressText,0,progressText.length(),progressTextRect);
        canvas.drawText(progressText,centerX,centerY+progressTextRect.height()/2,progressTextPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        //top and left negative side of centers
        //bottom and right positive side of centers

        radius = Math.min(centerX, centerY);
        rectF.top = centerY - radius;
        rectF.bottom = centerY + radius;
        rectF.left = centerX - radius;
        rectF.right = centerX + radius;

        final float stroke=(progressLineWidth>backgroundProgressLineWidth)?progressLineWidth:backgroundProgressLineWidth;
        rectF.inset(stroke / 2, stroke / 2);

    }


    private void adjustIndeterminate() {
        try {
            Field mOnlyIndeterminateField = ProgressBar.class.getDeclaredField("mOnlyIndeterminate");
            mOnlyIndeterminateField.setAccessible(true);
            mOnlyIndeterminateField.set(this, false);

            Field mIndeterminateField = ProgressBar.class.getDeclaredField("mIndeterminate");
            mIndeterminateField.setAccessible(true);
            mIndeterminateField.set(this, false);

            Field mCurrentDrawableField = ProgressBar.class.getDeclaredField("mCurrentDrawable");
            mCurrentDrawableField.setAccessible(true);
            mCurrentDrawableField.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    //set and get methods of circle progress

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        progressPaint.setColor(progressColor);
        invalidate();
    }

    public int getProgressBackgroundColor() {
        return progressBackgroundColor;
    }

    public void setProgressBackgroundColor(int progressBackgroundColor) {
        this.progressBackgroundColor = progressBackgroundColor;
        progressBackgroundLinePaint.setColor(progressBackgroundColor);
        invalidate();
    }

    public int getProgressTextColor() {
        return progressTextColor;
    }

    public void setProgressTextColor(int progressTextColor) {
        this.progressTextColor = progressTextColor;
        progressTextPaint.setColor(progressTextColor);
        invalidate();
    }

    public float getProgressLineWidth() {
        return progressLineWidth;
    }

    public void setProgressLineWidth(float progressLineWidth) {
        this.progressLineWidth = progressLineWidth;
        progressPaint.setStrokeWidth(progressLineWidth);
        invalidate();
    }

    public float getProgressTextSize() {
        return progressTextSize;
    }

    public void setProgressTextSize(float progressTextSize) {
        this.progressTextSize = progressTextSize;
        progressTextPaint.setTextSize(progressTextSize);
        invalidate();
    }

    public float getBackgroundProgressLineWidth() {
        return backgroundProgressLineWidth;
    }

    public void setBackgroundProgressLineWidth(float backgroundProgressLineWidth) {
        this.backgroundProgressLineWidth = backgroundProgressLineWidth;
        progressBackgroundLinePaint.setStrokeWidth(backgroundProgressLineWidth);
        invalidate();
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void setProgress(int progress) {
        if (progress<=getMax()){
            this.progress = progress;
            invalidate();
        }
    }

}
