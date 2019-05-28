package com.dk.wx.event;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dk.data.dao.WxUserMapper;
import com.dk.data.entity.WxUser;
import com.dk.wx.po.TextMessage;
import com.dk.wx.util.Constant;
import com.dk.wx.util.MessageUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Service("coreService")
public class CoreService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private WxMpService wxMpService;

    public static Logger log = LoggerFactory.getLogger(CoreService.class);

    private static MessageUtil messageUtil = MessageUtil.getInstance();


//    @Autowired
//    private SmcyService smcyService;

    public String processRequest(HttpServletRequest request) {

        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = messageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");

            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
//            textMessage.setFuncFlag(0);

            String respContent = "";

            // 文本消息
            if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");

                /*// 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                // 单图文消息
                if ("1".equals(content)) {
                    Article article = new Article();
                    article.setTitle("我是一条单图文消息");
                    article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");
                    article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");
                    article.setUrl("http://tuposky.iteye.com");
                    articleList.add(article);

                    // 设置图文消息个数
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合

                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串
                    respMessage = messageUtil.newsMessageToXml(newsMessage);
                }
                // 多图文消息
                else if ("3".equals(content)) {

                    Article article1 = new Article();
                    article1.setTitle("我是一条多图文消息");
                    article1.setDescription("");
                    article1.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20130913/2013091314543416032.jpg");
                    article1.setUrl("http://tuposky.iteye.com/blog/2008583");

                    Article article2 = new Article();
                    article2.setTitle("微信公众平台开发教程Java版（二）接口配置 ");
                    article2.setDescription("");
                    article2.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20131021/2013102111243367254.jpg");
                    article2.setUrl("http://tuposky.iteye.com/blog/2008655");

                    Article article3 = new Article();
                    article3.setTitle("微信公众平台开发教程Java版(三) 消息接收和发送");
                    article3.setDescription("");
                    article3.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20131021/2013102111291287031.jpg");
                    article3.setUrl("http://tuposky.iteye.com/blog/2017429");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    newsMessage.setArticleCount(articleList.size());

                    newsMessage.setArticles(articleList);
                    respMessage = messageUtil.newsMessageToXml(newsMessage);
                }*/

                //事件处理开始
            } else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");

                if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {

                    String lang = "zh_CN"; //语言
                    WxMpUser wxMpUser = wxMpService.getUserService().userInfo(fromUserName, lang);
                    WxUser wxUser = new WxUser();
                    BeanUtil.copyProperties(wxMpUser, wxMpUser);

                    QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
                    queryWrapper.eq("openid", wxMpUser.getOpenId());
                    WxUser wxUser1 = wxUserMapper.selectOne(queryWrapper);


                    if (wxUser1 == null) {
                        //userMapper.insert(user);
                        wxUserMapper.insert(wxUser);
                    } else {
                        UpdateWrapper<WxUser> updateWrapper = new UpdateWrapper<WxUser>();
                        updateWrapper.eq("openid", wxMpUser.getOpenId());
                        wxUserMapper.update(wxUser, updateWrapper);
                    }


                    // 关注
                    respContent = "感谢您关注偶\n";
                    respContent = respContent + "易检测致力于在生命健康领域打造全国专业的、一流的互联网医学检验服务平台。我们为\n" +
                            "\n" +
                            "三甲医院提供检验项目信息发布；为用户提供在线支付、预约服务、上门采样以及陪诊服务、\n" +
                            "\n" +
                            "电子报告及时推送等一站式服务；协助基层医院扩展检验领域，丰富检验项目。";

                } else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) {
                    // 取消关注,用户接受不到我们发送的消息了，可以在这里记录用户取消关注的日志信息
                    //User userDB = userMapper.selectByOpenId(fromUserName);
                    QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
                    queryWrapper.eq("openid", fromUserName);
                    WxUser wxUser2 = wxUserMapper.selectOne(queryWrapper);
                    if (wxUser2 != null) {
                        wxUser2.setSubscribe(0);
                        //userMapper.updateByPrimaryKey(userDB);
                        wxUserMapper.updateById(wxUser2);
                    }

                } else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {

                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");

                    // 自定义菜单点击事件
                    if (eventKey.equals("11")) {
                        respContent = "天气预报菜单项被点击！";
                    } else if (eventKey.equals("12")) {
                        respContent = "公交查询菜单项被点击！";
                    }
                }
                textMessage.setContent(respContent);

                respMessage = messageUtil.textMessageToXml(textMessage);
                System.out.println(textMessage);
                System.out.println(respMessage);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }



}
