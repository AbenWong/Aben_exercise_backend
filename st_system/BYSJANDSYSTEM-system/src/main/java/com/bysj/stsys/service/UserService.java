package com.bysj.stsys.service;

import com.bysj.stsys.Utils.MD5utils;
import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import com.bysj.stsys.phone.SendSmsAPI;
import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
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
    topupDao topupDao=null;
    @Autowired
    staccountDao staccountDao=null;
    @Autowired
    stKindDao stKindDao=null;
    public User userlogin(String username, String password){
        User userlogin = userDao.userlogin(username, password);
        return userlogin;
    }
    /**
     * 发送短信
     */
    public String getTelcode(String telNum,String code){
        String msg_code = sendSmsAPI.sendSms(telNum, code, "SMS_188992117");
        return msg_code;
    }
    /**
     * 填写验证码后验证身份
     */
    public User loginBytel(String telNum){
        User user = userDao.userloginBytel(telNum);
        return user;
    }
    /**
     * 根据用户的id确定用户参与的社团
     */
    public List<Stpersoninf> getStlist(String userkey){
        List<Stpersoninf> stlist = stPersonDao.getStlist(userkey);
        return stlist;
    }
    /**
     * 根据用户传入的社团编号查看社团的详细信息
     */
    public Stinf finStinf(String Stkey){
        Stinf stinf = stListDao.findStinf(Stkey);
        return  stinf;
    }
    /**
     * 查看社团人数
     */
    public Integer StAllNumber(String Stkey){
        Integer StPeopleNum = stPersonDao.StAllNumber(Stkey);
        return  StPeopleNum;
    }
    /**
     * 查看自己所在社团的成员
     */
    public List<Stpersoninf> searchStperson(String Stkey,String Userkey){
       List<Stpersoninf> stpersoninfs = stPersonDao.searchStperson(Stkey, Userkey);
        return  stpersoninfs;
    }
    /**
     * 给用户留言
     */
    public Integer addLeaveMessage(Leavemessage leavemessage){
        Integer integer = leaveMessageDao.addLeaveMessage(leavemessage);
        return integer;
    }
    /**
     * 查询系统公告
     */
    public List<Announcement> findRootAccounceMent(){
        List<Announcement> rootAccounceMent = accouncementDao.findRootAccounceMent();
        for (int i = 0; i <rootAccounceMent.size() ; i++) {
            if(rootAccounceMent.get(i).getAtMessage().length()>40){
                String oldmessage=rootAccounceMent.get(i).getAtMessage();
                String newmessage=oldmessage.substring(0,40);
                rootAccounceMent.get(i).setAtMessage(newmessage);
            }
        }
        return rootAccounceMent;
    }
    /**
     * 利用ajax查询系统公告完整版
     */
    public String findAllMessage(String Atkey){
        String allMessage = accouncementDao.findAllMessage(Atkey);
        return allMessage;
    }
    /**
     * 查询社团公告
     */
    public List<Announcement> findStannouncement(String userKey){
        List<String> JoinStList = stPersonDao.searchMyjoinSt(userKey);
        System.out.println("加入的社团");
        System.out.println(JoinStList);
        List<Announcement> stAccounceMent = accouncementDao.findStAccounceMent(JoinStList);
        for (int i = 0; i <stAccounceMent.size() ; i++) {
            if(stAccounceMent.get(i).getAtMessage().length()>40){
                String oldmessage=stAccounceMent.get(i).getAtMessage();
                String newmessage=oldmessage.substring(0,40);
                stAccounceMent.get(i).setAtMessage(newmessage);
            }
        }
        return  stAccounceMent;
    }
    /**
     * 查看社团活动信息
     */
    public List<Active> findStactive(String UserKey){
        List<String> myjionactive = activePersonDao.findMyjionactive(UserKey);
        List<Active> stActive = activeDao.findStActive(myjionactive);
        if(stActive.size()>0) {
            for (int i = 0; i < stActive.size(); i++) {
                stActive.get(i).setAcTime(stActive.get(i).getAcTime().toString().substring(0, 5).toString());
                if ((stActive.get(i).getAcContent().toString().length() > 20)) {
                    stActive.get(i).setAcContent(stActive.get(i).getAcContent().toString().substring(0, 20).toString());

                }

            }
        }
        return stActive;
    }
    /**
     * 查看当前有多少人参加了这个活动
     */
    public Integer findHowManyPeopleJoinActive(String acKey){
        Integer howManyPeopleJoinActive = activePersonDao.findHowManyPeopleJoinActive(acKey);
        return howManyPeopleJoinActive;
    }
    /**
     * 查看社团详细的信息
     */
    public Active findStactiveInf(String acKey){
        Active stactiveInf = activeDao.findStactiveInf(acKey);
        if(stactiveInf!=null){
            stactiveInf.setAcTime(stactiveInf.getAcTime().toString().substring(0,5));

        }
        return stactiveInf;
    }
    /**
     * 查看社团详细信息
     */
    public Stinf findStinf(String Stkey){
        Stinf stinf = stListDao.findStinf(Stkey);
        return stinf;
    }
    /**
     * 活动报名
     */
    public Integer SinginUp(String userKey,String acKey){
        UUID uuid = UUID.randomUUID();
        String apKey = uuid.toString().replaceAll("-", "");
        Integer integer = activePersonDao.singInUp(apKey, userKey, acKey);
        return integer;
    }
    /**
     * 查询我加入的活动
     */
    public List<Activeperson> findMyjoinactiveList(String userKey){
        List<Activeperson> myjoinactiveList = activePersonDao.findMyjoinactiveList(userKey);
        return myjoinactiveList;
    }
    /**
     * 取消报名
     */
    public Integer delSinginUp(String userKey,String acKey){
        Integer integer = activePersonDao.delSinginUp(userKey, acKey);
        return integer;
    }
    /**
     * 申请加入社团
     */
    public List<Stinf> joinSt(String userKey){
        List<String> myjoinSt = stPersonDao.myJoinStkey(userKey);
        List<Stinf> notJoinStList = stListDao.findNotJoinStList(myjoinSt);
        return  notJoinStList;
    }
    /**
     * 申请的信息
     */
    public Integer addJoinapplication(String userkey,String stkey,String myinf,String myreason){
        String uuid = UUID.randomUUID().toString();
        String apkey = uuid.replaceAll("-", "");
        Integer integer = joinStapplicationDao.addJoinapplication(apkey, userkey, stkey, myinf, myreason);
        return integer;
    }
    /**
     * 根据用户id查看申请列表
     */
    public List<Joinstapplication> showMyapplicate(String userKey){
        List<Joinstapplication> joinstapplications = joinStapplicationDao.showMyapplicate(userKey);
        return joinstapplications;
    }
    /**
     * 查看自己的申请信息
     */
    public Joinstapplication showMyapplicationInf(String Ank){
        Joinstapplication joinstapplication = joinStapplicationDao.showMyapplicationInf(Ank);
        return joinstapplication;
    }
    /**
     * 查看个人信息
     */
    public User findUserinf(String userKey){
        User userinf = userDao.findUserinf(userKey);
        return userinf;
    }
    /**
     * 修改手机号发送短信
     */
    public String changeTelSendCode(String code, String tel){
        String msg_code = sendSmsAPI.sendSms(tel, code, "SMS_188992117");
        return msg_code;
    }
    /**
     * 修改手机号
     */
    public Integer changeTelbegin(String newTel,String userKey){
            try {
                Integer s = userDao.changeTel(newTel, userKey);
                return s;
            }catch (
                    Exception e
            ){
                return 101;
            }




    }
    /**
     * 修改密码
     */
    public Integer changePassword(String userKey,String newPassword){
        Integer integer = userDao.changePassword(userKey, newPassword);
        return integer;
    }
    /**
     * 上传头像将文件路径写入数据库
     */
    public Integer updatetouxiang(String userKey,String path){

        userDao.updatetouxiang(userKey,path);
        return 1;
    }
    /**
     * 查询个人账户
     */
    public Account findMyaccountInf(String userKey){

        synchronized (new Object()){
            Account myaccountInf = accountDao.findMyaccountInf(userKey);
            return myaccountInf;
        }

    }

    /**
     * 开通账户
     * @param
     * @return
     */
    public Integer addAccount(String password,String userKey){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        String md5 = MD5utils.MD5_SHA(password, "MD5");
        md5="'"+md5+"'";
        Integer integer = accountDao.addAccount(userKey, md5, val);
        return integer;
    }
    /**
     * 充值操作
     */
    public  Integer topUp(String accountKey,String saveMoney,String userkey){
        Integer returnrows=null;
        synchronized (new Object()) {
            returnrows = accountDao.topUp(accountKey, saveMoney);
            if(returnrows==1){
                topupDao.addmoney(UUID.randomUUID().toString().replaceAll("-",""),
                        saveMoney,userkey);
            }
        }
        return returnrows;
    }
    /**
     * 修改用户支付密码
     */
    public Integer updatePaypassword(String password,String accountKey){
        Integer returnrows=null;
        synchronized (new Object()) {
            String md5 = MD5utils.MD5_SHA(password, "MD5");
            md5="'"+md5+"'";
            returnrows = accountDao.updatePaypassword(md5, accountKey);
        }
        return returnrows;
    }

    /**
     * 查询我没有支付的订单
     */
    public List<Order> findMyorder(String userKey){
        List<Order> myorder = orderDao.findMyorder(userKey, "1", "1");
        return  myorder;
    }
    /**
     * 查询所有和我有关的订单支付的订单
     */
    public List<Order> findorderaboutme(String userKey){
        List<Order> myorder = orderDao.findorderaboutme(userKey);
        return  myorder;
    }
    /**
     * 查询充值记录
     */
    public List<Topup> findtopupList(String userKey){
        List<Topup> topups = topupDao.findtopupList(userKey);
        return topups;
    }
    /**
     * 付款
     */
    @Transactional
    public Integer payorder(String orderKey,String userKey,String money,String stkey){
        //第一步：个人账户减少钱
        try {
            Integer myaccountpay = accountDao.myaccountpay(userKey, money);
            System.out.println("第一遍返回时"+myaccountpay);
            if(myaccountpay<1){
                throw new RuntimeException();
            }else {
                Integer addmoney = staccountDao.addmoney(stkey, money);
                System.out.println("第2遍返回时"+addmoney);

                //第二步：社团账户增加钱
                if(addmoney<1){
                    throw  new RuntimeException();
                }else {
                    //第三步：个人订单号状态修改
                    Integer payupdatestatue = orderDao.payupdatestatue(orderKey);
                    System.out.println("第3遍返回时"+payupdatestatue);

                    if(payupdatestatue<1){
                        throw new RuntimeException();
                    }else {
                        return payupdatestatue;
                    }
                }

            }
        }catch (Exception e){
            return 0;
        }



    }
    /**
     *查看我的消息
     */
    public List<Leavemessage> findmymessageshowMyleaveessage(String userKey){
        List<Leavemessage> leavemessages = leaveMessageDao.findmymessageshowMyleaveessage(userKey);
        return  leavemessages;
    }
    /**
     * 回复留言
     */
    public Integer backLeaveMessage(Leavemessage leavemessage,String leaveMessageKey){
        Integer integer1 = leaveMessageDao.updateleaveStatue(leaveMessageKey);
        Integer integer = leaveMessageDao.addLeaveMessage(leavemessage);
        return integer;
    }
    /**
     * 退出社团
     */
    public Integer tuichuSt(String key){
        Integer integer = stPersonDao.tuichuSt(key);
        return integer;
    }
    /**
     * 申请成立社团
     */
    public List<Stkind> findStkind(){
        List<Stkind> stKindList = stKindDao.getStKindList();
        return stKindList;
    }














}
