package com.orange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;

@ApiModel(value="用户对象",description = "用户对象")
public class Users {

    @ApiModelProperty(hidden=true)
    @Id
    private String id;

    @ApiModelProperty(value="用户名",name="username",example="user1",required=true)
    private String username;

    @ApiModelProperty(value="密码",name="password",example="123456",required=true)
    private String password;

    @Column(name = "user_mobile")
    private String userMobile;

    @Column(name = "face_image")
    private String faceImage;

    @ApiModelProperty(hidden=true)
    private String nickname;

    /**
     * 粉丝数
     */
    @ApiModelProperty(hidden=true)
    @Column(name = "fans_counts")
    private Integer fansCounts;

    /**
     * 关注数
     */
    @ApiModelProperty(hidden=true)
    @Column(name = "follow_counts")
    private Integer followCounts;

    /**
     * 受到的点赞总数
     */
    @ApiModelProperty(hidden=true)
    @Column(name = "receive_like_counts")
    private Integer receiveLikeCounts;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return user_mobile
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * @return face_image
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * @param faceImage
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取粉丝数
     *
     * @return fans_counts - 粉丝数
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * 设置粉丝数
     *
     * @param fansCounts 粉丝数
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * 获取关注数
     *
     * @return follow_counts - 关注数
     */
    public Integer getFollowCounts() {
        return followCounts;
    }

    /**
     * 设置关注数
     *
     * @param followCounts 关注数
     */
    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    /**
     * 获取受到的点赞总数
     *
     * @return receive_like_counts - 受到的点赞总数
     */
    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    /**
     * 设置受到的点赞总数
     *
     * @param receiveLikeCounts 受到的点赞总数
     */
    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}