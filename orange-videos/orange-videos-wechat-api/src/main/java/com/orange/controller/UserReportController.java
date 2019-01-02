package com.orange.controller;

import com.orange.pojo.UsersReport;
import com.orange.service.UserReportService;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import com.orange.utils.auth.AuthToken;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "用户举报接口",tags = {"用户举报的controller"})
@RequestMapping("/userReport")
public class UserReportController {
    @Resource
    UserReportService userReportService;

    @PostMapping("/reportUser")
    @AuthToken
    public Result reportUser(@RequestBody UsersReport usersReport){
        userReportService.reportUser(usersReport);
        String reportmsg = "举报成功!";
        return ResultUtil.success(reportmsg);
    }
}
