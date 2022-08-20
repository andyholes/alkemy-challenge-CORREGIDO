package com.alkemy.challenge.service;

import com.alkemy.challenge.domain.Image;
import com.alkemy.challenge.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService{

    @Autowired
    ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws IOException{
        if(file != null){
            try{
                Image image = new Image();
                image.setName(file.getName());
                image.setMime(file.getContentType());
                image.setContent(file.getBytes());
                imageRepository.save(image);
                return image;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
