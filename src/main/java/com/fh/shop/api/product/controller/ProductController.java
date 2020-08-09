package com.fh.shop.api.product.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.product.biz.IProductService;
import com.fh.shop.api.product.po.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "用户管理类")
@RequestMapping("/products")
public class ProductController {

    @Resource(name = "productService")
    private IProductService productService;


    @RequestMapping("findHotList")
    @Check
    @ApiOperation(value = "查询全部用户",notes = "默认根据升序查询全部用户信息")
    public ServerResponse findHotList(){

        return productService.findHotList();
    }

    @RequestMapping("findSrockLess")
    public List<Product> findSrockLess(){

       return productService.findSrockLess();
    }
}
