package com.fh.shop.api.category.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.category.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {



}
