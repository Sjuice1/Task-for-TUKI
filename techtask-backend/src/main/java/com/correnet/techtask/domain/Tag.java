package com.correnet.techtask.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "correnet_task_schema", name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;


}
