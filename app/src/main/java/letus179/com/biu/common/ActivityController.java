package letus179.com.biu.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xfyin on 2017/10/12.
 */

public class ActivityController {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static  void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }

        }
    }
}
