package com.example.demo.module.media;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    @Autowired
    private FileStorageService storageService;

    @GetMapping({"/media/{uuid}"})
    public void getFile(
            @PathVariable("uuid") String uuid,
            HttpServletResponse response) throws IOException {
        //path
        String fileFullPath = storageService.getFullPathFor(uuid);

        InputStream myStream = new FileInputStream(fileFullPath);

        response.addHeader("Content-disposition", "attachment;filename=" + fileFullPath);
        response.setContentType("image/png");
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @PostMapping("/uploadFile")
    public void save(
            @RequestParam("file") MultipartFile file) {
        storageService.store(file);
    }
}
