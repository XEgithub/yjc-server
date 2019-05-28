package com.dk.controller.wechat;

import com.dk.config.Constant;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wx")
public class HomeController {

    @GetMapping("home")
    String home(){
        return "wechat/index";
    }

}
