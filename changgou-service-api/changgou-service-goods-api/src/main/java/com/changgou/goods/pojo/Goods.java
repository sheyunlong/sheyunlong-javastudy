package com.changgou.goods.pojo;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/25 11:48
 * @description 标题
 * @package com.changgou.goods.pojo
 */

public class Goods {
    private Spu spu;

    private List<Sku> skuList;

    public Goods() {
    }

    public Goods(Spu spu, List<Sku> skuList) {
        this.spu = spu;
        this.skuList = skuList;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}

