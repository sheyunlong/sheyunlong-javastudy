package com.changgou.search.controller;

import com.changgou.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
