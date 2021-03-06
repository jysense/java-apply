package com.micro.course.controller;

import com.micro.course.model.vo.UserCourseQueryVo;
import com.micro.course.model.vo.UserCourseVo;
import com.micro.course.service.UserCourseService;
import com.model.base.PageVo;
import com.model.base.Req;
import com.model.base.Resp;
import com.model.base.RespHeader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api("用户课程接口")
@RequestMapping("/userCourse")
@RestController
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    @ApiOperation(value = "分页查询某用户的课程", notes = "分页查询某用户的课程")
    @RequestMapping(value = "/queryPageForUser", method = RequestMethod.POST)
    public Resp<PageVo<UserCourseVo>> queryPageForUser(@RequestBody Req<UserCourseQueryVo> req) {
        Resp<PageVo<UserCourseVo>> resp = new Resp<PageVo<UserCourseVo>>();
        UserCourseQueryVo queryVo = req.getData();
        Long userId = queryVo.getUserId();
        if(userId==null) {
            resp.getHeader().setCode(RespHeader.FAIL);
            resp.getHeader().setMsg("参数校验错误");
            return resp;
        }
        queryVo.genPage();
        PageVo<UserCourseVo> page = userCourseService.queryPage(queryVo);
        resp.setData(page);
        return resp;
    }

    @ApiOperation(value = "分页查询所有用户课程", notes = "分页查询所有用户课程")
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public Resp<PageVo<UserCourseVo>> queryPage(@RequestBody Req<UserCourseQueryVo> req) {
        Resp<PageVo<UserCourseVo>> resp = new Resp<PageVo<UserCourseVo>>();
        UserCourseQueryVo queryVo = req.getData();
        queryVo.genPage();
        PageVo<UserCourseVo> page = userCourseService.queryPage(queryVo);
        resp.setData(page);
        return resp;
    }

}
