package com.changgou.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.content.feign.ContentFeign;
import com.changgou.content.pojo.Content;
import com.xpand.starter.canal.annotation.*;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@CanalEventListener//标识该类就是一个监听类 用于监听数据的变化
public class CanalDataEventListener {

    /*@InsertListenPoint//发生insert的时候触发该方法执行
    public void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //do something...
    }*/

    /*@UpdateListenPoint//发生Update的时候触发该方法执行
    public void onEvent1(CanalEntry.RowData rowData) {
        //1.获取更新之前的数据
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            System.out.println(column.getName()+":"+column.getValue());
        }
        //2.更新之后的数据
        System.out.println("==============风格符号================");
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            System.out.println(column.getName()+":"+column.getValue());
        }
    }*/

   /* @DeleteListenPoint ///发生Delete的时候触发该方法执行
    public void onEvent3(CanalEntry.EventType eventType) {
        //do something...
    }*/

    /**
     *
     *
     * 当 数据库changgou_content 下的tb_content发生了变化
     * 监听到 被修改 或者被删除 或者 被新增的那行数据对应的category_id列名的值
     * 调用广告微服务 实现查询广告分类ID 对应的广告列表数据
     * 将广告列表数据 存储到redis中
     * @param eventType
     * @param rowData
     */

    // destination 指定的是目的地 和canal-server中的服务器中的example目录一致
    // schema 指定要监听的数据库的库名
    // table 指定要监听的表名
    // eventType 指定事件类型（当发生了insert delete update的时候处理）


    @Autowired
    private ContentFeign contentFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @ListenPoint
            (destination = "example",
                    schema = "changgou_content",
                    table = {"tb_content", "tb_content_category"},
                    eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.DELETE, CanalEntry.EventType.INSERT}
            )
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.获取category_id的值
        String categoryId = getColumnValue(eventType, rowData);
        //2.通过feign调用广告微服务（根据分类的ID 获取该分类下的所有的广告列表数据）
            //2.1 在 changgou-service-content-api中添加起步依赖 //ctr + f11
            //2.2 创建一个接口 定义方法 添加注解@feignclient注解
            //2.3 在content微服务中实现【业务接口】--》controller service dao
            //2.4 在changgou-service-canal 启用Enablefeignclients
            //2.5 注入一个接口即可
        Result<List<Content>> result = contentFeign.findByCategory(Long.valueOf(categoryId));
        List<Content> contentList = result.getData();
        //3.将数据存储到redis中
            //3.1 加入redis的起步依赖spring boot data redis starter
            //3.2 配置redis的链接到的服务端的ip和端口
            //3.3 注入StringRedisTemplate
            //3.3 执行set key value  key:
        stringRedisTemplate.boundValueOps("content_"+categoryId).set(JSON.toJSONString(contentList));
    }

    //获取category_id的值
    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String categoryId = "";
        if(eventType== CanalEntry.EventType.DELETE) {
            //1.如果是delete 获取before的数据
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                if (column.getName().equals("category_id")) {
                    categoryId= column.getValue();//分类的iD
                    break;
                }
            }
        }else{
            //2.判断如果是insert 和 update 那么获取after的数据
            List<CanalEntry.Column> AfterColumnsList = rowData.getAfterColumnsList();
            for (CanalEntry.Column column : AfterColumnsList) {
                if (column.getName().equals("category_id")) {
                    categoryId= column.getValue();//分类的iD
                    break;
                }
            }
        }
        //3.获取行中的category_id的值 返回
        return categoryId;
    }

}