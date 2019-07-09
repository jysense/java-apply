package com.micro.user.controller;

import com.micro.user.annotation.AppLogin;
import com.micro.user.enums.LoginTypeEnum;
import com.micro.user.enums.UserStatuEnum;
import com.micro.user.model.pojo.User;
import com.micro.user.model.req.LoginReq;
import com.micro.user.model.vo.LoginVo;
import com.micro.user.service.LoginService;
import com.micro.user.service.SmsService;
import com.micro.user.service.UserService;
import com.micro.user.util.PasswdUtil;
import com.shop.base.model.Req;
import com.shop.base.model.Resp;
import com.util.regex.RegexUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value="登录接口",description="登录接口")
@RestController
public class LoginController {

    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @Resource
    private SmsService smsService;

    @PostMapping("/login")
    public Resp<LoginVo> login(@Validated @RequestBody Req<LoginReq> req, Errors errors) {
        LoginReq loginReq = req.getData();
        String account = loginReq.getAccount();
        Integer loginType = loginReq.getLoginType();

        logger.info("LoginController login start,account={},loginType={}", account,loginType);
        LoginTypeEnum loginEnum= LoginTypeEnum.getLoginType(loginType);
        if(loginEnum==null) {
            loginEnum=LoginTypeEnum.ACCOUNT;//默认账号密码登录
        }
        Resp<LoginVo> resp =null;
        switch (loginEnum) {
            case ACCOUNT:
                resp=this.loginByAccount(loginReq);
                break;
            case MOBILE_CODE:
                resp=this.loginByMobileCode(loginReq);
                break;
            case THIRD_ACCOUNT:
                resp=this.loginByThirdAccount(loginReq);
                break;
            default:
                logger.info("LoginServiceImpl login fail,不支持此登录方式,loginType={}.account={}", loginType, account);
                return Resp.error("不支持此登录方式");
        }
        return  resp;
    }

    //账号密码登录
    private Resp<LoginVo> loginByAccount(LoginReq loginReq ) {
        String mobile = loginReq.getAccount();
        if (!RegexUtil.isCellPhone(mobile)) {
            return Resp.error("手机号不正确");
        }
        String passwd = loginReq.getPasswd();
        if(StringUtils.isBlank(passwd)) {
            return Resp.error("密码不正确");
        }
        User user = userService.queryByMobile(mobile);
        if(user==null) {
            return Resp.error("用户不存在");
        }
        if(UserStatuEnum.FORBID.getValue().intValue()==user.getStatu()) {
            return Resp.error("用户禁用中");
        }

        String orglPwd = user.getPasswd();
        String salt = user.getSalt();
        String inPwd = PasswdUtil.pwdEncrypt(passwd, salt);
        if (StringUtils.isBlank(orglPwd) || !orglPwd.equals(inPwd)) {
            return Resp.error("密码不正确");
        }
        LoginVo loginVo = loginService.login(user,loginReq);
        Resp<LoginVo> resp = Resp.success(loginVo);
        return resp;
    }

    //手机验证码登录
    private Resp<LoginVo> loginByMobileCode(LoginReq loginReq ) {
        String mobile = loginReq.getAccount();
        if (!RegexUtil.isCellPhone(mobile)) {
            return Resp.error("手机号不正确");
        }
        String code = loginReq.getPasswd();
        if(StringUtils.isBlank(code)) {
            return Resp.error("验证码不正确");
        }
        User user = userService.queryByMobile(mobile);
        if(user==null) {
            return Resp.error("用户不存在");
        }
        if(UserStatuEnum.FORBID.getValue().intValue()==user.getStatu()) {
            return Resp.error("用户禁用中");
        }
        boolean codeCheck = smsService.checkCode(mobile,code);
        if(!codeCheck) {
            return Resp.error("验证码不正确");
        }
        LoginVo loginVo = loginService.login(user,loginReq);
        Resp<LoginVo> resp = Resp.success(loginVo);
        return resp;
    }

    //第三方账号登录
    private Resp<LoginVo> loginByThirdAccount(LoginReq loginReq ) {
        String thirdAccount = loginReq.getAccount();
        if (StringUtils.isBlank(thirdAccount)) {
            return Resp.error("第三方账号不正确");
        }
        Integer thirdType= loginReq.getThirdType();
        User user = userService.queryByThirdAccount(thirdAccount,thirdType);
        if(user==null) {
            return Resp.error("用户不存在");
        }
        if(UserStatuEnum.FORBID.getValue().intValue()==user.getStatu()) {
            return Resp.error("用户禁用中");
        }
        LoginVo loginVo = loginService.login(user,loginReq);
        Resp<LoginVo> resp = Resp.success(loginVo);
        return resp;
    }

    @PostMapping("/logout")
    @AppLogin
    public Resp<Void> logout(@RequestBody Req<Void> req) {
        logger.info("LoginController logout start...............");
        Resp<Void> resp = new Resp<>();
        try {
        } catch (Exception e) {
        }
        return  resp;
    }

    @PostMapping("/checkLogin")
    public Resp<Void> checkLogin(@RequestBody @Valid Req<Void> req) {
        logger.info("LoginController checkLogin start...............");
        Resp<Void> resp = new Resp<>();
        try {
        } catch (Exception e) {
        }
        return  resp;
    }
}
