package com.orange.controller;

import com.orange.pojo.UsersFans;
import com.orange.service.UserFanService;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import com.orange.utils.auth.AuthToken;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "用户关注接口",tags = {"用户关注的controller"})
@RequestMapping("/userFan")
public class UserFanController {
    @Resource
    UserFanService userFanService;

    @PostMapping("/followHim")
    @AuthToken
    public Result followHim(@RequestBody UsersFans usersFans){
        String userId = usersFans.getUserId();
        String fanId = usersFans.getFanId();
        userFanService.followHim(userId,fanId);
        return ResultUtil.success();
    }

    @PostMapping("/unFollowHim")
    @AuthToken
    public Result unFollowHim(@RequestBody UsersFans usersFans){
        String userId = usersFans.getUserId();
        String fanId = usersFans.getFanId();
        userFanService.unFollowHim(userId,fanId);
        return ResultUtil.success();
    }


}
