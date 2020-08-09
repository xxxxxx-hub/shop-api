package com.fh.shop.api.category.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_category")
public class Category {

    private Long id;

    private String categoryName;

    private Long pid;

    private Integer type;



}
