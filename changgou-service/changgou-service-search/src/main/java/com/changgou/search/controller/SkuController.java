package com.changgou.search.controller;

import com.changgou.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SkuController {
    @Autowired
    private SkuService skuService;

    //从数据库查询出来在导入数据ES中
    @GetMapping("/import")
    public Result importToES(){
        //1.调用feign 查询商品微服务的符合条件的sku的数据集合
        //2.将数据存储到es服务器中
        skuService.importSku();
        //3.返回
        return new Result(true, StatusCode.OK,"导入成功");
    }

    /*
    * 根据条件查询数据
    * searchMap搜索的条件封装对象 包含要搜索的关键字，品牌名，规格选项，分类。。。。
    * 封装的数据对象map里面包含（当前页的记录）
    */
    @PostMapping
    public Map<String,Object> search(@RequestBody Map<String,String> searchMap){
        return skuService.search(searchMap);
    }
}
