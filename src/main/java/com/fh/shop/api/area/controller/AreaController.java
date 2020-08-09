package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.IAreaService;
import com.fh.shop.api.area.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Resource(name = "areaService")
    private IAreaService areaService;

    @GetMapping
    public ServerResponse findChilds(Long  id){

        return areaService.findChilds(id);

    }

}
