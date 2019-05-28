package com.dk.wx.event;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "configinfo", urlPatterns = {"/configinfo"})
//@Controller
@RequestMapping("configinfo")
public class CoreServlet extends HttpServlet {

    private String token = "yijiance";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoreService coreService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter router;

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }


        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            out.print(echostr);
        }

        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

//路由
//        WxMpMessageHandler handler = new WxMpMessageHandler() {
//            @Override
//            public WxMpXmlOutMessage exception(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
//                WxMpXmlOutTextMessage m
//                        = WxMpXmlOutMessage.TEXT().content("测试加密消息").fromUser(wxMpXmlMessage.getToUser())
//                        .toUser(wxMpXmlMessage.getFromUser()).build();
//                return m;
//            }
//        };
//
//
//        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
//        WxMpXmlOutMessage outMessage = router.route(inMessage);

        // 调用核心业务类接收消息、处理消息
        String respXml = coreService.processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
//        out.print(outMessage.toXml());
        out.close();
    }

}
