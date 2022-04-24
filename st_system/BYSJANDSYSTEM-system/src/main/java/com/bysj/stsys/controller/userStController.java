package com.bysj.stsys.controller;

import com.bysj.stsys.Utils.GsonUtils;
import com.bysj.stsys.model.*;
import com.bysj.stsys.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.bysj.stsys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/userSt")
public class userStController {
    @Autowired
    UserService userService=null;
    @RequestMapping("/showMyinf")
    public String showMyinf(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        User userinf1 = userService.findUserinf(userinf.getUserStid());
        model.addAttribute("stinf",userinf1);
        System.out.println(userinf1);
        return "User/userMyinf";
    }

    /**
     * 修改手机号发送短信
     * @param telNum
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeTelSendCode")
    public String changeTelSendCode(String telNum,HttpSession session){
        int num=(int)((Math.random()*9+1)*1000);
        String code=String.valueOf(num);
        String telcode = userService.getTelcode(telNum,code);
        System.out.println("错误码"+telcode);
        if("isv.BUSINESS_LIMIT_CONTROL".equals(telcode)){
            return "2";
        }else {
            session.setAttribute("changetelcode",code);
            return "1";
        }
    }

    /**
     * 对比一下输入的验证码
     * @param code
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeTel")
    public String changeTel(String code,HttpSession session){
        String sessionCode = session.getAttribute("changetelcode").toString();//查询session中的code
        if(code.equals(sessionCode)){
            return "1";
        }else {
            return "2";
        }
    }

    /**
     * 检查验证码
     * @param newTel
     * @param telCode
     * @param session
     * @return
     */
    @RequestMapping("/changeTelcheck")
    public String changeTelcheck(String newTel,String telCode,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Integer s = userService.changeTelbegin(newTel, userinf.getUserStid());
        return String.valueOf(s);
    }
    @ResponseBody
    @RequestMapping("/setNewTelsendCode")
    public String setNewTelsendCode(String newtel,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Integer s = userService.changeTelbegin(newtel, userinf.getUserStid());
        HashMap<String, Integer> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("msg",s);
        String json = GsonUtils.toJson(stringStringHashMap);
        return json;
    }
    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(String newPassword,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Integer integer = userService.changePassword(userinf.getUserStid(), newPassword);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("msg", integer);
        String msg = GsonUtils.toJson(stringIntegerHashMap);
        return msg;
    }
    @RequestMapping("/searchMySt")
    public String searchMySt(Model model,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        System.out.println(stlist);
        return "User/userJoinSt";
    }
    @RequestMapping("/userShowStinf")
    public String userShowStinf(String stKey,Model model){
        Stinf stinf = userService.finStinf(stKey);
        model.addAttribute("stPeopleNume",userService.StAllNumber(stKey));
        model.addAttribute("stinf",stinf);
        return "User/userShowStinf";
    }
    /**
     * 跳转到社团成员查看界面
     */
    @RequestMapping("/showMyJoinStperson")
    public String showMyJoinStperson(Model model,HttpSession session){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/userStpersonList";
    }
    /**
     * 查询社团成员的信息
     */
    @RequestMapping("/searchJoinStperson")
    public String searchJoinStperson(String Stkey,  Model model, HttpSession session,
                                     @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "5",value = "showNum") Integer showNum){
        if(Stkey!=null){
            session.setAttribute("Stkey",Stkey);
        }
        if(Stkey==null){
            Stkey=session.getAttribute("Stkey").toString();
        }
        PageHelper.startPage(pageNum,showNum);
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stpersoninfs = userService.searchStperson(Stkey,userinf.getUserStid());
        PageInfo<Stpersoninf> pageInfo = new PageInfo<>(stpersoninfs);
        model.addAttribute("pageinf",pageInfo);
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/userStpersonList";
    }
    /**
     * 给用户留言
     */
    @ResponseBody
    @RequestMapping("/addLeaveMessage")
    public String addLeaveMessage(String Receiverperson,String title,String message,HttpSession session){
        Leavemessage leavemessage = new Leavemessage();
        UUID uuid = UUID.randomUUID();
        User userinf = (User)session.getAttribute("userinf");
        leavemessage.setLmKey(uuid.toString().replaceAll("\\-", ""));
        leavemessage.setLmSendperson(userinf.getUserStid());
        leavemessage.setLmReceiverperson(Receiverperson);
        leavemessage.setLmTitle(title);
        leavemessage.setLmMessage(message);
        System.out.println(leavemessage);
        Integer result = userService.addLeaveMessage(leavemessage);
        HashMap<String, String> resultmap = new HashMap<>();
        resultmap.put("msg", result.toString());
        String resultJson = GsonUtils.toJson(resultmap);
        return resultJson;
    }
    /**
     * 跳转到公告信息查看界面
     */
    @RequestMapping("/showAccountmessage")
    public String showAccountmessage(Model model,HttpSession session){
        List<Announcement> rootAccounceMent = userService.findRootAccounceMent();
        model.addAttribute("rootAccounceMent",rootAccounceMent);
        User userinf = (User)session.getAttribute("userinf");
        List<Announcement> stannouncement = userService.findStannouncement(userinf.getUserStid());
        model.addAttribute("StAccounceMent",stannouncement);
        return "User/useraccount";
    }
    /**
     * 利用ajax查看完整的公告
     */
    @ResponseBody
    @RequestMapping("/showAllmessage")
    public String showAllmessage(String Atkey){
        String allMessage = userService.findAllMessage(Atkey);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("message",allMessage);
        String message = GsonUtils.toJson(stringStringHashMap);
        return message;
    }

    /**
     * 查询社团活动
     *
     */
    @RequestMapping("/showUserActive")
    public String showUserActive(Model model,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        List<Active> stactive = userService.findStactive(userinf.getUserStid());
//        System.out.println(stactive);
        model.addAttribute("stativeList",stactive);
        return "User/userActive";
    }
    /**
     * 查询社团活动详细信息
     */
    @RequestMapping("/showActiveInf")
    public String showActiveInf(Model model,String acKey,String statue){
        System.out.println(statue);
        System.out.println("状态");
        //返回目前有多少人参加了这个活动
        Integer JoinPeople = userService.findHowManyPeopleJoinActive(acKey);
        model.addAttribute("joinPeopleNum",JoinPeople);
//        System.out.println(JoinPeople);
        Active stactiveInf = userService.findStactiveInf(acKey);
        model.addAttribute("stactiveInf",stactiveInf);
        Stinf stinf = userService.findStinf(stactiveInf.getAcCreatest());
        model.addAttribute("stinf",stinf);
        model.addAttribute("statue",statue);
        return "User/userShowAcInf";
    }
    /**
     * 活动报名
     */
    @ResponseBody
    @RequestMapping("/SinginUp")
    public String SinginUp(String acKey,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Integer resultrow = userService.SinginUp(userinf.getUserStid(), acKey);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("result",resultrow);
        String result = GsonUtils.toJson(stringIntegerHashMap);
        return result;
    }
    /**
     * 跳转到我加入的社团活动中
     */
    @RequestMapping("/searchMySinginupActive")
    public String searchMySinginupActive(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        List<Activeperson> myjoinactiveList = userService.findMyjoinactiveList(userinf.getUserStid());
        System.out.println(myjoinactiveList);
        model.addAttribute("activeList",myjoinactiveList);
        return "User/userSinginupActive";
    }
    /**
     * 取消报名
     */
    @ResponseBody
    @RequestMapping("/delSinginUp")
    public String delSinginUp(String acKey,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Integer resultrow = userService.delSinginUp(userinf.getUserStid(), acKey);
        HashMap<String, Integer> resultmap = new HashMap<>();
        resultmap.put("result",resultrow);
        String result = GsonUtils.toJson(resultmap);
        return result;
    }
    /**
     * 进入申请加入社团页面
     */
    @RequestMapping("/ApplicationJoinSt")
    public String applicatioJoinSt(Model model,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        List<Stinf> stList = userService.joinSt(userinf.getUserStid());
        model.addAttribute("stlist",stList);
        System.out.println(stList);
        return "User/userApplicationJoinst";
    }
    /**
     * 申请加入社团
     */
    @ResponseBody
    @RequestMapping("/applicationInf")
    public String applicationInf(HttpSession session,String stkey,String myinf,String myreason){
        User userinf = (User)session.getAttribute("userinf");
        Integer integer = userService.addJoinapplication(userinf.getUserStid(), stkey, myinf, myreason);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("result",integer);
        String result = GsonUtils.toJson(stringIntegerHashMap);
        return result;
    }
    /**
     * 查看我发出的申请
     */
    @RequestMapping("/showMyapplication")
    public String showMyapplication(Model model,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        List<Joinstapplication> joinstapplications = userService.showMyapplicate(userinf.getUserStid());
        model.addAttribute("applicationList",joinstapplications);
        return "User/userMyapplicationList";
    }
    /**
     * 根据id查看申请的详细信息
     */
    @RequestMapping("/showApplicationInf")
    public String showApplicationInf(String Ank,Model model){
        Joinstapplication joinstapplication = userService.showMyapplicationInf(Ank);
        model.addAttribute("applicationInf",joinstapplication);
        return "User/userShowApplicationInf";
    }

    @RequestMapping("/showMyaccount")
    public String showMycaaount(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        Account myaccountInf = userService.findMyaccountInf(userinf.getUserStid());
        if(session.getAttribute("topupflag")==null){
            session.setAttribute("topupflag","3");
        }
        if(myaccountInf==null){
            model.addAttribute("msg","1");
            model.addAttribute("accountinf",myaccountInf);
        }else {
            model.addAttribute("msg","2");
            session.setAttribute("md5password",myaccountInf.getAccountPassword());
            session.setAttribute("accountKey",myaccountInf.getAccountKey());
            List<Topup> topups = userService.findtopupList(userinf.getUserStid());
            model.addAttribute("accountinf",myaccountInf);
            model.addAttribute("topupList",topups);

        }
        return "User/userAccountInf";
    }

    /**
     * 开通账户
     * @param session
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/addaccount")
    public String addaccount(HttpSession session,String password){
        User userinf = (User)session.getAttribute("userinf");

        Integer integer = userService.addAccount(password,userinf.getUserStid());
        return String.valueOf(integer);
    }
    /**
     * 修改支付密码
     */
    @ResponseBody
    @RequestMapping("/sendcodes")
    public String sendcode(String telnum,HttpSession session){
        int num=(int)((Math.random()*9+1)*1000);
        String code=String.valueOf(num);
        String telcode = userService.getTelcode(telnum,code);
        System.out.println("错误码"+telcode);
        if("isv.BUSINESS_LIMIT_CONTROL".equals(telcode)){
            return "2";
        }else {
            session.setAttribute("sendcodes",code);
            return "1";
        }
    }
    @ResponseBody
    @RequestMapping("/checkcodes")
    public String checkcodes(String code,HttpSession session){
        if(session.getAttribute("sendcodes").toString().equals(code)){
            return "1";
        }else{
            return "2";
        }
    }
    @ResponseBody
    @RequestMapping("/updatePaypassword")
    public String updatePaypassword(String newpassword,HttpSession session){
        Integer accountKey = userService.updatePaypassword(newpassword, session.getAttribute("accountKey").toString());
        return accountKey.toString();

    }
    /**
     * 查询订单信息
     */
    @RequestMapping("/showMyorder")
    public String showOrder(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        List<Order> myorder = userService.findMyorder(userinf.getUserStid());
        Model myorder1 = model.addAttribute("myorder", myorder);
        return "User/userPayList";
    }

    /**
     * 支付前检查开户没有
     *
     */
    @ResponseBody
    @RequestMapping("/checkAccountStatue")
    public String checkOpen(HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Account myaccountInf = userService.findMyaccountInf(userinf.getUserStid());
        if(myaccountInf==null){
            return "1";
        }else {
            session.setAttribute("md5password",myaccountInf.getAccountPassword());

            return "2";
        }
    }
    /**
     * 支付
     */
    @ResponseBody
    @RequestMapping("/payorder")
    public String payMoney(String orderKey,String payMoney,String stkey,HttpSession session){
        User userinf = (User)session.getAttribute("userinf");
        Account myaccountInf = userService.findMyaccountInf(userinf.getUserStid());
        if(myaccountInf.getAccountMoney()<Long.valueOf(payMoney)){
            return "3";//状态3余额不足
        }else {
            Integer payorder = userService.payorder(orderKey, userinf.getUserStid(), payMoney, stkey);
            return payorder.toString();
        }
    }

    /**
     * 查询订单信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/showMyoldorder")
    public String showbeforeOrder(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        List<Order> myorder = userService.findorderaboutme(userinf.getUserStid());
        Model myorder1 = model.addAttribute("myorder", myorder);
        return "User/userorderList";
    }
    /**
     * 回复留言
     */
    @ResponseBody
    @RequestMapping("/backLeavemessage")
    public String backLeaveMessage(String Receiverperson,String title,String message,
                                   HttpSession session,String leaveMessageKey){
        Leavemessage leavemessage = new Leavemessage();
        UUID uuid = UUID.randomUUID();
        User userinf = (User)session.getAttribute("userinf");
        leavemessage.setLmKey(uuid.toString().replaceAll("\\-", ""));
        leavemessage.setLmSendperson(userinf.getUserStid());
        leavemessage.setLmReceiverperson(Receiverperson);
        leavemessage.setLmTitle("回复"+title);
        leavemessage.setLmMessage(message);
        System.out.println(leavemessage);
        Integer result = userService.backLeaveMessage(leavemessage,leaveMessageKey);
        HashMap<String, String> resultmap = new HashMap<>();
        resultmap.put("msg", result.toString());
        String resultJson = GsonUtils.toJson(resultmap);
        return resultJson;
    }
    @ResponseBody
    @RequestMapping("/tuichuSt")
    public String tuichuSt(String key){
        Integer integer = userService.tuichuSt(key);
        return integer.toString();
    }











}
