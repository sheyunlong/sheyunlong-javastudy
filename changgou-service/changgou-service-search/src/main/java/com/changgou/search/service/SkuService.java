package com.changgou.search.service;

import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/28 12:03
 * @description 标题
 * @package com.changgou.search.service
 */
public interface SkuService {
    void importSku();

    /**
     * 根据条件执行搜索
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map<String, String> searchMap);
}
