package com.dk.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpConfiguration {

  @Autowired
  private WechatMpProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public WxMpConfigStorage configStorage() {
    WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
    configStorage.setAppId(this.properties.getAppId());
    configStorage.setSecret(this.properties.getSecret());
    configStorage.setToken(this.properties.getToken());
    configStorage.setAesKey(this.properties.getAesKey());
    return configStorage;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService(WxMpConfigStorage configStorage) {
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.okhttp.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.jodd.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.apache.WxMpServiceImpl();
    WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
    WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
    return wxMpMessageRouter;
  }

}
