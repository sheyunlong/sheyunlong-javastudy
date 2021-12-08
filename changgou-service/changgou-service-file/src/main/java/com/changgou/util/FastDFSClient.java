package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 工具类用于 图片的操作 CURD
 *
 * @author ljh
 * @version 1.0
 * @date 2020/12/23 11:43
 * @description 标题
 * @package com.changgou.util
 */
public class FastDFSClient {

    static {
        ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
        String path = classPathResource.getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    //上传图片
    public static String[] upload(FastDFSFile file) throws Exception {
        //1.创建一个配置文件 用于配置tracker_server的ip和端口
        //2.加载配置文件生效

        //3.创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4.获取trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //6.执行一个方法（上传图片的方法）storageClient 提供了许多操作文件的方法
        //参数1 指定要上传文件的本地的路径
        //参数2 指定文件的扩展名 不要带 “.”
        //参数3 指定元数据（图片的作者，拍摄日期 像素 。。。。。。）
        NameValuePair[] meta_list = new NameValuePair[]{
                new NameValuePair(file.getName())//文件名作为元数据
        };

        //String[]  args = new String[]{new String("1"),new String("2")};


        String[] pngs = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        for (String png : pngs) {
            System.out.println(png);
        }
        return pngs;
    }

    //下载图片

    public static byte[] downFile(String groupName, String remoteFileName) throws Exception {
        //1.创建一个配置文件 用于配置tracker_server的ip和端口
        //2.加载配置文件生效
        //3.创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4.获取trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);

        byte[] bytes = storageClient.download_file(groupName, remoteFileName);

        return bytes;
    }

    //删除图片
    public static boolean deleteFile(String groupName, String remoteFileName) throws Exception {
        //1.创建一个配置文件 用于配置tracker_server的ip和端口
        //2.加载配置文件生效
        //3.创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4.获取trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);

        int i = storageClient.delete_file(groupName, remoteFileName);

        return i == 0;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获得TrackerServer信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取StorageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }


}
