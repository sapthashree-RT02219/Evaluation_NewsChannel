package com.evalutaion.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewsTypeDto {
    private int id ;

    private int channelId;


    private LocalDateTime date;

    private int status;


    private int type;

    private DTO typesofNews;
    private List<DetailsDTO> detailsDTOS;

}
