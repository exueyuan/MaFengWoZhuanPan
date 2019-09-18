package com.duyi.mafengwozhuanpan.zhuanpan;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.duyi.mafengwozhuanpan.R;

/**
 * Created by exueyuan on 2017/8/16.
 */

public class ZhuanPanSetData {
    public void setData(ZhuanPanView zhuanPanView, Context context) {
        ArcData[] arcDatas = new ArcData[]{
                new ArcData(ZhuanPanColor.EDGE_COLOR_1, ZhuanPanColor.MIDDLE_COLOR_1, ZhuanPanColor.INNER_COLOR_1, 1, "iPhone7", BitmapFactory.decodeResource(context.getResources(), R.mipmap.shouji)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_2, ZhuanPanColor.MIDDLE_COLOR_2, ZhuanPanColor.INNER_COLOR_2, 100, "10蜂蜜", BitmapFactory.decodeResource(context.getResources(), R.mipmap.pingzi)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_1, ZhuanPanColor.MIDDLE_COLOR_1, ZhuanPanColor.INNER_COLOR_1, 100, "优惠券", BitmapFactory.decodeResource(context.getResources(), R.mipmap.kaquan2)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_2, ZhuanPanColor.MIDDLE_COLOR_2, ZhuanPanColor.INNER_COLOR_2, 100, "优惠券", BitmapFactory.decodeResource(context.getResources(), R.mipmap.kaquan3)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_1, ZhuanPanColor.MIDDLE_COLOR_1, ZhuanPanColor.INNER_COLOR_1, 500, "5蜂蜜", BitmapFactory.decodeResource(context.getResources(), R.mipmap.pingzi)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_2, ZhuanPanColor.MIDDLE_COLOR_2, ZhuanPanColor.INNER_COLOR_2, 1000, "1蜂蜜", BitmapFactory.decodeResource(context.getResources(), R.mipmap.pingzi)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_1, ZhuanPanColor.MIDDLE_COLOR_1, ZhuanPanColor.INNER_COLOR_1, 100, "通用券", BitmapFactory.decodeResource(context.getResources(), R.mipmap.kaquan)),
                new ArcData(ZhuanPanColor.EDGE_COLOR_2, ZhuanPanColor.MIDDLE_COLOR_2, ZhuanPanColor.INNER_COLOR_2, 100, "20蜂蜜", BitmapFactory.decodeResource(context.getResources(), R.mipmap.pingzi))
        };
        zhuanPanView.setArcDatas(arcDatas);
    }

    public void setDataNotIncludeColor(ZhuanPanView zhuanPanView, Context context, ArcData[] arcDatasNotIncludeColor) {
        for (int i = 0; i < arcDatasNotIncludeColor.length; i++) {
            ArcData arcData = arcDatasNotIncludeColor[i];
            if (i%2 == 0){
                arcData.edgeColor = ZhuanPanColor.EDGE_COLOR_1;
                arcData.middleColor = ZhuanPanColor.MIDDLE_COLOR_1;
                arcData.innerColor = ZhuanPanColor.INNER_COLOR_1;
            } else {
                arcData.edgeColor = ZhuanPanColor.EDGE_COLOR_2;
                arcData.middleColor = ZhuanPanColor.MIDDLE_COLOR_2;
                arcData.innerColor = ZhuanPanColor.INNER_COLOR_2;
            }
        }
        zhuanPanView.setArcDatas(arcDatasNotIncludeColor);
    }

    public void setDataIncludeColor(ZhuanPanView zhuanPanView, Context context, ArcData[] arcDatas) {
        zhuanPanView.setArcDatas(arcDatas);
    }
}
