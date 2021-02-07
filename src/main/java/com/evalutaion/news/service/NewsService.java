package com.evalutaion.news.service;


import com.evalutaion.news.dto.BookMarkDTO;
import com.evalutaion.news.dto.DTO;
import com.evalutaion.news.dto.NewsDTO;
import com.evalutaion.news.dto.NewsTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {



    Page<NewsDTO> getNews(Pageable pageable);

    List<NewsTypeDto> getDetailedNewsById(int id);

    List<NewsTypeDto> getDetailedNewsByTypeAndId(int type,int id);

    List<BookMarkDTO> getbookmarks();
}
