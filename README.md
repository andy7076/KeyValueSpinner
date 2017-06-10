# KeyValueSpinner
一个可以获每个Item键值信息的下拉控件
MaterialDesign风格

## Preview ##
<center>
<img src="https://github.com/autotrans/KeyValueSpinner/blob/master/pic/preview.png?raw=true" width="40%" height="75%" />
</center>

## How to ##
Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
		 compile 'com.github.autotrans:KeyValueSpinner:v1.0.0'
	}

Step 3. to use

	<com.andy7.myviewdemo.widget.MySpinner
        android:id="@+id/myspinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </com.andy7.myviewdemo.widget.MySpinner>


	HashMap hashMap = new HashMap<>();
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

## Performance ##
每个Spinner选项拥有键值信息，当选择选项时可以获得当前选项对应的键值信息，而不是简单的索引位置

![](https://github.com/autotrans/KeyValueSpinner/blob/master/pic/performance.png?raw=true)
