package com.bysj.stsys.service;

import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import com.bysj.stsys.phone.SendSmsAPI;
import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserManagerService {
    @Autowired
    com.bysj.stsys.dao.userDao userDao=null;
    @Autowired
    com.bysj.stsys.dao.stPersonDao stPersonDao=null;
    @Autowired
    SendSmsAPI sendSmsAPI=null;
    @Autowired
    com.bysj.stsys.dao.stListDao stListDao=null;
    @Autowired
    com.bysj.stsys.dao.leaveMessageDao leaveMessageDao=null;
    @Autowired
    com.bysj.stsys.dao.accouncementDao accouncementDao=null;
    @Autowired
    activeDao activeDao=null;
    @Autowired
    activePersonDao activePersonDao=null;
    @Autowired
    joinStapplicationDao joinStapplicationDao=null;
    @Autowired
    orderDao orderDao=null;
    @Autowired
    accountDao accountDao=null;
    @Autowired
    tollDao tollDao=null;
    @Autowired
    staccountDao staccountDao=null;
    /**
     * 查询是否为管理员
     */
    public List<Stpersoninf> searchIsStmanager(String userKey){
        List<Stpersoninf> stpersoninfs = stPersonDao.searchIsStmanager(userKey);
        return  stpersoninfs;
    }
    /**
     * 社团管理员查看社员的超详细信息
     */
    public Map<String,String> searchUserInf(String studentid){
        User user = userDao.searchUserInf(studentid);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("studentTel",user.getUserTel());
        stringStringHashMap.put("studentName",user.studentinf.getStudentName());
        stringStringHashMap.put("profession",user.studentinf.profession.getProfessionName());
        stringStringHashMap.put("college",user.studentinf.profession.collegeinf.getCollegeName());
        return stringStringHashMap;
    }
    public Integer addannounce(String atTitle,String atMessage,String userKey,String stKey){

        Random random = new Random();
        String result="";
        for (int i=0;i<8;i++)
        {
            result+=random.nextInt(10);
        }
        String atKey="AT"+result;
        Integer addannounce = accouncementDao.addannounce(atKey, atTitle, atMessage, userKey, stKey);
        return addannounce;
    }
    /**
     * 查看系统公告
     */
    public List<Announcement> showMyannounce(String userKey){
        List<Announcement> announcements = accouncementDao.showMyannounce(userKey);
        return announcements;
    }
    /**
     * 修改状态
     */
    public Integer updateAnnounceStatue(String anKey){
        Integer integer = accouncementDao.updateAnnounceStatue("'"+anKey+"'");
        return integer;
    }
    /**
     * 申请活动
     */
    public Integer addStactive(String actitle,String accontent,String acmaxpeoplenum,
                                   String acplace,String actime,String acdate,String acotherinf,String accreatest,String userKey){
        Random random = new Random();
        String result="";
        for (int i=0;i<8;i++)
        {
            result+=random.nextInt(10);
        }
        String acKey="AC"+result;
        Integer integer = activeDao.addStactive(acKey, actitle, accontent, acmaxpeoplenum, acplace, actime, acdate, acotherinf, accreatest,userKey);
        return integer;
    }
    /**
     * 查看申请列表
     */
    public List<Active> activeApplication(String userKey){

        List<Active> actives = activeDao.activeAppliaction(userKey);
        return actives;
    }
    /**
     * 查看报名信息
     */
    public List<Activeperson> findactivePerson(String acKey){
        List<Activeperson> activepeople = activePersonDao.searchActivePeopleInf(acKey);
        return activepeople;
    }
    /**
     * 查看要审核的申请
     */
    public List<Joinstapplication> dealWithJoinApplication(String stKey){
        List<Joinstapplication> joinstapplications = joinStapplicationDao.dealWithJoinApplication(stKey);
        return joinstapplications;
    }
    /**
     * 查看审核完成列表
     */
    public List<Joinstapplication> dealWithJoinApplicationresoult(String stKey){
        List<Joinstapplication> joinstapplications = joinStapplicationDao.dealWithJoinApplicationresoult(stKey);
        return joinstapplications;
    }
    /**
     * 拒绝加入社团的申请
     */
    public Integer refuseapplication(String applicationKey,String reason){
        Integer refuseapplication = joinStapplicationDao.refuseapplication(applicationKey,reason);
        return refuseapplication;
    }
    /**
     * 同意加入社团
     */
    public Integer agreeApplication(String application,String stpePersonKey,String stPestid){
        Integer integer = joinStapplicationDao.agreeApplication(application);
        Integer rows=null;
        if(integer==1){
            Random random = new Random();
            String result="";
            for (int i=0;i<10;i++)
            {
                result+=random.nextInt(10);
            }
            String stpeKey="STPE"+result;
            Integer integer1 = stPersonDao.addinSt(stpeKey, stpePersonKey, stPestid);
            rows =integer1;
        }
        return rows;
    }
    /**
     * 添加收款信息
     */
    @Transactional
    public Integer addtoll(String stkey,String tolltitle,String tollmessage,String tolllastime,String money){
        Integer row=null;
        try {

            Random random = new Random();
            String result="";
            for (int i=0;i<10;i++)
            {
                result+=random.nextInt(10);
            }
            String tollkey="TOLL"+result;
            Integer addtoll = tollDao.addtoll(tollkey, stkey, tolltitle, tollmessage, tolllastime,money);
            if(addtoll==1){
                //查找社团人物的id
                List<Stpersoninf> stpersoninfs = stPersonDao.searchstPerson(stkey);
                if(stpersoninfs.size()>0){
                    for (int i = 0; i < stpersoninfs.size(); i++) {
                        String orderKey = UUID.randomUUID().toString().replaceAll("-", "");
                        Integer addorder = orderDao.addorder(orderKey, tollkey, stpersoninfs.get(i).getStpePersonid(),money);
                        if(addorder>0){
                            row=1;
                        }
                    }
                }

            }
            return row;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException();

        }

    }
    public  List<Stpersoninf> finduserList(String stkey){
        List<Stpersoninf> stpersoninfs = stPersonDao.searchstPerson(stkey);
        return stpersoninfs;
    }

    public List<Toll> findtoll(String stkey){
        List<Toll> findtoll = tollDao.findtoll(stkey);
        return findtoll;
    }
    public List<Order> StmanagerFindorder(String tollkey){
        List<Order> orders = orderDao.StmanagerFindorder(tollkey);
        return orders;
    }
    public Integer closeToll(String tollkey){
        Integer closetoll = tollDao.closetoll(tollkey);
        return closetoll;
    }
    public Staccounnt findStaccount(String stkey){
        Staccounnt lookstaccount = staccountDao.lookstaccount(stkey);
        return lookstaccount;
    }



}

