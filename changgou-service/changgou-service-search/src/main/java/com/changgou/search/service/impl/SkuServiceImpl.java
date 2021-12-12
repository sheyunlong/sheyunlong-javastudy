package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Override
    public void importSku() {
        //1.调用feign 查询商品微服务的符合条件的sku的数据集合
        //1.1在changgou-service-api中创建一个接口 业务接口
        //1.2定义方法 和添加注解 方法：根据状态进行查询符合条件的sku的数据列表
        //1.3在changgou-service-goods微服务实现业务接口（编写controller service dao）
        //1.4添加changgou-service-goods-api的依赖启动类中启用feignclients
        //1.5注入接口
        Result<List<Sku>> result=skuFeign.findByStatus("1");
        List<Sku> skuList=result.getData();
        //先将pojo 转成JSON 再将JSON 转成pojo 类型不同
        List<SkuInfo> skuInfoList= JSON.parseArray(JSON.toJSONString(skuList),SkuInfo.class);
        //2.将数据存储到es服务器中
        skuEsMapper.saveAll(skuInfoList);
    }
}
