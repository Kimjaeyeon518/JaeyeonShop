package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.domain.FileModel;
import com.jaeyeon.book.springboot.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadFileController {
    private static String uploadedFolder = "C:\\Users";
    @Autowired
    FileRepository fileRepository;

    @GetMapping("/uploadFileForm")
    public String index() {
        return "product/uploadFile";
    }

    @PostMapping("/uploadFile")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile file, Model model) {
        String fileNames ="";

        try {
            FileModel storedFile;
            String fileInfo = uploadedFolder + file.getOriginalFilename();
                FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());
                if (fileModel != null) {
                    // update new contents
                    fileModel.setPic(file.getBytes());
                } else {
                    fileModel = new FileModel(file.getOriginalFilename(),fileInfo, file.getContentType(), file.getBytes());
                }

                fileNames = file.getOriginalFilename();
                storedFile = fileModel;

            // Save all Files to database
            fileRepository.save(storedFile);

            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
        } catch (Exception e) {
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
        }

        return "product/uploadFile";
    }
}
