package com.orange.controller;

import com.orange.pojo.Bgm;
import com.orange.service.BgmService;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value="bgm业务接口",tags = "bgm业务的controller")
@RequestMapping("/bgm")
public class BgmController {
    @Resource
    private BgmService bgmService;

    @ApiOperation(value="获取bgm列表",notes="获取bgm列表的接口")
    @PostMapping("/list")
    public Result<List<Bgm>> list(){
        return ResultUtil.success(bgmService.findBgmList());
    }
}
