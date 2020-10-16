package com.jaeyeon.book.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class UploadFileUtil {

    public static final String PRODUCT_UPLOAD_IMAGE = "product-upload-image";
    public static final String REVIEW_UPLOAD_IMAGE = "review-upload-image";

    public static String fileSave(String uploadPath, MultipartFile file) throws IllegalStateException, IOException {
        File uploadPathDir = new File(uploadPath);

        if (!uploadPathDir.exists()){
            uploadPathDir.mkdirs();
        }

        // 파일 중복명 처리
        String genId = UUID.randomUUID().toString();
        genId = genId.replace("-", "");

        String originalfileName = file.getOriginalFilename();
        String fileExtension = getExtension(originalfileName);
        String saveFileName = genId + "." + fileExtension;

        // String savePath = calcPath(uploadPath);

        File target = new File(uploadPath, saveFileName);

        FileCopyUtils.copy(file.getBytes(), target);
        return makeFilePath(uploadPath, saveFileName);
    }

    // 파일의 확장자 반환
    private static String getExtension(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');

        if (-1 != dotPosition && fileName.length() - 1 > dotPosition) {
            return fileName.substring(dotPosition + 1);
        } else {
            return "";
        }
    }

    private static void makeDir(String uploadPath, String... paths) {

        System.out.println(paths[paths.length - 1] + " : " + new File(paths[paths.length - 1]).exists());
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    private static String makeFilePath(String uploadPath, String fileName) throws IOException {
        String filePath = uploadPath + File.separator + fileName;

        return filePath.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
}