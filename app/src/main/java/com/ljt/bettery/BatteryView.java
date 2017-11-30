package com.ljt.bettery;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ljt.circledemo.R;

/**
 * Created by ${JT.L} on 2017/11/30.
 */

public class BatteryView extends View {

    private Context mContext;
    private Paint mPaint;

    //竖直方向
    private static final int VERTICAL = 0;
    //水平方向
    private static final int HORIZONTAL = 1;

    //view的方向
    private int oritation;
    //边界宽度
    private float border_width;
    //item个数
    private int item_count;

    //边界宽度
    private float item_width;
    //边界高度
    private float item_height;
    //view内部的进度前景色
    private int item_charging_src;
    //view内部的进度背景色
    private int item_charging_background;
    //view背景色
    private int background;
    //<!--边界颜色-->
    private int border_color;
    //圆角半径
    private float border_cornor_radius;
    //动画时间
    private int duration;

    //view的宽度和高度
    private int mWidth;
    private int mHeight;

    //交替显示充电完成的动画
    private boolean show = true;

    //直流电: direct-current （ DC ）
    public static final int DC = 1;

    //交流电:	alternating current ( AC ) （交流电流） AC
    public static final int AC = 2;

    //充电类型，默认为交流
    private int chargeType = DC;

    private int progress = 0;

    private ObjectAnimator animAC;
    private ValueAnimator animatorDC;



    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        getSettingValue(attrs);
        initView(context);
    }

    private void getSettingValue(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.charging_progress);
        oritation = array.getInt(R.styleable.charging_progress_cgv_oritation, VERTICAL);
        border_width = array.getDimension(R.styleable.charging_progress_cgv_border_width, dp2px(2));
        item_height = array.getDimension(R.styleable.charging_progress_cgv_item_height, dp2px(10));
        item_width = array.getDimension(R.styleable.charging_progress_cgv_item_width, dp2px(20));
        item_charging_src = array.getColor(R.styleable.charging_progress_cgv_item_charging_src, 0xffffea00);
        item_charging_background = array.getColor(R.styleable.charging_progress_cgv_item_charging_background, 0xff544645);
        background = array.getColor(R.styleable.charging_progress_cgv_background, 0xff463938);
        border_color = array.getColor(R.styleable.charging_progress_cgv_border_color, 0xffb49d7c);
        border_cornor_radius = array.getDimension(R.styleable.charging_progress_cgv_border_cornor_radius, dp2px(2));
        duration = array.getInt(R.styleable.charging_progress_cgv_duration, 10 * 1000);
        item_count = array.getInt(R.styleable.charging_progress_cgv_item_count, 10);
    }
    /**
     * 当前进度
     *
     * @return
     */
    public int getProgress() {
        return progress % 100;
    }

    /**
     * 设置充电进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }



    private void initView(Context context) {
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(border_width);
        mPaint.setColor(border_color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left=mWidth*3/8;
        int top=0;
        int right=5*mWidth/8;
        int bottom= (int) (item_height/2);
        RectF topRect = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(topRect,border_cornor_radius,border_cornor_radius,mPaint);

        RectF border = new RectF(0, bottom, mWidth, mHeight);
        canvas.drawRoundRect(border, border_cornor_radius, border_cornor_radius, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor((background));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       if(oritation==VERTICAL){
           //总间隔数=(item_count+1)  乘以间隔高度（间隔高度等于item_height的一半）
           //总数=item_count 乘以 item_height + 总间隔数 + 顶部一个矩形（高度等于item的高度，宽度等于item的宽度的一半）
           mHeight= (int) (item_count*item_height+(item_count+1)*item_height/2+item_height);
           mWidth= (int) (2*item_width);
       }

        setMeasuredDimension(mWidth,mHeight);

    }

    /**
     * dp转化为px`
     *
     * @param dp
     * @return
     */
    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics());
    }

    /**
     * sp转为px
     *
     * @param sp
     * @return
     */
    protected int sp2px(int sp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp,
                getResources().getDisplayMetrics());
    }
}
