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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    @Value("B:/GalleryApp/AngularGallery/src/assets")
    private String path;

    @Autowired
    private MediaRepository mediaRepository;

    public void store(MultipartFile file) {
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(Timestamp.valueOf(LocalDateTime.now()));
        String fileExtension = Arrays.stream(file.getOriginalFilename()
                        .split("."))
                        .skip(1)
                        .map(s -> "."+s)
                        .collect(Collectors.joining());
        try {
            Path destinationFile = Paths.get(path)
                    .resolve(Paths.get(path+"/"+newFileName+ fileExtension))
                    .normalize()
                    .toAbsolutePath()
                    ;
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            mediaRepository.saveAndFlush(
                    new MediaEntity(file.getOriginalFilename(),newFileName+fileExtension)
            );
        }catch (Exception e){
            throw new StorageException("Failed to store file",e);
        }
    }
    public List<MediaEntity> list(){
        return mediaRepository.findAll();
    }

    public MediaEntity getOneByUuid(String uuid) {
        return  mediaRepository.getOneByUuid(uuid);
    }
}
