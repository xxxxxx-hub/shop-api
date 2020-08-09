package com.fh.shop.api.category.controller;



import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.category.biz.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

        @GetMapping
    public ServerResponse queryCategoryList(){

           return categoryService.queryCategoryList();


    }

}
