package com.alkemy.ong.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comments SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class commentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private Integer userId;

    private String body;

    @Column(name = "id_news")
    private Integer newsId;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Boolean deleted = false;

}
