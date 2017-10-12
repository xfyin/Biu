package letus179.com.biu.navigation;

import java.util.ArrayList;
import java.util.List;

public class HeadTabDb {
    private static final List<HeadTab> Selected = new ArrayList<>();

    static {
        Selected.add(new HeadTab("话题"));
        Selected.add(new HeadTab("帮选"));
        Selected.add(new HeadTab("热门"));
        Selected.add(new HeadTab("碎碎念"));
        Selected.add(new HeadTab("最新"));
    }

    /***
     * 获得头部tab的所有项
     */
    public static List<HeadTab> getSelected() {
        return Selected;
    }

}
