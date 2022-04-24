package com.bysj.stsys.controller;

import com.bysj.stsys.Utils.GsonUtils;
import com.bysj.stsys.model.*;
import com.bysj.stsys.service.UserManagerService;
import com.bysj.stsys.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bysj.stsys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userManager")
public class userManangerController {
    @Autowired
    UserService userService=null;
    @Autowired
    UserManagerService userManagerService=null;
    @RequestMapping("/jumptoManagerPage")
    public String jumptoManagerpage(){
        return "/User/Usermanager/userManagerPage";
    }
    @RequestMapping("jumptoUserPage")
    public String jumptoUserPage(){
        return "/User/userMtpage";
    }
    /**
     * 查询我管理的社团信息
     */
    @RequestMapping("/searchMymanagerSt")
    public String searchMymanangerSt(HttpSession session, Model model) {
        User userinf = (User) session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt", stlist);
        System.out.println(stlist);
        return "User/Usermanager/userManagerStList";
    }
    /**
     * 查看社团详细信息
     */
    @RequestMapping("/showStinf")
    public String userManagerShowStinf(String stKey,Model model){
        Stinf stinf = userService.finStinf(stKey);
        model.addAttribute("stPeopleNume",userService.StAllNumber(stKey));
        model.addAttribute("stinf",stinf);
        return "User/Usermanager/userManagerShowStInf";
    }
    /**
     * 查看管理的社团成员信息
     * 查看列表
     */
    @RequestMapping("/searchStpersonList")
    public String showStpersonInf(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        System.out.println(stlist);
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagerStpersonList";
    }
    /**
     * 查看人员
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
        return "User/Usermanager/userManagerStpersonList";
    }
    /**
     * 查看详细人员信息
     */
    @ResponseBody
    @RequestMapping("/ManagershowPersonInf")
    public String ManagershowPersonInf(String userKey){
        Map<String, String> stringStringMap = userManagerService.searchUserInf(userKey);
        String studentinf = GsonUtils.toJson(stringStringMap);
        return studentinf;
    }
    /**
     * 进入发布公告界面
     */
    @RequestMapping("/addAnnouncePage")
    public String addAnnouncePage(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        System.out.println(stlist);
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagerAddannounce";
    }
    @ResponseBody
    @RequestMapping("/addAnnounce")
    public String addAnnounce(HttpSession session,Model model,String atTitle,String atMessage,String stKey){
        User userinf = (User)session.getAttribute("userinf");
        Integer addannounce = userManagerService.addannounce(atTitle, atMessage, userinf.getUserStid(), stKey);
        return addannounce.toString();
    }
    /**
     * 查看我发布的公告
     */
    @RequestMapping("/showAnnounce")
    public String showAnnounce(HttpSession session,Model model){
        User userinf = (User)session.getAttribute("userinf");
        List<Announcement> announcements = userManagerService.showMyannounce(userinf.getUserStid());
        model.addAttribute("accounceList",announcements);
        return "User/Usermanager/userManagerShowannounce";

    }
    @ResponseBody
    @RequestMapping("/updateAnnounceStatue")
    public String updateAnnounceStatue(String ankey){
        Integer integer = userManagerService.updateAnnounceStatue(ankey);
        return integer.toString();
    }
    /**
     * 跳转到发布公告
     */
    @RequestMapping("/userManageraddAcctive")
    public String addAcctivePage(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManageraddAcctive";
    }
    /**
     * 新增社团
     */
    @ResponseBody
    @RequestMapping("/userManageraddActiveAjax")
    public String addActive(String actitle,String accontent,String acmaxpeoplenum,HttpSession session,
                            String acplace,String actime,String acdate,String acotherinf,String stKey){
        User userinf = (User)session.getAttribute("userinf");
        Integer integer = userManagerService.addStactive(actitle, accontent, acmaxpeoplenum, acplace, actime, acdate, acotherinf, stKey,userinf.getUserStid());
        return integer.toString();
    }
    /**
     * 查看申请列表
     */
    @RequestMapping("/userManageraddAcctiveList")
    public String addAcctiveList(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");

        List<Active> actives = userManagerService.activeApplication(userinf.getUserStid());
        model.addAttribute("activeApplication",actives);
        return "User/Usermanager/userManagerActiveApplication";
    }
    @RequestMapping("/showManagerActiveInf")
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
        return "User/Usermanager/userManagerShowActiveInf";
    }
    @ResponseBody
    @RequestMapping("/findActivepersonList")
    public String findActivepersonList(String acKey){
        List<Activeperson> activepeople = userManagerService.findactivePerson(acKey);
        String personJson = GsonUtils.toJson(activepeople);
        System.out.println(personJson);
        return personJson;
    }

    /**
     * 处理申请跳转页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/jumpToshowapplicationList")
    public String jumptoshowApplicationList(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagershowApplication";
    }
    /**
     * 处理申请 查询再调回去
     */
    @RequestMapping("/dealWithJoinapplication")
    public String dealWithJoinapplication(String Stkey,HttpSession session,Model model){
        if(Stkey!=null){
            session.setAttribute("Stkey",Stkey);
        }
        if(Stkey==null){
            Stkey=session.getAttribute("Stkey").toString();
        }
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        List<Joinstapplication> joinstapplications = userManagerService.dealWithJoinApplication(Stkey);
        model.addAttribute("applicationList",joinstapplications);
        return "User/Usermanager/userManagershowApplication";

    }
    /**
     * 查看申请的处理记录
     */
    @RequestMapping("/jumpToshowapplicationResoultList")
    public String jumptoshowApplicationResoultList(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagershowApplicationResoult";
    }
    /**
     * 返回搜索结果
     */
    @RequestMapping("/showApplicationreason")
    public String showApplicationResoult(String Stkey,HttpSession session,Model model){
        if(Stkey!=null){
            session.setAttribute("Stkey",Stkey);
        }
        if(Stkey==null){
            Stkey=session.getAttribute("Stkey").toString();
        }
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        List<Joinstapplication> joinstapplications = userManagerService.dealWithJoinApplicationresoult(Stkey);
        model.addAttribute("applicationList",joinstapplications);
        return "User/Usermanager/userManagershowApplicationResoult";
    }
    @ResponseBody
    @RequestMapping("/refuseapplication")
    public String refuseapplication(String application,String reason){
        Integer refuseapplication = userManagerService.refuseapplication(application,reason);
        return refuseapplication.toString();
    }
    @ResponseBody
    @RequestMapping("/agreeapplication")
    public String agreeApplication(String application,String studentid,String stid){
        Integer integer = userManagerService.agreeApplication(application, studentid, stid);
        return integer.toString();
    }
    /**
     * 跳转到发布界面
     */
    @RequestMapping("/jumptoAddtoll")
    public String jumptoAddtoll(HttpSession session,Model model){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagerAddtoll";
    }
    @ResponseBody
    @RequestMapping("/addtoll")
    public String addtoll(String stkey,String tolltitle,String tollmessage,String tolllastime,String money){
        Integer addtoll = userManagerService.addtoll(stkey, tolltitle, tollmessage, tolllastime,money);
        return addtoll.toString();
    }

    /**
     * 根据stkey查询需要缴费的人物列表
     *
     */
    @ResponseBody
    @RequestMapping("/searchPersonlist")
    public String searchPersonlist(String stkey){
        List<Stpersoninf> stpersoninfs = userManagerService.finduserList(stkey);
        String s = GsonUtils.toJson(stpersoninfs);
        System.out.println(s);
        return s;
    }

    @RequestMapping("/showmytoll")
    public String gotoshowtollpage(Model model,HttpSession session){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagerTollList";
    }
    @RequestMapping("/searchtoll")
    public String searchtoll(String Stkey,HttpSession session,Model model){
        if(Stkey!=null){
            session.setAttribute("Stkey",Stkey);
        }
        if(Stkey==null){
            Stkey=session.getAttribute("Stkey").toString();
        }
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        List<Toll> findtoll = userManagerService.findtoll(Stkey);
        model.addAttribute("tollList",findtoll);
        return "User/Usermanager/userManagerTollList";

    }
    @ResponseBody
    @RequestMapping("/showStpersontoll")
    public String showStpersontoll(String tollkey){
        List<Order> orders = userManagerService.StmanagerFindorder(tollkey);
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getOrderStatue().toString().equals('1')){
                orders.get(i).setOrderStatue("已经支付");
            }
            if(orders.get(i).getOrderStatue().toString().equals('2')){
                orders.get(i).setOrderStatue("没有支付");
            }

        }
        String s = GsonUtils.toJson(orders);
        System.out.println(s);

        return s;
    }
    @ResponseBody
    @RequestMapping("/closetoll")
    public String closetoll(String tollkey){
        Integer integer = userManagerService.closeToll(tollkey);
        return integer.toString();
    }
    @RequestMapping("/showManagerAccount")
    public String showStaccount(Model model,HttpSession session){
        session.removeAttribute("Stkey");
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        return "User/Usermanager/userManagerAccountinf";
    }
    @RequestMapping("/showAccountinf")
    public String showAccountinf(Model model,HttpSession session,String Stkey){
        if(Stkey!=null){
            session.setAttribute("Stkey",Stkey);
        }
        if(Stkey==null){
            Stkey=session.getAttribute("Stkey").toString();
        }
        User userinf = (User)session.getAttribute("userinf");
        List<Stpersoninf> stlist = userService.getStlist(userinf.getUserStid());
        model.addAttribute("userJoinSt",stlist);
        Staccounnt staccount = userManagerService.findStaccount(Stkey);
        model.addAttribute("account",staccount);
        return "User/Usermanager/userManagerAccountinf";

    }



}
