//package com.store.velvetbloom.controller;
//
//import com.store.velvetbloom.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/images")
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        // Call the image service to upload and store the image
//        return imageService.uploadImage(file.getInputStream(), file.getOriginalFilename());
//    }
//    
//    
//}

package com.store.velvetbloom.controller;

import com.store.velvetbloom.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // Call the image service to upload and store the image
        String imgUrl = imageService.uploadImage(file.getInputStream(), file.getOriginalFilename());

        // Prepare JSON response
        Map<String, String> response = new HashMap<>();
        response.put("imgUrl", imgUrl);

        // Return JSON response
        return ResponseEntity.ok(response);
    }
}
