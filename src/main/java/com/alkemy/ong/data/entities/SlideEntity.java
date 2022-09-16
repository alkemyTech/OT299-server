package com.alkemy.ong.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "slides")
@Data
@NoArgsConstructor
public class SlideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String slideText;
    private String slideOrder;

}
