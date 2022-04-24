package com.bysj.stsys.service;

import com.bysj.stsys.Face.BaiduAIFace;
import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import com.bysj.stsys.SetingModel.Setingmodel;
import com.bysj.stsys.dao.*;
import com.bysj.stsys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class RootService {
    @Autowired
    com.bysj.stsys.dao.rootUserDao rootUserDao=null;
    @Autowired
    Setingmodel setingmodel=null;
    @Autowired
    BaiduAIFace faceapi=null;
    @Autowired
    com.bysj.stsys.dao.stListDao stListDao=null;
    @Autowired
    com.bysj.stsys.dao.userDao userDao=null;
    @Autowired
    com.bysj.stsys.dao.stPersonDao stPersonDao=null;
    @Autowired
    com.bysj.stsys.dao.stKindDao stKindDao=null;
    @Autowired
    collegeDao collegeDao=null;
    @Autowired
    proferceDao proferceDao=null;
    @Autowired
    activeDao activeDao=null;
    @Autowired
    accouncementDao accouncementDao=null;
    public Rootuser login(String username, String password){
        Rootuser rootuser = rootUserDao.rootLogin(username, password);
        return rootuser;

    }
    public Map<String,Object> searchface(StringBuffer imagebase64) throws IOException {
        String substring = imagebase64.substring(imagebase64.indexOf(",")+1, imagebase64.length());
        setingmodel.setImgpath(substring);
        setingmodel.setGroupID("StRoot");
        System.out.println(substring);
        Map map = faceapi.FaceSearch(setingmodel);
        return map;

    }
    public List<Stinf> findStlist(){
        List<Stinf> stlist = stListDao.rootfindStlist();
        return stlist;
    }
    public Stinf finStinf(String Stkey){
        Stinf stinf = stListDao.findStinf(Stkey);
        return  stinf;
    }
    /**
     * 获取社团总人数
     */
    public Integer StAllNumber(String Stkey){
        Integer StPeopleNum = stPersonDao.StAllNumber(Stkey);
        return  StPeopleNum;
    }
    /**
     * 修改社团名称
     */
    public Integer updateStName(String newStName,String stKey){
        Integer row= stListDao.updateStName(newStName, stKey);
        return row;
    }
    /**
     * 获取社团列表
     */
    public List<Stkind> getStKindList(){
        List<Stkind> stKindList = stKindDao.getStKindList();
        return stKindList;
    }
    /**
     * 修改社团所属类别
     */
    public Integer updateStkind(String newStkind,String stKey){
        Integer row = stListDao.updateStkind(newStkind, stKey);
        return  row;
    }

    /**
     * 修改社团下的备注
     */
    public Integer updateStintf(String newStintf,String stKey){
        Integer row = stListDao.updateStintf(newStintf, stKey);
        return row;
    }
    /**
     * 根据社团类别key获取下述的社团
     */
    public List<Stinf> selectStinfByStkey(String stKindKey){
        List<Stinf> stinfs = stListDao.selectStinfByStkey(stKindKey);
        return stinfs;
    }
    /**
     * 修改社团钟类名称
     */
    public Integer updateStKindName(String stKindKey,String newStKindName){
        Integer row = stKindDao.updateStKindName(stKindKey, newStKindName);
        return row;
    }
    /**
     * 删除社团分类
     */
    public Integer delStkind(String stKindKey){
        Integer row=null;
        try {
             row = stKindDao.delStkind(stKindKey);
        }catch (Exception e){

            return 200001;
        }
        return row;
    }
    /**
     * 新增社团
     */
    public Map<String,String> addStkind(String newStkindName){
        String stKindKey = "SD"+String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        Integer rows = stKindDao.addStkind(stKindKey, newStkindName);
        HashMap<String, String> resultmap = new HashMap<>();
        resultmap.put("msg",String.valueOf(rows));
        resultmap.put("newKey",stKindKey);
        return resultmap;
    }
    /**
     * 查看新增的社团是否存在
     */
    public List<Stkind> checkStkindNameIsPresence(String stKindName){
        List<Stkind> stkinds = stKindDao.checkStkindNameIsPresence(stKindName);
        return stkinds;
    }
    /**
     * 上传头像
     */
    public Integer uploadStlogo(String stkey,String logo){
        Integer integer = stListDao.uploadStlogo(stkey, logo);
        return integer;
    }
    public List<Stpersoninf> searchstPerson(String stkey){
        List<Stpersoninf> stpersoninfs = stPersonDao.searchstPerson(stkey);
        return stpersoninfs;
    }
    /**
     * 修改社团负责人
     */
    public Integer updateFuzeren(String newStudentId,String oldStudentId,String Stkey){
        //第一步修改社团表中的负责人
        Integer rows=null;
        Integer integer = stListDao.updateFuzeren(Stkey,newStudentId);
        if(integer>0){
            //第二步修改社团人员表中的issz为2
            Integer integer1 = stPersonDao.updateFuezeren(oldStudentId, Stkey);
            if(integer1>0){
                Integer integer2 = stPersonDao.updateFuezeren2(newStudentId, Stkey);
                rows=integer2;
            }

        }
        return rows;
    }
    /**
     * 修改状态
     */
    public Integer updateStstatue(String Stkey,String statue){
        Integer integer = stListDao.updateStstatue(Stkey, statue);
        return integer;
    }
    /**
     * 新增社团
     */
    public List<Stkind> findStkind(){
        List<Stkind> stKindList = stKindDao.getStKindList();
        return stKindList;
    }
    public List<Collegeinf> findcollege(){
        List<Collegeinf> collegeinfs = collegeDao.findcollegeList();
        return collegeinfs;
    }
    public List<Profession> findprofession(String collegeKey){
        List<Profession> findprofession = proferceDao.findprofession(collegeKey);
        return findprofession;
    }
    public List<User> findpersonList(String profecessKey,String xuenian){
        List<User> users = userDao.findpersonList(profecessKey, xuenian);
        return users;
    }
    public Integer addSt(String stname,String studentpersonId,String stKind,String stinf){
        Random random = new Random();
        String result="";
        for (int i=0;i<8;i++)
        {
            result+=random.nextInt(10);
        }
        result="ST"+result;
        Integer integer = stListDao.addSt(result, stname, studentpersonId, stKind, stinf);
        //需要新增一个把这个学生添加到社团成员的表里面
        String stpe="";
        for (int i=0;i<10;i++)
        {
            stpe+=random.nextInt(10);
        }
        String stpeKey="STPE"+stpe;
        stPersonDao.createinSt(stpeKey, studentpersonId, result);
        return integer;
    }
    public List<Active> findactive(){
        List<Active> findactive = activeDao.findactive();
        return  findactive;
    }
    /**
     * 修改活动状态
     */
    public Integer updateActiveStatue(String statue,String ackey){
        Integer integer = activeDao.updateAcStatue(statue, ackey);
        return integer;
    }
    /**
     * 查看社团公告
     */
    public List<Announcement> findRootAccounceMent(){
        List<Announcement> rootAccounceMent = accouncementDao.findRootAccounceMentall();
        return rootAccounceMent;
    }
    /**
     * 修改状态
     */
    public Integer updateRootAccouncestatue(String ankey){
        Integer integer = accouncementDao.updateAnnounceStatue(ankey);
        return integer;
    }
    public Integer rootaddannounce(String atTitle,String atMessage){

        Random random = new Random();
        String result="";
        for (int i=0;i<8;i++)
        {
            result+=random.nextInt(10);
        }
        String atKey="AT"+result;
        Integer addannounce = accouncementDao.rootaddannounce(atKey, atTitle, atMessage);
        return addannounce;
    }
    public Rootuser findroot(String userkey){
        Rootuser rootuser = rootUserDao.rootfaceLogin(userkey);
        return rootuser;
    }
    public Integer updatepassword(String newpassword,String userkey){
        Integer updatepassword = rootUserDao.updatepassword(newpassword, userkey);
        return updatepassword;
    }




}
