package letus179.com.biu.navigation;

import letus179.com.biu.R;

/**
 * 设置导航栏的数据和图片切换时候的资源
 */
public class FootTabDb {
    /***
     * 获得底部所有项
     */
    public static String[] getTabsTxt() {
        String[] tabs = {"动态", "发现", "消息", "我的"};
        return tabs;
    }

    /***
     * 获得所有碎片
     */
    public static Class[] getFragment() {
        Class[] cls = {DynamicFragment.class, FoundFragment.class, MessageFragment.class, MyFragment.class};
        return cls;
    }

    /***
     * 获得所有点击前的图片
     */
    public static int[] getTabsImg() {
        int[] img = {R.drawable.home1, R.drawable.glod1, R.drawable.xc1, R.drawable.user1};
        return img;
    }

    /***
     *  获得所有点击后的图片
     */
    public static int[] getTabsImgLight() {
        int[] img = {R.drawable.home2, R.drawable.glod2, R.drawable.xc2, R.drawable.user2};
        return img;
    }
}
