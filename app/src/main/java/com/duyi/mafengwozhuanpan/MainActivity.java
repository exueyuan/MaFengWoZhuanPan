package com.duyi.mafengwozhuanpan;

import android.app.Activity;
import android.os.Bundle;

import com.duyi.mafengwozhuanpan.zhuanpan.ZhuanPanSetData;
import com.duyi.mafengwozhuanpan.zhuanpan.ZhuanPanView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZhuanPanView zhuanPanView = (ZhuanPanView) findViewById(R.id.zpv_zhuanpan);
        ZhuanPanSetData zhuanPanSetData = new ZhuanPanSetData();
        zhuanPanSetData.setData(zhuanPanView,this);
    }

}
