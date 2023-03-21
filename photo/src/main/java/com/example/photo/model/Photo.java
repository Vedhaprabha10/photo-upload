package com.example.photo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String type;
    @Lob
    @Column(name="photo",length= 100000000)
    private byte[] photo;

}
