package com.example.demo.module.media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService storageService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/media/{uuid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StringResponse getFile(
            @PathVariable("uuid") String uuid) {
        return new StringResponse(storageService.getOneByUuid(uuid).getFileName());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/media",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllPhotos(){
        return storageService.list().stream().map(c -> storageService.getOneByUuid(c.getUuid()).getFileName()).collect(Collectors.toList());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional(rollbackFor = StorageException.class)
    @PostMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StringResponse save(@RequestParam("imageFile") MultipartFile file) {
        storageService.store(file);
        return new StringResponse("File saved");
    }
}
