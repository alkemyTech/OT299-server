package com.alkemy.ong.data.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted =true WHERE id = ?")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Where(clause = "deleted = false")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String content;
    private String image;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean deleted = false;

}
