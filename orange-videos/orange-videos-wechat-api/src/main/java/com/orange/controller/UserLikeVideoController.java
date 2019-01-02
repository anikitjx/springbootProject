package com.orange.controller;

import com.orange.pojo.UsersLikeVideos;
import com.orange.service.UserLikeVideoService;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import com.orange.utils.auth.AuthToken;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "用户点赞接口",tags = {"用户点赞的controller"})
@RequestMapping("/userLikeVideo")
public class UserLikeVideoController {
    @Resource
    UserLikeVideoService userLikeVideoService;

    @PostMapping(value = "/likeVideo")
    @AuthToken
    public Result likeVideo(@RequestBody UsersLikeVideos usersLikeVideos){
        String userId = usersLikeVideos.getUserId();
        String videoId = usersLikeVideos.getVideoId();
        userLikeVideoService.likeVideo(userId,videoId);
        return ResultUtil.success();
    }

    @PostMapping(value = "/unLikeVideo")
    @AuthToken
    public Result unLikeVideo(@RequestBody UsersLikeVideos usersLikeVideos){
        String userId = usersLikeVideos.getUserId();
        String videoId = usersLikeVideos.getVideoId();
        userLikeVideoService.unLikeVideo(userId,videoId);
        return ResultUtil.success();
    }

}
