package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    // 文件路径
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file 文件
     * @return string
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //file 是一个临时文件，需要转存到指定位置，否则请求完成后临时文件会删除
        //log.info("file:{}",file.toString());

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
        //使用UUID随机生成文件名，防止因为文件名相同造成文件覆盖
        String fileName = UUID.randomUUID() + suffix;
        //创建一个目录对象
        File file1 = new File(basePath);
        File file2 = new File(file1.getAbsolutePath(), fileName);
        //判断当前目录是否存在
        if (!file2.exists()){
            //目录不存在
            file2.mkdirs();
        }
        //将临时文件转存到指定位置
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response) {

        //输入流，通过输入流读取文件内容
        try {
            FileInputStream inputStream = new FileInputStream(new File(basePath + name));
            //输出流，通过输出流将文件写回浏览器，在浏览器中展示图片
            ServletOutputStream servletInputStream = response.getOutputStream();

            // 设置流管道
            int len = 0;
            byte[] bytes = new byte[1024];
            if ((len = inputStream.read(bytes)) != -1){
                // 写入到输出流中
                servletInputStream.write(bytes);
                // 冲洗输出流管道
                servletInputStream.flush();
            }
            inputStream.close();
            servletInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
