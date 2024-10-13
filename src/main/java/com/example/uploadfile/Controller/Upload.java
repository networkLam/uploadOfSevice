package com.example.uploadfile.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/test")
@Slf4j
public class Upload {

    @CrossOrigin("*")
    @GetMapping("/hello")
    public String hello(){
        return "hello springboot";
    }

    @CrossOrigin("*") //配置该接口允许跨域
    @PostMapping("/upload")
    public String upload(String name, MultipartFile multipartFile) throws IOException {
        log.info("name:{}",name);
        InputStream inputStream = multipartFile.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); //搞一个输出流，把文件内容读取到这里面来
        byte[] buffer = new byte[1024]; //每次读1024个字节
        int bytesRead;
        while((bytesRead = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,bytesRead);
        }
        String fileContent = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        log.info("file content is {}",fileContent);
        return "test";
    }
}
