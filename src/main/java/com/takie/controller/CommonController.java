/**
 * FileName: CommonController
 * Author: jane
 * Date: 2022/7/28 17:25
 * Description: upload and download file
 * Version:
 */

package com.takie.controller;

import com.takie.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${takie.path}")
    private String basePath;

    /**
     *
     * @param file param name must be "file" to response with front end
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //use UUID to recreate a filename
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID().toString() + suffix;
        //"file" is a temporary file, which needs to be transferred to certain location. Otherwise it would be lost after this connection
        //create a catalogue object
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {//inputstream reads file content
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //outputstram output file
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len  = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
