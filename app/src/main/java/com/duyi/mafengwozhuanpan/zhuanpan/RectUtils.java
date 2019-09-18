package com.duyi.mafengwozhuanpan.zhuanpan;

import android.graphics.Bitmap;
import android.graphics.Rect;


/**
 * Created by exueyuan on 2017/8/16.
 */

public class RectUtils {
    public static Rect getDrawableRect(Bitmap bitmap, Rect src) {
        int dstHeight;
        int dstWidth;
        int top;
        int left;
        int right;
        int bottom;
        if ((((float) bitmap.getHeight()) / bitmap.getWidth()) >= (float) src.height() / src.width()) {
            dstHeight = src.height();
            dstWidth = dstHeight * bitmap.getWidth() / bitmap.getHeight();
            top = src.top;
            left = src.left + (src.width() / 2 - dstWidth / 2);
            right = left + dstWidth;
            bottom = top + dstHeight;
        } else {
            dstWidth = src.width();
            dstHeight = dstWidth * bitmap.getHeight() / bitmap.getWidth();
            left = src.left;
            top = src.top + (src.height() / 2 - dstHeight / 2);
            right = left + dstWidth;
            bottom = top + dstHeight;
        }
        return new Rect(left, top, right, bottom);
    }
}
