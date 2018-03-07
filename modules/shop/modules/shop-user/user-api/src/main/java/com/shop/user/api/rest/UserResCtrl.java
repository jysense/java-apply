package com.shop.user.api.rest;


import com.shop.user.api.model.UserInfo;
import com.shop.user.api.model.base.Rep;
import com.shop.user.api.model.base.Req;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "用户接口")
@RequestMapping(value="/user")
public interface UserResCtrl {

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @RequestMapping(value="/getUserInfo",method= RequestMethod.POST)
    public UserInfo getUserInfo(@RequestParam(value = "userId",required = false) Long userId);

    @ApiOperation(value = "获取用户信息2", notes = "获取用户信息2")
    @RequestMapping(value="/getUserInfo2",method= RequestMethod.GET)
    public Rep<UserInfo> getUserInfo2(@RequestParam(value = "userId",required = false) Long userId);

    @ApiOperation(value = "获取用户信息3", notes = "获取用户信息3")
    @RequestMapping(value="/queryUserInfo3",method= RequestMethod.POST)
    public Rep<UserInfo> queryUserInfo3(@RequestBody @Validated Req<Long> req, Errors errors);

}