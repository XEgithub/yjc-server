package com.dk.controller.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.SearchCarouselDto;
import com.dk.data.entity.Carousel;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CarouselController
 * @Description
 * @Author zyf
 * @Date 2018/12/4 10:24
 **/
@RestController
@RequestMapping("rest/carousel")
public class CarouselRestController {

    @Autowired
    private CarouselService carouselService;

    /*
     * @Description 获取轮播图列表
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WrappedResponse list(SearchCarouselDto condition) {
        WrappedResponse result = new WrappedResponse();
        condition.setAlone(Byte.valueOf("0"));
        IPage<Carousel> carouselList = carouselService.findAll(condition);
        if (carouselList.getRecords().size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(carouselList);
        return result;
    }

    /*
     * @Description 获取静态活动图
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/static", method = RequestMethod.GET)
    public WrappedResponse statical(SearchCarouselDto condition) {
        WrappedResponse result = new WrappedResponse();
        condition.setAlone(Byte.valueOf("1"));
        IPage<Carousel> carouselList = carouselService.findAll(condition);
        if (carouselList.getRecords().size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(carouselList.getRecords().get(0));
        return result;
    }

}
