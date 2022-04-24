package com.bysj.stsys.controller;

import com.bysj.stsys.Utils.GsonUtils;
import com.bysj.stsys.model.Rootuser;
import com.bysj.stsys.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class loginController {
    @Autowired
    RootService rootService=null;
    @ResponseBody
    @RequestMapping("./")
    public String filter(){
        return "null";
    }
    @RequestMapping("/")
    public String welcome(){
        return "User/userindex";
    }
    @RequestMapping("/goroot")
    public String gorootpage(){
        return "Root/index";
    }
    @RequestMapping("/gologinpage")
    public String gologinpage(){
        return "Root/login";
    }
    @RequestMapping("/login")
    /**
     * 普通登录
     */
    public String login(String username, HttpSession session,String password, Model model){
        System.out.println(username);
        System.out.println(password);
        Rootuser login = rootService.login(username, password);
        if(login==null){
            model.addAttribute("login_msg","用户名或密码错误");
            return "Root/login";
        }else {
            session.setAttribute("rootinf",login);
            return "Root/index";
        }
    }
    @RequestMapping("/searchface")
    @ResponseBody
    /**
     * 刷脸登录
     */
    public String searchface(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, Model model, HttpServletRequest request) throws IOException {
        System.out.println("wojinrule");
        Map<String, Object> searchface = rootService.searchface(imagebast64);
        if(searchface==null||searchface.get("user_id")==null){
//            System.out.println("我进来了");
            String flag="fail";
            String s = GsonUtils.toJson(flag);
            return s;
        }
        String user_id = searchface.get("user_id").toString();
        String score=searchface.get("score").toString().substring(0,2);
        int i = Integer.parseInt(score);
        if(i>80){
            model.addAttribute("userinf",user_id);
            HttpSession session = request.getSession();
            session.setAttribute("userinf",user_id);
            Rootuser findroot = rootService.findroot(user_id);
            session.setAttribute("rootinf",findroot);

        }
        String s = GsonUtils.toJson(searchface);
        return s;
    }

    @RequestMapping("/jumpGetface")
    public String getface(){
        return "Root/getface";
    }
    @RequestMapping("/facesucceed")
    public String Faceloginsucceed(){

        return "Root/index";
    }
}

