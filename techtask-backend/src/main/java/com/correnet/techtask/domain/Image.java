package com.correnet.techtask.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(schema = "correnet_task_schema", name = "image")
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "image",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Tag> tags;
}
