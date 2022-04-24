package com.bysj.stsys.controller;

import com.bysj.stsys.Utils.GsonUtils;
import com.bysj.stsys.model.*;
import com.bysj.stsys.service.RootService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.bysj.stsys.model.*;
import com.bysj.stsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stlnf")
public class stInfController {
    @Autowired
    RootService rootService;
    @Autowired
    UserService userService;
    @RequestMapping("/search_stList")
    public String seaachStList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "5",value = "showNum") Integer showNum){

        PageHelper.startPage(pageNum,showNum);
        List<Stinf> stlist = rootService.findStlist();
        PageInfo<Stinf> stinfPageInfo = new PageInfo<>(stlist);
        model.addAttribute("pageinf",stinfPageInfo);
        return "Root/showStList";
    }
    /**
     * 根据社团的id查看社团的详细信息
     */
    @RequestMapping("/searchStinf")
    public String searchStinf(Model model,@RequestParam(value = "Stkey") String stKey){
        Stinf stinf = rootService.finStinf(stKey);
        Integer stPeopleNume = rootService.StAllNumber(stKey);
        model.addAttribute("stPeopleNume",stPeopleNume);
        model.addAttribute("stinf",stinf);
        return "Root/showStinf";
    }
    /**
     * 修改社团名
     */
    @RequestMapping("/updateStname")
    @ResponseBody
    public String updateStname(String stKey,String newStName){
        Integer row = rootService.updateStName(newStName, stKey);
//        newStName="'"+newStName+"'";
        HashMap<String, Integer> rowMsg = new HashMap<String, Integer>();
        rowMsg.put("msg",row);
        String rowJson = GsonUtils.toJson(rowMsg);
        return rowJson;
    }
    /**
     * 获取列表
     */
    @ResponseBody
    @RequestMapping("/getStKindList")
    public String getStkindList(){
        List<Stkind> stKindList = rootService.getStKindList();
        String stKindListJson = GsonUtils.toJson(stKindList);
        return stKindListJson;
    }
    /**
     *修改社团所属类别
     */
    @ResponseBody
    @RequestMapping("/updateStkind")
    public String updateStkind(String newStkind,String stKey){
        Integer row= rootService.updateStkind(newStkind, stKey);
        HashMap<String, Integer> rowmsg = new HashMap<>();
        rowmsg.put("msg",row);
        String rowMsgJson = GsonUtils.toJson(rowmsg);
        return rowMsgJson;
    }
    /**
     *修改社团备注
     */
    @ResponseBody
    @RequestMapping("/updateStintf")
    public String updateStintf(String newStintf,String stKey){
        Integer row = rootService.updateStintf(newStintf, stKey);
        HashMap<String, Integer> rowmsg = new HashMap<>();
        rowmsg.put("msg",row);
        String rowMsgJson = GsonUtils.toJson(rowmsg);
        return rowMsgJson;
    }
    /**
     * 跳转到社团分类列表界面
     */
    @RequestMapping("/search_StkingList")
    public String getStkindList(Model model){
        List<Stkind> stKindList = rootService.getStKindList();
        model.addAttribute("stKindList",stKindList);
        return "Root/showStkindList";
    }
    /**
     * 查看社团类别下属的社团
     *
     */
    @ResponseBody
    @RequestMapping("/getStListByStKey")
    public String getStListByStkey(String stKindKey){
        List<Stinf> stinfs = rootService.selectStinfByStkey(stKindKey);
        String stInfJson = GsonUtils.toJson(stinfs);
        System.out.println(stInfJson);
        return stInfJson;
    }
    /**
     * 修改社团类别名称
     */
    @ResponseBody
    @RequestMapping("/updateStkindName")
    public String updateStKindName(String stKindkey,String newStkindname){
        Integer row = rootService.updateStKindName(stKindkey, newStkindname);
        HashMap<String, Integer> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("msg",row);
        String rows = GsonUtils.toJson(stringStringHashMap);
        return rows;
    }
    /**
     * 删除社团钟类
     */
    @ResponseBody
    @RequestMapping("/delStkind")
    public String delStkind(String stKindKey){
        Integer row = rootService.delStkind(stKindKey);
        HashMap<String, Integer> rows = new HashMap<>();
        rows.put("msg",row);
        String rowJSON = GsonUtils.toJson(rows);
        return rowJSON;
    }
    /**
     * 添加新的社团种类
     */
    @ResponseBody
    @RequestMapping("/addNewStkind")
    public String addnewStkind(String newStkindName){
        Map<String, String> resultMap = rootService.addStkind(newStkindName);
        String resultJson = GsonUtils.toJson(resultMap);
        return resultJson;
    }
    @ResponseBody
    @RequestMapping("/checkStkindNameIsPresence")
    public String checkStkindNameIsPresence(String newStkindName){
        List<Stkind> stkinds = rootService.checkStkindNameIsPresence(newStkindName);
        String resultJson = GsonUtils.toJson(stkinds);
        return resultJson;
    }
    @ResponseBody
    @RequestMapping("/showStperson")
    public String showStperson(String Stkey, HttpSession session){
        List<Stpersoninf> stpersoninfs = rootService.searchstPerson(Stkey);
        String s = GsonUtils.toJson(stpersoninfs);
        System.out.println(s);

        return s;
    }
    @ResponseBody
    @RequestMapping("/sheweishezhang")
    public String shsweishezhang(String newStudentId,String oldStudentId,String Stkey){
        Integer integer = rootService.updateFuzeren(newStudentId, oldStudentId, Stkey);
        return integer.toString();
    }
    @ResponseBody
    @RequestMapping("/updateStstatue")
    public String updateStstatue(String Stkey,String statue){
        Integer integer=null;
        if(statue.equals("1")){
            integer = rootService.updateStstatue(Stkey, "2");

        }
        if(statue.equals("2")){
             integer = rootService.updateStstatue(Stkey, "1");

        }
        return integer.toString();
    }
    @RequestMapping("/jumptoAddSt")
    public String jumptoAddst(Model model){
        /**
         * 查查询社团种类列表和学院列表
         */
        List<Stkind> stKindList = rootService.getStKindList();
        model.addAttribute("stKindlist",stKindList);
        return "Root/addSt";
    }
    /**
     * 申请成立社团
     */
    @RequestMapping("/chengliSt")
    public String chengliSt(Model model){
        List<Stkind> stkind = rootService.findStkind();
        List<Collegeinf> findcollege = rootService.findcollege();
        model.addAttribute("stkind",stkind);
        model.addAttribute("college",findcollege);
        return "Root/addSt";
    }
    @ResponseBody
    @RequestMapping("/findprofession")
    public String findprofession(String collegeKey){
        List<Profession> findprofession = rootService.findprofession(collegeKey);
        String s = GsonUtils.toJson(findprofession);
        System.out.println(s);
        return s;
    }
    @ResponseBody
    @RequestMapping("/findpeopleList")
    public String findpeopleList(String profecessKey,String xuenian){
        List<User> users = rootService.findpersonList(profecessKey, xuenian);
        String s = GsonUtils.toJson(users);
        System.out.println(s);
        return s;
    }
    @ResponseBody
    @RequestMapping("/addSt")
    public String addSt(String stname,String studentpersonId,String stKind,String stinf){
        Integer integer = rootService.addSt(stname, studentpersonId, stKind, stinf);
        return integer.toString();
    }
    @RequestMapping("/jumptoStactivepage")
    public String showUserActive(Model model,HttpSession session){
        List<Active> findactive = rootService.findactive();
        model.addAttribute("active",findactive);
        return "Root/showActive";
    }
    @ResponseBody
    @RequestMapping("/updateacStatue")
    public String updateStatue(String statue,String ackey){
        Integer integer = rootService.updateActiveStatue(statue, ackey);
        return integer.toString();
    }
    @RequestMapping("/jumptoAnnouncePage")
    public String jumptoAnnouncePage(Model model){
        List<Announcement> rootAccounceMent = rootService.findRootAccounceMent();
        model.addAttribute("accounce",rootAccounceMent);
        return "Root/showaccouncement";
    }
    @ResponseBody
    @RequestMapping("/updateAnnounceStatue")
    public String updateAnnounceStatue(String ankey){
        Integer integer = rootService.updateRootAccouncestatue("'"+ankey+"'");
        return integer.toString();
    }
    @RequestMapping("/addaccouncement")
    public String addaccouncement(){
        return "Root/addAccouncement";
    }
    @ResponseBody
    @RequestMapping("/addAnnounce")
    public String addAnnounce(HttpSession session,Model model,String atTitle,String atMessage){

        Integer addannounce = rootService.rootaddannounce(atTitle,atMessage);
        return addannounce.toString();
    }
    @RequestMapping("/updaterootinf")
    public String jumptorootinf(HttpSession session, Model model){

        return "Root/rootinf";
    }
    @ResponseBody
    @RequestMapping("/updatepassword")
    public String updatepassword(String newpassword,String userkey){
        Integer updatepassword = rootService.updatepassword(newpassword, userkey);
        return updatepassword.toString();
    }

}
