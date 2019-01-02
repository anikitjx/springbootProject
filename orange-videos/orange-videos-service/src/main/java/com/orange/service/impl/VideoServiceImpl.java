package com.orange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orange.mapper.SearchRecordsMapper;
import com.orange.mapper.VideosDtoMapper;
import com.orange.mapper.VideosMapper;
import com.orange.pojo.SearchRecords;
import com.orange.pojo.Videos;
import com.orange.pojo.dto.VideosDto;
import com.orange.service.VideoService;
import com.orange.utils.PagedResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    VideosMapper videosMapper;
    @Resource
    VideosDtoMapper videosDtoMapper;
    @Resource
    SearchRecordsMapper searchRecordsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveVideo(Videos video) {
        videosMapper.saveVideo(video);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize) {
        String desc = video.getVideoDesc();
        String userId = video.getUserId();
        if(isSaveRecord != null && isSaveRecord == 1){
            SearchRecords record = new SearchRecords();
            record.setId(UUID.randomUUID().toString());
            record.setContent(desc);
            searchRecordsMapper.saveRecord(record);
        }
        PageHelper.startPage(page,pageSize);
        List<VideosDto> list = videosDtoMapper.getAllVideos(desc,userId);
        PageInfo<VideosDto> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setPages(pageList.getPages());
        pagedResult.setTotal(pageList.getTotal());
        pagedResult.setRows(list);
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getMyLikeVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<VideosDto> list = videosDtoMapper.getMyLikeVideos(userId);
        PageInfo<VideosDto> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setPages(pageList.getPages());
        pagedResult.setTotal(pageList.getTotal());
        pagedResult.setRows(list);
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> getHotSearch() {
        return searchRecordsMapper.selectHotSearch();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Videos findVideo(String videoId) {
        Videos videos = new Videos();
        videos.setId(videoId);
        Videos result = videosMapper.findAllVideos(videos);
        return result;
    }
}
