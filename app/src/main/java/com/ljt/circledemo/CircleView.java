package com.ljt.circledemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ${JT.L} on 2017/10/24.
 */

public class CircleView extends View{

    public static String TAG= CircleView.class.getSimpleName();

    private Paint rPaint;//绘制矩形的画笔
    private Paint progressPaint;//绘制圆弧的画笔
    private Paint bgPaint;//绘制背景圆弧的画笔
    private int bgColor;
    private float startAngle;
    private float sweepAngle;
    private float barWidth;
    private TypedArray typedArray;
    private int progressColor;

    private float progressNum;
    private float maxNum;
    private int defaultSize;
    private RectF mRectF;


    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
         Log.d(TAG,TAG+" initView(----->>> ");
        TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.CircleBarView);
        progressColor = typedArray.getColor(R.styleable.CircleBarView_progress_color, Color.GREEN);
        bgColor = typedArray.getColor(R.styleable.CircleBarView_bg_color, Color.GRAY);
        startAngle = typedArray.getFloat(R.styleable.CircleBarView_start_angle, 0);
        sweepAngle = typedArray.getFloat(R.styleable.CircleBarView_sweep_angle, 360);
        barWidth = typedArray.getDimension(R.styleable.CircleBarView_bar_width, DpOrPxUtils.dip2px(context, 10));
        typedArray.recycle();

        progressNum=0;
        maxNum=100;
        defaultSize=DpOrPxUtils.dip2px(context,100);
         Log.d(TAG,TAG+" ----->>>defaultSize= "+defaultSize);
        mRectF = new RectF();


        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);//设置抗锯齿
        progressPaint.setStrokeWidth(barWidth);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔为圆角

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        bgPaint.setColor(bgColor);
        bgPaint.setAntiAlias(true);//设置抗锯齿
        bgPaint.setStrokeWidth(barWidth);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
