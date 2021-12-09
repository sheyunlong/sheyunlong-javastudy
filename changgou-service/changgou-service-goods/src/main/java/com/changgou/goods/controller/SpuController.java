package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController extends AbstractCoreController<Spu>{

    private SpuService  spuService;

    @Autowired
    public SpuController(SpuService  spuService) {
        super(spuService, Spu.class);
        this.spuService = spuService;
    }

    //保存数据到 sku 和spu表中
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        spuService.save(goods);
        return Result.ok();
    }


    /**
     * 获取spu的数据和skuList数据组合在GOODS返回
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable(name="id") Long id){
        Goods goods = spuService.findGoodsById(id);
        return new Result<Goods>(true, StatusCode.OK,"查询成功",goods);
    }
}
