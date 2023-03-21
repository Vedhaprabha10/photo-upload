package com.example.photo.dao;

import com.example.photo.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhotoDao extends JpaRepository<Photo,Integer> {
    @Query(value = "select * FROM allowances where status=true", nativeQuery = true)
    List<byte[]> listImage(List<Photo> photos);
}
