package letus179.com.biu.bean;

import java.io.Serializable;

import letus179.com.biu.utils.PinYinUtils;

/**
 * Created by xfyin on 2017/10/14.
 * <p>
 * 封装联系人列表
 */

public class Contacts implements Serializable {
    private String name;
    private int imageId;
    private String pinYin;
    private String headerWord;
    private boolean isChecked;

    public Contacts() {
    }

    public Contacts(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
        pinYin = PinYinUtils.getPinyin(name);
        headerWord = pinYin.substring(0, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPinYin() {
        return pinYin;
    }

    public String getHeaderWord() {
        return headerWord;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
