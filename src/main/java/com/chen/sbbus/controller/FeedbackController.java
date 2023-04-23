package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Feedback;
import com.chen.sbbus.service.FeedbackService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    //指定本地文件夹存储图片，写到需要保存的目录下
    String filePath = "D:\\学习\\stm32\\sbBus\\src\\main\\resources\\static\\feedbackImgs\\";

    @GetMapping
    public R getAllFeedbackList(){
        return new R(true,feedbackService.getAllFeedbackList());
    }
    @GetMapping("/{id}")
    public R getFeedbackListById(@PathVariable("id")Integer id){
        return new R(true,feedbackService.getFeedbackListById(id));
    }

    @GetMapping("/driver/{id}")
    public R getFeedbackListByDriverId(@PathVariable("id")Integer id){
        return new R(true,feedbackService.getFeedbackListByDriverId(id));
    }

    @PostMapping("/insert")
    public R insertFeedback(@RequestBody Feedback feedback){
        System.out.println(feedback.toString());;
        return new R(true,feedbackService.insertFeedback(feedback));
    }
    /*
    * 上传图片
    * */
    @PostMapping("/upload")
    public R upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        System.out.println("fileName:"+fileName);
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            //返回提示信息
            return new R(true,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new R(false);
        }
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) throws IOException {
        Path path = Paths.get(filePath + filename);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
