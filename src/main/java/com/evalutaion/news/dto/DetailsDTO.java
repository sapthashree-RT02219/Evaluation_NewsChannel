package com.evalutaion.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDTO {
    private int id;


    private String title;


  //  private int type;

    private String description;


    private java.sql.Blob thumbnail;
}
