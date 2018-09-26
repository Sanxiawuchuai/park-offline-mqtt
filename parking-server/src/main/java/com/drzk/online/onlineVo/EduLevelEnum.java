package com.drzk.online.onlineVo;

/**
 * 2018/9/21 cx
 */
public enum EduLevelEnum {
    OPEN_BRAKE(1, "博士"),
    CLOSE_BRAKE(2, "硕士"),
    CONNECTION(3, "学士"),
    DISCONNECTION(4, "本科"),
    RESTART(5, "大专"),
    RECONNECT(6, "高中"),
    MIDDLE_TEL(7, "中专"),
    OTHERS(8, "其他");

    private final int code;
    private final String desc;

    EduLevelEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EduLevelEnum get(int code){
        switch (code){
            case 1:
                return OPEN_BRAKE;
            case 2:
                return CLOSE_BRAKE;
            case 3:
                return CONNECTION;
            case 4:
                return DISCONNECTION;
            case 5:
                return RESTART;
            case 6:
                return RECONNECT;
            case 7:
                return MIDDLE_TEL;
            case 8:
                return OTHERS;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
