package org.zgg.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zgg.publisher.bean.NameValue;
import org.zgg.publisher.service.PublisherService;

import java.util.List;
import java.util.Map;

@RestController
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    /**
     * 日活分析
     * @param td
     * @return
     */
    @GetMapping("dauRealtime")
    public Map<String, Object> dauRealtime(@RequestParam("td") String td){

        Map<String, Object> result = publisherService.doDauRealTime(td);
        return result;
    }

    /**
     * 交易分析 - 按照类别(年龄、性别)统计
     *
     * http://bigdata.gmall.com/statsByItem?itemName=小米手机&date=2021-02-02&t=age
     * http://bigdata.gmall.com/statsByItem?itemName=小米手机&date=2021-02-02&t=gender
     */
    @GetMapping("statsByItem")
    public List<NameValue> statsByItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("date") String date,
            @RequestParam("t") String t){
        List<NameValue> results = publisherService.doStatsByItem(itemName,date,t);

        return results;
    }

    /**
     *
     * 交易分析 - 明细
     * http://bigdata.gmall.com/detailByItem?date=2021-02-02&itemName=小米手机&pageNo=1&pageSize=20
     */
    @GetMapping("detailByItem")
    public Map<String, Object> detailByItem(@RequestParam("date") String date ,
                                            @RequestParam("itemName") String itemName ,
                                            @RequestParam(value ="pageNo" , required = false  , defaultValue = "1") Integer pageNo ,
                                            @RequestParam(value = "pageSize" , required = false , defaultValue = "20") Integer pageSize){
        Map<String, Object> results =  publisherService.doDetailByItem(date, itemName, pageNo, pageSize);
        return results ;
    }

}
