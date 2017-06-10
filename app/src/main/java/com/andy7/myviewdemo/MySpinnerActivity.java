package com.andy7.myviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andy7.myviewdemo.widget.MySpinner;

import java.util.HashMap;

public class MySpinnerActivity extends AppCompatActivity {

    MySpinner myspinner;

    private HashMap<Integer, String> hashMap;

    private static final String TAG = "MySpinnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spinner);
        myspinner = (MySpinner) findViewById(R.id.myspinner);
        hashMap = new HashMap<>();
        hashMap.put(1, "GREEN");
        hashMap.put(2, "RED");
        hashMap.put(3, "BLUE");
        myspinner.setHashMap(hashMap);
        myspinner.setOnSelectorItemKeyValue(new MySpinner.OnSelectorItemKeyValue() {
            @Override
            public void obtainKeyValue(int key, String value) {
                Log.i(TAG, "obtainKeyValue: " + key + "---->" + value);
            }
        });
    }
}
