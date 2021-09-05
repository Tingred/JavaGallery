package com.example.demo.module.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "media")
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String fileName;
    private String originalFileName;

    public MediaEntity(String originalFilename){
        this.uuid = UUID.randomUUID().toString();
        this.fileName = Timestamp.valueOf(LocalDateTime.now()).toString();
        this.originalFileName = originalFilename;
    }
}
