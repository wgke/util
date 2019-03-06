# util
工具 自己使用

涉及，普通工具类，图片加载，json解析

初始化 AppUtil.setContext(getApplication());

权限添加：
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    
    
 gradle:
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
    implementation 'jp.wasabeef:glide-transformations:4.0.1'
    implementation 'com.alibaba:fastjson:1.1.70.android'


-------------------

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

--------------------
dependencies {
	        implementation 'com.github.wgke:util:1.0'
	}