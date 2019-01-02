package com.orange.controller;

import com.orange.pojo.Users;
import com.orange.pojo.dto.UsersDto;
import com.orange.service.UserService;
import com.orange.utils.MD5Utils;
import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import com.orange.utils.auth.AuthToken;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import com.orange.utils.redis.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value="用户注册登录接口",tags={"注册和登录的controller"})
public class RegisterandLoginController {

    @Resource
    private UserService userService;
    @Resource
    private RedisOperator redis;

    @ApiOperation(value="用户注册",notes="根据传入的用户注册参数进行校验通过后插入到数据库\n" +
            "需要的参数为{username,password}",httpMethod = "POST")
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Result<Users> regist(@RequestBody Users user) throws Exception {
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            throw new MyException(ResultEnum.USER_ERROR);
        }
        boolean usernameIsExist = userService.UsernameIsExist(user.getUsername());
        if(!usernameIsExist){
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            user.setFollowCounts(0);
            userService.saveUser(user);
        }else{
            throw new MyException(ResultEnum.REPEAT_ERROR);
        }
        user.setPassword("");
        UsersDto userDto = userService.setUserToken(user);
        return ResultUtil.success(userDto);
    }

    @ApiOperation(value="用户登录",notes="用户登录校验\n"+"需要的参数为{username,password}",httpMethod = "POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result<UsersDto> login(@RequestBody Users user) throws Exception {
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            throw new MyException(ResultEnum.USER_ERROR);
        }
        String password = MD5Utils.getMD5Str(user.getPassword());
        Users userResult= userService.UserIsExist(user.getUsername(),password);
        if(userResult != null){
            userResult.setPassword("");
            UsersDto userDto = userService.setUserToken(userResult);
            return ResultUtil.success(userDto);
        }else{
            throw new MyException(ResultEnum.USER_NOT_EXIST);
        }
    }

    @ApiOperation(value="测试token")
    @RequestMapping(value="/test",method = RequestMethod.GET)
    @AuthToken
    public Result<Users> test(){
        Users user = new Users();
        user.setUsername("success");
        return ResultUtil.success(user);
    }

    @ApiOperation(value="用户注销",notes="用户注销的接口")
    @ApiImplicitParam(name="userId",value="用户id",required=true,dataType="String",paramType ="query" )
    @PostMapping("/logout")
    @AuthToken
    public Result<Users> logout(String userId){
        redis.del(userId);
        return ResultUtil.success();
    }
}
