package com.alkemy.ong.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET delete=true WHERE id=?")
@Where(clause = "false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String image;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    private boolean delete = false;
}
