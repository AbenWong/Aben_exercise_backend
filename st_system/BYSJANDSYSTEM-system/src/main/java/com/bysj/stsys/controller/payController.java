package com.bysj.stsys.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bysj.stsys.config.AlipayConfig;
import com.bysj.stsys.model.User;
import com.bysj.stsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class payController {
    @Autowired
    UserService userService=null;

    @ResponseBody
    @RequestMapping("/pay")
    public String payController(String out_trade_no,String subject,String total_amount,
                                String body,String timeout_express,String product_code) throws AlipayApiException {
        System.out.println(out_trade_no);
        System.out.println(subject);
        System.out.println(total_amount);
        System.out.println(body);
        System.out.println(timeout_express);
        System.out.println(product_code);

        AlipayConfig alipayConfig = new AlipayConfig();
        //请求网关 收款人id 支付宝密钥 返回格式 字符编码格式 支付宝公钥 加密方式
        AlipayClient alipayClient=new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,AlipayConfig.FORMAT,AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.sign_type);
        //创建request请求
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        //商品单号
        model.setOutTradeNo(out_trade_no);
        //商品名称
        model.setSubject(subject);
        //金额
        model.setTotalAmount(total_amount);
        //商品描述
        model.setBody(body);
        //支付超时时间
//        model.setTimeoutExpress(timeout_express);
        //产品id
        model.setProductCode(product_code);
        request.setBizModel(model);
        //设置异步地址
        request.setNotifyUrl(AlipayConfig.notify_url);
        //设置同步地址
        request.setReturnUrl(AlipayConfig.return_url);
        //生成表单
        String form= alipayClient.pageExecute(request).getBody();

        return form;
    }
    @ResponseBody
    @RequestMapping("/alipay")
    public   void   doPost (HttpServletRequest httpRequest,
                            HttpServletResponse httpResponse,
                            String out_trade_no,
                            String total_amount,
                            HttpSession session)   throws ServletException, IOException {
        //把充值的金额存入session
        session.setAttribute("saveMoney",total_amount);
        AlipayConfig alipayConfig=new AlipayConfig();
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2021000117638572", AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/paysuccess");
        alipayRequest.setNotifyUrl("http://localhost:8080/paysuccess"); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+out_trade_no+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+total_amount+","  +
                "    \"subject\":\"社团账户充值\","  +
                "    \"body\":\"社团账户充值\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    @RequestMapping("/paysuccess")
    public ModelAndView paysuccess(HttpSession session, ModelAndView mv){
        String AccountKey=session.getAttribute("accountKey").toString();
        String saveMoney=session.getAttribute("saveMoney").toString();
        User userinf = (User)session.getAttribute("userinf");
        Integer integer = userService.topUp(AccountKey, saveMoney,userinf.getUserStid());
        if(integer==1){
            session.setAttribute("topupflag","1");
        }else {
            session.setAttribute("topupflag","2");
        }
        mv.setViewName("redirect:userSt/showMyaccount");
        return mv ;
    }

}

