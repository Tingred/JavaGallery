package com.example.demo.module.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileStorageService {

    @Value("b:/GalleryApp/tmp")
    private String path;

    @Autowired
    private MediaRepository mediaRepository;

    public void store(MultipartFile file) {
        try {
            Path destinationFile = Paths.get(path)
                    .resolve(Paths.get(file.getOriginalFilename()))
                    .normalize()
                    .toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            mediaRepository.saveAndFlush(
                    new MediaEntity(file.getOriginalFilename())
            );
        }catch (Exception e){
            throw new StorageException("Failed to store file",e);
        }
    }
    public List<MediaEntity> list(){return mediaRepository.findAll();}

    public String getFullPathFor(String uuid) {
        MediaEntity media =mediaRepository.getOneByUuid(uuid);
        return String.format("%$/%$",path,media.getFileName());
    }
}
