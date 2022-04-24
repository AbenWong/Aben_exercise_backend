package com.bysj.stsys.controller;

import com.bysj.stsys.model.Leavemessage;
import com.bysj.stsys.model.Stpersoninf;
import com.bysj.stsys.model.User;
import com.bysj.stsys.service.UserManagerService;
import com.bysj.stsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userLoginController {
    @Autowired
    UserService userService=null;
    @Autowired
    UserManagerService userManagerService=null;
    @RequestMapping("/login")
    public String userLogin(HttpSession session){
        session.removeAttribute("userinf");
        return "User/userlogin";
    }
    @RequestMapping("/psLogin")
    public String pslogin(String username, String password, Model model,HttpSession session){
        User userlogin = userService.userlogin(username, password);
        System.out.println(userlogin);
        if(userlogin!=null){
            session.setAttribute("userinf",userlogin);
            List<Stpersoninf> stpersoninfs = userManagerService.searchIsStmanager(userlogin.getUserStid());
            List<Leavemessage> leavemessages = userService.findmymessageshowMyleaveessage(userlogin.getUserStid());
            model.addAttribute("leaveList",leavemessages);
            if(stpersoninfs.size()>0){
                session.setAttribute("IsSz","1");
            }
            else {
                session.setAttribute("IsSz","2");

            }
            System.out.println(userlogin);
            return "User/userMtpage";
        }else {
            model.addAttribute("login_msg","用户名或密码错误");
            return "User/userlogin";
        }
    }

    /**
     * 发送短信
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTelcode")
    public String getTelcode(String telNum, HttpSession session){
        int num=(int)((Math.random()*9+1)*1000);
        String code=String.valueOf(num);
        String telcode = userService.getTelcode(telNum,code);
        System.out.println("错误码"+telcode);
        if("isv.BUSINESS_LIMIT_CONTROL".equals(telcode)){
            return "2";
        }else {
            session.setAttribute("telcode",code);
            return "1";
        }

    }
    /**
     * 短信登录
     */
    @ResponseBody
    @RequestMapping("/telLogin")
    public String telLogin(String telCode,String telNum,Model model, HttpSession session){
        System.out.println("我填写的验证码"+telCode);
        System.out.println("session中的验证码"+session.getAttribute("telcode").toString());
        if(telCode.equals(session.getAttribute("telcode").toString())){
            User user = userService.loginBytel(telNum);
            if(user==null){
                return "0";
            }else {
                session.setAttribute("userinf",user);
                return "1";
            }
        }else {
            return "2";
        }

    }
    @RequestMapping("/gouserMtpage")
    public String gouserMtpage(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stpersoninfs = userManagerService.searchIsStmanager(userinf.getUserStid());
        List<Leavemessage> leavemessages = userService.findmymessageshowMyleaveessage(userinf.getUserStid());
        model.addAttribute("leaveList",leavemessages);
        if(stpersoninfs.size()>0){
            session.setAttribute("IsSz","1");
        }
        else {
            session.setAttribute("IsSz","2");

        }
        return "User/userMtpage";
    }
}
