package com.cyy.mvpsimple;

import com.fire.annotation.Column;
import com.fire.annotation.Table;

/**
 * Created by study on 17/6/22.
 *
 * message
 */

@Table(name = "message")
public class Message {

    public Message(String message){
        this.message = message;
    }

    public Message(){}

    @Column(name = "msgId" , isAuto = true , isId = true)
    public int msgId;

    @Column(name = "message")
    public String message;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
