package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/22 12:23
 * @description 标题
 * @package com.changgou.goods.service.impl
 */
@Service
public class BrandServiceImpl extends CoreServiceImpl<Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    //注解可以修饰在构造函数中
    //当修饰的构造函数的参数类型 spring容器有该参数类型的BEAN 就会进行注入

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper) {
        super(brandMapper, Brand.class);
        //this.brandMapper=brandMapper;
    }




}
