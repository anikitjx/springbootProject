package com.orange.mapper;

import com.orange.pojo.Videos;
import com.orange.pojo.dto.VideosDto;
import com.orange.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosDtoMapper extends MyMapper<Videos> {
    List<VideosDto> getAllVideos(@Param(value="videoDesc")String videoDesc,@Param(value="userId")String userId);
    List<VideosDto> getMyLikeVideos(@Param(value="userId")String userId);
    void addVideoLikeCount(@Param(value="videoId")String videoId);
    void reduceVideoLikeCount(@Param(value="videoId")String videoId);
}