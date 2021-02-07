package com.evalutaion.news.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "news_type")
public class NewsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @Column(name = "channel_id")
    private int channelId;


    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "active_status")
    private int status;


    @Column(name = "type")
    private int type;
}
