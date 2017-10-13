package letus179.com.biu.common;

import android.app.Application;
import android.content.Context;

/**
 * 该类使得在项目的任何地方都能获取到Context
 * 只需要调用MyApplication.getContext() 即可获得
 * <p>
 * Created by xfyin on 2017/10/13.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
