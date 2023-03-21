package com.example.photo.controller;

import com.example.photo.Util.PhotoUtil;
import com.example.photo.dao.PhotoDao;
import com.example.photo.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoDao photoDao;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("photo") MultipartFile file) throws IOException {
        Photo ph = photoDao.save(Photo.builder()
                .type(file.getContentType())
                .photo(PhotoUtil.compressImage(file.getBytes())).build());
        if (ph != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("File upload successfully : " + file.getOriginalFilename());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Photo upload failed ");
        }
    }

    //get image by imageId
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int id)
    {
        Optional<Photo> ph =photoDao.findById(id);
        byte[] photo = PhotoUtil.decompressImage(ph.get().getPhoto());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(photo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<byte[]>> getAll(@PathVariable List<Photo> photos){
        List<Photo> photo = new ArrayList<>();
        List<byte[]> all = photoDao.listImage(photos);
        //byte[] photo = PhotoUtil.decompressImage(ph.get().getPhoto());
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

}
