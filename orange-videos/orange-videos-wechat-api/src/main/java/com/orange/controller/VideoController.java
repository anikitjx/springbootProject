package com.orange.controller;

import com.orange.pojo.Bgm;
import com.orange.pojo.UsersLikeVideos;
import com.orange.pojo.Videos;
import com.orange.service.BgmService;
import com.orange.service.UserLikeVideoService;
import com.orange.service.VideoService;
import com.orange.utils.*;
import com.orange.utils.auth.AuthToken;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.enums.VideoStatusEnum;
import com.orange.utils.exception.MyException;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(value = "视频业务接口",tags = "视频业务的controller")
@RequestMapping("/video")
public class VideoController {
    @Resource
    BgmService bgmService;

    @Resource
    VideoService videoService;

    @Resource
    UserLikeVideoService userLikeVideoService;

    @ApiOperation(value="上传视频",notes = "上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true, dataType="String",
                    paramType = "form"),
            @ApiImplicitParam(name="bgmId",value="bgmid",required=false, dataType="String",
                    paramType = "form"),
            @ApiImplicitParam(name="videoSeconds",value="bgm播放长度",required=true, dataType="double",
                    paramType = "form"),
            @ApiImplicitParam(name="videoWidth",value="视频宽度",required=true, dataType="int",
                    paramType = "form"),
            @ApiImplicitParam(name="videoHeight",value="视频高度",required=true, dataType="int",
                    paramType = "form"),
            @ApiImplicitParam(name="desc",value="视频描述",required=false, dataType="String",
                    paramType = "form")
    })
    @PostMapping(value="/upload")
    @AuthToken
    public Result<String> upload(String userId,String bgmId,double videoSeconds,
                                 int videoWidth,int videoHeight,String desc,
                                 @RequestParam("file") MultipartFile multiFile) throws Exception{
        if (StringUtils.isBlank(userId)){
            throw new MyException(ResultEnum.USERID_NOT_EXIST);
        }
        //文件保存空间
        String fileSpace = "C:/orange-videos";
        String bgmSpace = "C:/orangebgm";
        //保存到数据库的相对路径
        String uploadPath = "/"+userId+"/video";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalVideoPath = null;
        String coverUploadPath = null;
        try {
            if (multiFile != null) {
                String fileName = multiFile.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    finalVideoPath = fileSpace + uploadPath + "/" + fileName;
                    String fileNamePrefix = fileName.substring(0,fileName.lastIndexOf("."));
                    String fileNameSuffix = fileNamePrefix.substring(fileNamePrefix.lastIndexOf(".")+1);
                    coverUploadPath = uploadPath +"/"+fileNameSuffix+".jpg";
                    File file = new File(finalVideoPath);
                    if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
                        file.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    inputStream = multiFile.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }else{
                throw new MyException(ResultEnum.File_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ResultEnum.File_ERROR);
        }finally {
            if(fileOutputStream !=null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        //ffmpeg合成视频音频
        String audio = null;
        if(StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.findBgmById(bgmId);
            audio= bgmSpace + bgm.getPath();
        }
        String ffmpegEXE = "C:/ffmpeg/bin/ffmpeg.exe";
        MergeVideo mergeVideo = new MergeVideo(ffmpegEXE);
        String videoOutPutName = UUID.randomUUID().toString()+".mp4";
        String videoUploadPath = uploadPath+"/" + videoOutPutName;
        String newVideoPath = fileSpace + videoUploadPath;
        mergeVideo.convertor(finalVideoPath,audio,newVideoPath);
        //ffmpeg截图
        GetVideoImage getVideoImage = new GetVideoImage(ffmpegEXE);
        getVideoImage.getCover(finalVideoPath, fileSpace+coverUploadPath);
        //保存视频信息到数据库
        Videos video = new Videos();
        String videoId = UUID.randomUUID().toString();
        video.setId(videoId);
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoDesc(desc);
        video.setVideoWidth(videoWidth);
        video.setVideoHeight(videoHeight);
        video.setVideoSeconds((float)videoSeconds);
        video.setVideoPath(videoUploadPath);
        video.setCoverPath(coverUploadPath);
        video.setStatus(VideoStatusEnum.SUCCESS.getValue());
        video.setCreateTime(new Date());
        videoService.saveVideo(video);
        return ResultUtil.success();
    }

    @PostMapping(value = "/showAllVideos")
    public Result showAllVideos(@RequestBody Videos video,Integer isSaveRecord,Integer page,Integer pageSize){
        if(page == null){
            page = 1;
        }
        if(pageSize == null) {
            pageSize = 5;
        }
        PagedResult pagedResult = videoService.getAllVideos(video,isSaveRecord,page,pageSize);
        return ResultUtil.success(pagedResult);
    }

    @GetMapping(value = "/hotSearch")
    public Result hotSearch(){
        return ResultUtil.success(videoService.getHotSearch());
    }

    @PostMapping(value="/showMyLikeVideos")
    public Result showMyLikeVideos(String userId,Integer page,Integer pageSize){
        if(StringUtils.isBlank(userId)){
            throw new MyException(ResultEnum.USERID_NOT_EXIST);
        }
        if(page == null) {
            page = 1;
        }
        if(pageSize == null){
            pageSize = 12;
        }
        PagedResult pagedResult = videoService.getMyLikeVideos(userId,page,pageSize);
        return ResultUtil.success(pagedResult);
    }
}
