package com.duyi.mafengwozhuanpan.zhuanpan;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.duyi.mafengwozhuanpan.R;

import java.util.Random;

/**
 * Created by exueyuan on 2017/8/6.
 */

public class ZhuanPanView extends View {

    private Paint paint;
    private int num;
    private float perAngle;
    private float startAngle;
    private RectF edgeCircleRect;
    private RectF middleCircleRect;
    private RectF innerCircleRect;
    private int weightNum;
    private ValueAnimator valueAnimator;
    private Bitmap anNiubitmap;
    private Rect rect;

    public int getArcNum() {
        int arcNum = 0;
        Random random = new Random();
        int num = random.nextInt(weightNum);
        Log.i("arcNum", "随机数为：" + num + "," + weightNum);
        for (int i = 0; i < arcDatas.length; i++) {
            ArcData arcData = arcDatas[i];
            if (num >= arcData.startNum && num <= arcData.stopNum) {
                arcNum = i;
                break;
            }
        }
        return arcNum;
    }


    private ArcData[] arcDatas;

    private int min;
    private int width;
    private int height;
    private float canvasRotate;

    public ZhuanPanView(Context context) {
        super(context);
        init();
    }


    public ZhuanPanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZhuanPanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        min = Math.min(height, width);

        if (height > width) {
            float edgePainyi = min / 2 * 0.7f / 100;
            edgeCircleRect = new RectF(0 + edgePainyi, height / 2 - min / 2 + edgePainyi, width - edgePainyi, height / 2 + min / 2 - edgePainyi);
            float middlePianyi = min / 2 * 25f / 100;
            middleCircleRect = new RectF(min / 2 / 4 + middlePianyi, height / 2 - min / 2 + middlePianyi, width - middlePianyi, height / 2 + min / 2 - middlePianyi);
            innerCircleRect = new RectF(min / 2 / 3 * 2, height / 2 - min / 2 + min / 2 / 3 * 2, width - min / 2 / 3 * 2, height / 2 + min / 2 - min / 2 / 3 * 2);
        } else {
            float edgePainyi = min / 2 * 0.7f / 100;
            edgeCircleRect = new RectF(width / 2 - min / 2 + edgePainyi, 0 + edgePainyi, width / 2 + min / 2 - edgePainyi, height - edgePainyi);
            float middlePianyi = min / 2 * 25f / 100;
            middleCircleRect = new RectF(width / 2 - min / 2 + middlePianyi, 0 + middlePianyi, width / 2 + min / 2 - middlePianyi, height - middlePianyi);
            innerCircleRect = new RectF(width / 2 - min / 2 + min / 2 / 3 * 2, 0 + min / 2 / 3 * 2, width / 2 + min / 2 - min / 2 / 3 * 2, height - min / 2 / 3 * 2);
        }
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //需要关闭硬件加速（没有关闭则没效果）
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initArcData() {
        //计算有多少个元素
        num = arcDatas.length;
        //计算每个元素的角度
        perAngle = 360f / num;
        //设置开始的角度
        startAngle = -90;
        //计算每个元素的开始和结束权重数字
        weightNum = 0;
        for (int i = 0; i < arcDatas.length; i++) {
            ArcData arcData = arcDatas[i];
            arcData.startNum = weightNum;
            weightNum += arcData.weight;
            arcData.stopNum = weightNum - 1;
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setXuanzhuan();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        if (arcDatas == null) {
            return;
        }

        canvas.save();
        canvas.rotate(canvasRotate, width / 2, height / 2);
        canvas.drawCircle(width / 2, height / 2, min / 2, paint);
        for (int i = 0; i < arcDatas.length; i++) {
            ArcData arcData = arcDatas[i];
            drawArc(canvas, startAngle + i * perAngle, arcData, i);
        }

        canvas.restore();
        if (anNiubitmap == null) {
            anNiubitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.anniu);
        }
        if (rect == null) {
            rect = new Rect((int) (width / 2 - min / 2 * 34f / 100), (int) (width / 2 - min / 2 * 49f / 100), (int) (width / 2 + min / 2 * 34f / 100), (int) (height / 2 + min / 2 * 34f / 100));
        }
        canvas.drawBitmap(anNiubitmap, null, rect, paint);

    }

    public void drawArc(Canvas canvas, float startAngle, ArcData arcData, int length) {
        paint.setColor(arcData.edgeColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(edgeCircleRect, startAngle, perAngle, true, paint);
        paint.setColor(arcData.middleColor);
        canvas.drawArc(middleCircleRect, startAngle, perAngle, true, paint);
        paint.setColor(arcData.innerColor);
        canvas.drawArc(innerCircleRect, startAngle, perAngle, true, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawArc(edgeCircleRect, startAngle, perAngle, true, paint);

        //画文字
        canvas.save();
        canvas.rotate(perAngle * length + perAngle / 2, width / 2, height / 2);

        paint.reset();
        paint.setTextSize(min / 2 * 10 / 100);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float textCenter = height / 2 - min / 2 + min / 2 / 4 / 2;
        float baselineY = textCenter + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;

        canvas.drawText(arcData.text, width / 2, baselineY, paint);
        //画图
        int left = (int) (width / 2 - min / 2 * 15f / 100);
        int top = (int) (height / 2 - min / 2 * 65f / 100);
        int right = (int) (width / 2 + min / 2 * 15f / 100);
        int bottom = (int) (height / 2 - min / 2 * 45f / 100);
        Rect srcRect = new Rect(left, top, right, bottom);
        Rect dstRect = RectUtils.getDrawableRect(arcData.bitmap, srcRect);
        Log.i("rectRect", "srcRect:left:" + srcRect.left + ",top:" + srcRect.top + ",right:" + srcRect.right + ",bottom:" + srcRect.bottom);
        Log.i("rectRect", "dstRect:left:" + dstRect.left + ",top:" + dstRect.top + ",right:" + dstRect.right + ",bottom:" + dstRect.bottom);
        Log.i("rectRect", "bitmap:" + arcData.bitmap.getWidth() + "," + arcData.bitmap.getHeight());
        canvas.drawBitmap(arcData.bitmap, null, dstRect, paint);
        canvas.restore();

    }

    public void setXuanzhuan() {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            int arcNum = getArcNum();
            for (int i = 0; i < arcDatas.length; i++) {
                Log.i("arcNum", arcDatas[i].startNum + "," + arcDatas[i].stopNum);
            }
            Log.i("arcNum", arcNum + "");
            valueAnimator = new ValueAnimator().ofInt((int) canvasRotate % 360, (int) (360 * 12 - perAngle / 2 - perAngle * arcNum));
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.setDuration(3000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();
                    canvasRotate = animatedValue;
                    invalidate();
                }
            });
            valueAnimator.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (arcDatas == null) {
            return false;
        }
        if (event.getX() > (width / 2 - min / 2 / 3)
                && event.getX() < (width / 2 + min / 2 / 3)
                && event.getY() > (height / 2 - min / 2 / 3)
                && event.getY() < (height / 2 + min / 2 / 3)) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    public void setArcDatas(ArcData[] arcDatas) {
        this.arcDatas = arcDatas;
        initArcData();
        invalidate();
    }
}
