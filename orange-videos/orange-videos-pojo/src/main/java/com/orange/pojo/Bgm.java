package com.orange.pojo;

import javax.persistence.Id;

public class Bgm {
    @Id
    private String id;

    /**
     * 歌手
     */
    private String author;

    /**
     * 歌名
     */
    private String name;

    private String path;

    private String image;

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
     * 获取歌手
     *
     * @return author - 歌手
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置歌手
     *
     * @param author 歌手
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取歌名
     *
     * @return name - 歌名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置歌名
     *
     * @param name 歌名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }
}