package com.fh.shop.api.category.biz;



import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.category.mapper.CategoryMapper;
import com.fh.shop.api.category.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public ServerResponse queryCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return ServerResponse.success(categoryList);
    }


}
