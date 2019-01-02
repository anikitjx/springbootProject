package com.orange.pojo.dto;

import com.orange.pojo.Videos;

public class VideoPublisher {
    private UsersDto publisher;
    private Videos video;
    private boolean userLikeVideo;

    public UsersDto getPublisher() {
        return publisher;
    }

    public void setPublisher(UsersDto publisher) {
        this.publisher = publisher;
    }

    public boolean isUserLikeVideo() {
        return userLikeVideo;
    }

    public void setUserLikeVideo(boolean userLikeVideo) {
        this.userLikeVideo = userLikeVideo;
    }

    public Videos getVideo() {
        return video;
    }

    public void setVideo(Videos video) {
        this.video = video;
    }
}
