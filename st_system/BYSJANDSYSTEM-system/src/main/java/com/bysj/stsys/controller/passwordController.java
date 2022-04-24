package com.bysj.stsys.controller;

import com.bysj.stsys.Utils.MD5utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/password")
public class passwordController {
    @ResponseBody
    @RequestMapping("/checkpaypassword")
    public String checkPassword(String password, HttpSession session){

        String inputpassword = MD5utils.MD5_SHA(password, "MD5");
        String string = session.getAttribute("md5password").toString();
        System.out.println(password);
        System.out.println(string);
        if(inputpassword.equals(string)){
            return "1";
        }else {
            return "2";
        }

    }
}
