package letus179.com.biu.utils;

import android.util.Log;

/**
 * Created by xfyin on 2017/10/7.
 */

public class LogUtils {

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    // 当不想打印任何日志时，只需要将level设置为NOTHING
    public static final int NOTHING = 6;

    public static int level = VERBOSE;


    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.i(tag, msg);
        }
    }


}
