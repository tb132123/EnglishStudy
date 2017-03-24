package app.leiyu.com.english.utill;

import cn.jpush.im.android.api.model.Message;

/**
 * Created by leiyu on 17/3/16.
 */

public class Chat {
    private String value;
    private int type;
    private Message msg;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
