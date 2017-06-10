package com.andy7.myviewdemo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.andy7.myviewdemo.R;
import com.andy7.myviewdemo.adapter.MySpinnerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by andy7 on 2017/6/8.
 */

public class MySpinner extends AppCompatSpinner implements MySpinnerAdapter.OnItemClickListener {

    public static SelectDialog selectDialog = null;
    private MySpinnerAdapter adapter;
    private HashMap<Integer, String> hashMap;
    private ArrayList<String> list;
    public static String currentContent;

    private OnSelectorItemKeyValue onSelectorItemKeyValue;

    private int[] outLocation = new int[2];
    private int pointX;
    private int pointY;
    private int spinnerWidth;
    private int spinnerHeight;

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        list = new ArrayList<>();
    }

    public void setOnSelectorItemKeyValue(OnSelectorItemKeyValue onSelectorItemKeyValue) {
        this.onSelectorItemKeyValue = onSelectorItemKeyValue;
    }

    @Override
    public void onItemClick(int position) {
        getKey(list.get(position));
        setSelection(position);
        setCurrentContent(list.get(position));
        if (selectDialog != null) {
            selectDialog.dismiss();
            selectDialog = null;
        }
    }

    //如果视图定义了OnClickListener监听器，调用此方法来执行
    @Override
    public boolean performClick() {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.layout_myspinner_list, null);

        this.getLocationOnScreen(outLocation);
        this.pointX = outLocation[0];
        this.pointY = outLocation[1];
        spinnerWidth = getMeasuredWidth();
        spinnerHeight = getMeasuredHeight();

        RecyclerView listMySpinner = (RecyclerView) view.findViewById(R.id.list_myspinner);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listMySpinner.setLayoutManager(manager);
        adapter = new MySpinnerAdapter(context, list);
        adapter.setOnItemClickListener(this);
        listMySpinner.setAdapter(adapter);

        selectDialog = new SelectDialog(context, R.style.dialog);//创建Dialog并设置样式主题
        LayoutParams params = new LayoutParams(spinnerWidth, LayoutParams.WRAP_CONTENT);
        //selectDialog的位置:在mySpinner的下面
        Window window = selectDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER | android.view.Gravity.TOP;
        lp.y = pointY + spinnerHeight - getStatusBarHeight(context);
        window.setAttributes(lp);

        selectDialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
        selectDialog.show();
        selectDialog.addContentView(view, params);
        return true;
    }

    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, String> hashMap) {
        this.hashMap = hashMap;
        list.clear();
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            list.add((String) entry.getValue());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        this.setAdapter(adapter);
    }

    public static String getCurrentContent() {
        return currentContent;
    }

    public static void setCurrentContent(String currentContent) {
        MySpinner.currentContent = currentContent;
    }

    /**
     * 通过值得到key
     * 如果没有找到返回-1
     *
     * @param value
     * @return
     */
    public int getKey(String value) {
        int key = -1;
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (value.equals(entry.getValue())) {
                key = (int) entry.getKey();
                onSelectorItemKeyValue.obtainKeyValue(key, value);
                break;
            }
        }
        return key;
    }

    public interface OnSelectorItemKeyValue {
        void obtainKeyValue(int key, String value);
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
