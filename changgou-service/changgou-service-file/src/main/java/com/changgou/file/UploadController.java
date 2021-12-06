package com.changgou.file;

import com.changgou.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/23 12:09
 * @description 标题
 * @package com.changgou.file
 */
@RestController

public class UploadController {

    @Value("${pic.url}")
    private String url;


    /**
     * 上传图片  接收页面传递过来的文件对象
     * 返回一个图片的地址
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
        if (!file.isEmpty()) {
            //1.获取文件的字节数组
            byte[] bytes = file.getBytes();
            //2.获取文件的名称 （按需求获取）
            String originalFilename = file.getOriginalFilename();//45.png
            //3.获取文件的扩展名
            String extName = StringUtils.getFilenameExtension(originalFilename);

            //4.使用工具类 上传图片到fastdfs

            // strings[0]=group1
            //strings[1]=M00/00/00/wKjThF_jNZmAQmfrAAAl8vdCW2Y668.png
            //访问图片的路径为：http://192.168.211.132:8080/group1/M00/00/00/wKjThF_jJ0iAXz7wAAAl8vdCW2Y421.png
            String[] strings = FastDFSClient.upload(new FastDFSFile(originalFilename,bytes,extName));

            //5.拼接Url 返回给页面
            String realPath=url+"/"+strings[0]+"/"+strings[1];
            return realPath;
        }
        return null;
    }
}
