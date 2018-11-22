package com.justl.controller;

import com.justl.domain.response.ResponseData;
import com.justl.service.AppService;
import com.justl.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private  AppService appService;
    @RequestMapping("/btn")
    @ResponseBody
    public ResponseData btn(){
        List list = appService.queryBtns();
        ListUtils listu=new ListUtils(list.size(),list);
        return new ResponseData(listu);
    }

    @RequestMapping("/Models")
    @ResponseBody
    public ResponseData Models(){
        List list = appService.queryModels();
        ListUtils listu=new ListUtils(list.size(),list);
        return new ResponseData(listu);
    }

    @RequestMapping("/Income")
    @ResponseBody
    public ResponseData Income(){
        List list = appService.queryIncome();
        ListUtils listu=new ListUtils(list.size(),list);
        return new ResponseData(listu);
    }
}
