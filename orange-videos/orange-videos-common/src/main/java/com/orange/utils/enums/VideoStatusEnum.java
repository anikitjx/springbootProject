package com.orange.utils.enums;

public enum VideoStatusEnum {
    SUCCESS(1), //发布成功
    FORBID(0); //违反规则禁止播放

    private int value;
    VideoStatusEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
