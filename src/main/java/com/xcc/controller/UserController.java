package com.xcc.controller;

import com.xcc.common.R;
import com.xcc.domain.User;
import com.xcc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xcc
 * @version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody Map map, HttpServletRequest request){
        return userService.login(map,request);
    }

    @PostMapping("/sendMsg")
    public R sendMsg(@RequestBody User user,HttpServletRequest request){
        String phone = user.getPhone();
        String code = "1234";
        //将生成的手机号码和验证码保存到session中
//        request.getSession().setAttribute("phone",phone);
//        request.getSession().setAttribute("code",code);

        //将生成的手机号码和验证码缓存到redis中  5 分钟过期
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

        return R.success("发送验证码成功");
    }

    @PostMapping("/loginout")
    public R loginOut(HttpServletRequest request){
        return userService.loginOut(request);
    }
}
