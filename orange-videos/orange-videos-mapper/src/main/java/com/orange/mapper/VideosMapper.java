package com.orange.mapper;

import com.orange.pojo.Videos;
import com.orange.utils.MyMapper;

import java.util.List;

public interface VideosMapper extends MyMapper<Videos> {
    Videos findAllVideos(Videos video);
    void saveVideo(Videos video);
    void updateVideo(Videos video);
}