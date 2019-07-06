package com.limxtop.research.touch;

import android.util.Log;

public class LogUtils {

	public static void d(String tag, String method, String message) {
		d("Research", tag, method, message);
	}
	
	public static void d(String module, String tag, String method, String message) {
		Log.d(module + "_" + tag + "_" + method, message);
	}

}
