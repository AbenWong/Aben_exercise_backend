package com.bysj.stsys.controller;


import com.bysj.stsys.Utils.Base64Util;
import com.bysj.stsys.model.Stinf;
import com.bysj.stsys.model.User;
import com.bysj.stsys.service.RootService;
import com.bysj.stsys.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/fileservice")
public class fileController {
    @Autowired
    UserService userService=null;
    @Autowired
    RootService rootService=null;
    @RequestMapping("/uploadfile")
    public String uploadFile(MultipartFile file, HttpSession session) throws IOException {
        //获取文件信息
        //1获取文件原始名称
        String oldFilename = file.getOriginalFilename();
        //2生成新的文件名称
        String newFilename= UUID.randomUUID().toString().replaceAll("-","");
        //获取文件后缀名
        String extension = "."+FilenameUtils.getExtension(file.getOriginalFilename());
        newFilename=newFilename+extension;
        //文件的大小
        long size = file.getSize();
        //文件的类型
        String contentType = file.getContentType();
        //处理文件上传e传到static中
        //String realPath = request.getSession().getServletContext().getRealPath(File.separator);
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/Files/touxiang";
        //根据日期生成目录
//        String dateDirpath=realPath+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String encode = Base64Util.encode(file.getBytes());
//        File dateDir=new File(realPath);
//        if(!dateDir.exists())dateDir.mkdirs();
//        System.out.println(dateDir.toString());
//        file.transferTo(new File(dateDir,newFilename));
        //写入数据库
        //数据库中存储的文件名
        String Datebasepath="/Files/touxiang/"+newFilename;
        User userinf = (User)session.getAttribute("userinf");

        Integer updatetouxiang = userService.updatetouxiang(userinf.getUserStid(), "data:image/jpg;base64,"+encode);

        return "redirect:/userSt/showMyinf";
    }
    @RequestMapping("/uploadStlogo")
    public String uoloadStlogo(MultipartFile file, Model model, HttpSession session, String Stkey) throws IOException {
        //获取文件信息
        //1获取文件原始名称
        String oldFilename = file.getOriginalFilename();
        //2生成新的文件名称
        String newFilename= UUID.randomUUID().toString().replaceAll("-","");
        //获取文件后缀名
        String extension = "."+FilenameUtils.getExtension(file.getOriginalFilename());
        newFilename=newFilename+extension;
        //文件的大小
        long size = file.getSize();
        //文件的类型
        String contentType = file.getContentType();
        //处理文件上传传到static中
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/Files/stlogo";
        //根据日期生成目录
//        String dateDirpath=realPath+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String encode = Base64Util.encode(file.getBytes());
//        File dateDir=new File(realPath);
//        if(!dateDir.exists())dateDir.mkdirs();
//        System.out.println(dateDir.toString());
//        file.transferTo(new File(dateDir,newFilename));
        //写入数据库
        //数据库中存储的文件名
        String Datebasepath="/Files/stlogo/"+newFilename;
        Integer integer = rootService.uploadStlogo(Stkey, "data:image/jpg;base64,"+encode);
        Stinf stinf = rootService.finStinf(Stkey);
        Integer stPeopleNume = rootService.StAllNumber(Stkey);
        model.addAttribute("stPeopleNume",stPeopleNume);
        model.addAttribute("stinf",stinf);
        return "Root/ShowStinf";
    }
}
