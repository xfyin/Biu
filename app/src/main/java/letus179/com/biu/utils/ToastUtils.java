package letus179.com.biu.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xfyin on 2017/10/15.
 */

public class ToastUtils {

    private static Toast toast;

    public static void show(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
