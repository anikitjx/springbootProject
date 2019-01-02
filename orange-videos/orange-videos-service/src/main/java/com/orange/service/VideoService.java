package com.orange.service;

import com.orange.pojo.UsersLikeVideos;
import com.orange.pojo.Videos;
import com.orange.utils.PagedResult;

import java.util.List;

public interface VideoService {
    /*
     *保存视频
     */
    void saveVideo(Videos video);
    /*
     *分页查询视频列表
     */
    PagedResult getAllVideos(Videos video,Integer isSaveRecord,Integer page,Integer pageSize);
    /*
     *分页查询我收藏的视频
     */
    PagedResult getMyLikeVideos(String userId,Integer page,Integer pageSize);
    /*
     *热搜词列表
     */
    List<String> getHotSearch();
    /*
     *根据id查找视频
     */
    Videos findVideo(String videoId);
}
