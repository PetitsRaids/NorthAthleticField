package com.petits_raids.northathleticfield.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.CalenderUtils;
import com.petits_raids.northathleticfield.utils.Logger;

public class ProgressView extends View {
    final float radius = dpToPixel(80);

    float progress = 0;
    RectF arcRectF = new RectF();

    int dayOfMonth;

    private boolean isMonth;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        isMonth = array.getBoolean(R.styleable.ProgressView_isMonth, false);
        array.recycle();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    {
        paint.setTextSize(dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        if (isMonth) {
            dayOfMonth = (int) (CalenderUtils.getTotalDays() * progress / 100);
        }
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;

        paint.setColor(Color.parseColor("#E91E63"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dpToPixel(15));
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(arcRectF, 135, progress * 2.7f, false, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        drawText(isMonth, canvas, centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);
//        canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);
    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    private void drawText(boolean isMonth, Canvas canvas, float x, float y, Paint paint) {
        String progressStyle;
        if (isMonth) {
            progressStyle = dayOfMonth + "/" + CalenderUtils.getTotalDays();
        } else {
            progressStyle = (int) progress + "%";
        }
        canvas.drawText(progressStyle, x, y, paint);
    }
}
