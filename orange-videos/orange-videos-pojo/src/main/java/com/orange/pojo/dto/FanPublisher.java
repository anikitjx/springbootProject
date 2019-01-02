package com.orange.pojo.dto;

public class FanPublisher {
    private UsersDto publisher;
    private boolean userFan;

    public UsersDto getPublisher() {
        return publisher;
    }

    public void setPublisher(UsersDto publisher) {
        this.publisher = publisher;
    }

    public boolean isUserFan() {
        return userFan;
    }

    public void setUserFan(boolean userFan) {
        this.userFan = userFan;
    }
}
