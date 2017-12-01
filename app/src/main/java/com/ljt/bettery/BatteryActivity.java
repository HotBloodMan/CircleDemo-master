package com.ljt.bettery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ljt.circledemo.R;

public class BatteryActivity extends AppCompatActivity {


    private BatteryView bv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        bv = (BatteryView) findViewById(R.id.chargingprigressView);
        Button btnACDraw = (Button) findViewById(R.id.btnACDraw);
        btnACDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             bv.closeAnimation();
             bv.setACAnimation();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bv.closeAnimation();
    }
}
