package com.example.demo.module.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long>, JpaSpecificationExecutor<MediaEntity> {

    MediaEntity getOneByUuid(String uuid);

}
