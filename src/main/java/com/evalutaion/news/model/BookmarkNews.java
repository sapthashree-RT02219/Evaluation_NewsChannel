package com.evalutaion.news.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "bookmark_news")
public class BookmarkNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private int type;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private java.sql.Blob thumbnail;

}
