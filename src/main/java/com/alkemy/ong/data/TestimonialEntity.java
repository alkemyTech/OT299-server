package com.alkemy.ong.data;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "testimonials")
@SQLDelete(sql= "UPDATE testimonials SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class TestimonialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String image;

    private String content;

    //Timestamps
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    //Delete property
    private Boolean deleted = false;
}
