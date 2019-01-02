package com.orange.controller;

import com.orange.mapper.VideosMapper;
import com.orange.pojo.Users;
import com.orange.pojo.UsersFans;
import com.orange.pojo.UsersLikeVideos;
import com.orange.pojo.Videos;
import com.orange.pojo.dto.FanPublisher;
import com.orange.pojo.dto.UsersDto;
import com.orange.pojo.dto.VideoPublisher;
import com.orange.service.UserFanService;
import com.orange.service.UserLikeVideoService;
import com.orange.service.UserService;
import com.orange.service.VideoService;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import com.orange.utils.auth.AuthToken;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@Api(value = "用户相关接口",tags = {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserLikeVideoService userLikeVideoService;
    @Resource
    private UserFanService userFanService;
    @Resource
    private VideoService videoService;

    @ApiOperation(value="头像上传",notes="头像上传接口")
    @ApiImplicitParam(name="userId",value="用户id",required=true,dataType = "String",paramType = "query")
    @PostMapping("/uploadAvatar")
    @AuthToken
    public Result<String> uploadAvatar(String userId, @RequestParam("file")MultipartFile[] files) throws Exception{
        if (StringUtils.isBlank(userId)){
            throw new MyException(ResultEnum.USERID_NOT_EXIST);
        }
        //文件保存空间
        String fileSpace = "C:/orange-videos";
        //保存到数据库的相对路径
        String uploadPath = "/"+userId+"/avatar";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    String finalFacePath = fileSpace + uploadPath + "/" + fileName;
                    uploadPath += ("/" + fileName);
                    File file = new File(finalFacePath);
                    if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
                        file.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    inputStream = files[0].getInputStream();
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

        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPath);
        userService.updateUser(user);
        return ResultUtil.success(uploadPath);
    }

    @GetMapping("/findUser")
    @ApiOperation(value = "查询用户",notes = "查询用户的接口")
    @ApiImplicitParam(name="userId",value="用户id",required=true,dataType = "String",paramType = "query")
    @AuthToken
    public Result<Users> findUser(String userId){
        if(StringUtils.isBlank(userId)){
            throw new MyException(ResultEnum.USERID_NOT_EXIST);
        }
        Users user = userService.findUserByUserId(userId);
        UsersDto usersDto = new UsersDto();
        BeanUtils.copyProperties(user,usersDto);
        return ResultUtil.success(usersDto);
    }

    @PostMapping("/publisher")
    @ApiOperation(value="查询当前用户和视频点赞关系及视频发布者信息",notes = "查询当前用户和视频点赞关系及视频发布者信息的接口")
    public Result publisher(@RequestBody UsersLikeVideos usersLikeVideos, String publishUserId){
        if(StringUtils.isBlank(publishUserId)){
            throw new MyException(ResultEnum.USERID_NOT_EXIST);
        }
        //查询视频发布者的信息
        Users userInfo = userService.findUserByUserId(publishUserId);
        UsersDto publisher = new UsersDto();
        BeanUtils.copyProperties(userInfo,publisher);

        if(StringUtils.isBlank(usersLikeVideos.getUserId()) || StringUtils.isBlank(usersLikeVideos.getVideoId())){
            throw new MyException(ResultEnum.USERANDVIDEOID_NOT_EXIST);
        }
        //查询当前用户和视频点赞关系
        String loginUserId = usersLikeVideos.getUserId();
        String videoId = usersLikeVideos.getVideoId();
        boolean userLikeVideo = userLikeVideoService.userLikeVideo(loginUserId,videoId);
        //查询视频信息
        Videos video = videoService.findVideo(videoId);

        VideoPublisher videoPublisher = new VideoPublisher();
        videoPublisher.setPublisher(publisher);
        videoPublisher.setUserLikeVideo(userLikeVideo);
        videoPublisher.setVideo(video);
        return ResultUtil.success(videoPublisher);
    }
    @PostMapping("/userFanRel")
    @ApiOperation(value="查询当前用户和视频发布者关住关系及视频发布者信息",notes = "查询当前用户和视频发布者关住关系及视频发布者信息的接口")
    public Result userFanRel(@RequestBody UsersFans usersFans){
        if(StringUtils.isBlank(usersFans.getUserId()) || StringUtils.isBlank(usersFans.getFanId())){
            throw new MyException(ResultEnum.USERANDFANID_NOT_EXIST);
        }
        //查询视频发布者的信息
        Users userInfo = userService.findUserByUserId(usersFans.getUserId());
        UsersDto publisher = new UsersDto();
        BeanUtils.copyProperties(userInfo,publisher);
        //查询视频发布者和当前用户关注关系
        String userId = usersFans.getUserId();
        String fanId = usersFans.getFanId();
        boolean userFan = userFanService.UserFan(userId,fanId);

        FanPublisher fanPublisher = new FanPublisher();
        fanPublisher.setPublisher(publisher);
        fanPublisher.setUserFan(userFan);
        return ResultUtil.success(fanPublisher);
    }
}
