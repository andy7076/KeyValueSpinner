package com.andy7.myviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andy7.myviewdemo.widget.MySpinner;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySpinnerActivity extends AppCompatActivity {

    @BindView(R.id.myspinner)
    MySpinner myspinner;

    private HashMap<Integer, String> hashMap;

    private static final String TAG = "MySpinnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spinner);
        ButterKnife.bind(this);
        hashMap = new HashMap<>();
        hashMap.put(1, "GREEN");
        hashMap.put(2, "RED");
        hashMap.put(3, "BLUE");
        myspinner.setHashMap(hashMap);
        myspinner.setOnSelectorItemKey(new MySpinner.OnSelectorItemKey() {
            @Override
            public void obtainKey(int key) {
                Log.i(TAG, "obtainKey: " + key);
            }
        });
    }
}
