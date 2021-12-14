package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 操作ES
 * @author ljh
 * @version 1.0
 * @date 2020/12/28 11:53
 * @description 标题
 * @package com.changgou.search
 */
//@Repository//@componse @controller @service @Repository 可以不用加
// SkuInfo,Long  skuinfo 标识要操作到的POJO --》映射到es
// Long 指定POJO的文档的唯一标识数据类型
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
