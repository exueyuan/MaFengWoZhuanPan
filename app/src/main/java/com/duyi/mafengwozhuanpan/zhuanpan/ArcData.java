package com.duyi.mafengwozhuanpan.zhuanpan;

import android.graphics.Bitmap;

/**
 * Created by exueyuan on 2017/8/16.
 */

public class ArcData {
    int innerColor;
    int middleColor;
    int edgeColor;
    int weight;
    int startNum;
    int stopNum;
    String text;
    Bitmap bitmap;

    public ArcData(int edgeColor, int innerColor, int middleColor, int weight, String text,Bitmap bitmap) {
        this.innerColor = innerColor;
        this.middleColor = middleColor;
        this.edgeColor = edgeColor;
        this.weight = weight;
        this.text = text;
        this.bitmap = bitmap;
    }

    public ArcData(int weight, String text,Bitmap bitmap) {
        this.weight = weight;
        this.text = text;
        this.bitmap = bitmap;
    }
}
