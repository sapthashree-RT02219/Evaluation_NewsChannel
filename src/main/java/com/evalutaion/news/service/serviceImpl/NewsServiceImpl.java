package com.evalutaion.news.service.serviceImpl;


import com.evalutaion.news.dto.*;
import com.evalutaion.news.model.*;
import com.evalutaion.news.repo.*;

import com.evalutaion.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    final private NewsChannelRepo newsChannelRepo;

    @Autowired
    final private NewsTypeRepo newsTypeRepo;

    @Autowired
    final private PopularNewsRepo popularNewsRepo;

    @Autowired
    final private TopNewsRepo topNewsRepo;

    @Autowired
    final private BookmarkRepo bookmarkRepo;



    @Override
    public Page<NewsDTO> getNews(Pageable pageable) {

        Page<NewsChannel> newsChannel= newsChannelRepo.findAll(Pageable.unpaged());
        List<NewsType> newsTypes=newsTypeRepo.findAll();
        List<TopNews> topNews=topNewsRepo.findAll();
        List<PopularNews>popularNews=popularNewsRepo.findAll();
        List<BookmarkNews> bookmarkNews=bookmarkRepo.findAll();

        List<NewsDTO> newsDTO=new ArrayList<>();
        List<NewsTypeDto> newsTypeDto=new ArrayList<>();
        DTO dto=new DTO();

        List<String> top=topNews.stream().map(TopNews::getTitle).collect(Collectors.toList());
        List<String> popular=popularNews.stream().map(PopularNews::getTitle).collect(Collectors.toList());
        List<String> bookmark=bookmarkNews.stream().map(BookmarkNews::getTitle).collect(Collectors.toList());

        dto.setTopNews(top);
        dto.setPopularNews(popular);
        dto.setBookmarkNews(bookmark);


        Map<Integer, NewsDTO> audiencesMap = new HashMap<Integer, NewsDTO>();
        Map<Integer, NewsTypeDto> audiencesMap2 = new HashMap<Integer, NewsTypeDto>();



        for (NewsType row : newsTypes) {
            NewsTypeDto type = NewsTypeDto.builder()
                   .channelId(row.getChannelId())
                    .date(row.getDate())
                    .type(row.getType())
                    .typesofNews(dto)
                    .build();

            newsTypeDto.add(type);
            audiencesMap2.put(row.getId(), type);
        }



        for (NewsChannel row : newsChannel) {
            NewsDTO newsDTO1 = NewsDTO.builder()
                    .id(row.getId())
                    .channelNames(row.getChannelNames())
                    .newsType( newsTypeDto)
                    .build();

            newsDTO.add(newsDTO1);
            audiencesMap.put(row.getId(), newsDTO1);
        }

        return new PageImpl<NewsDTO>(newsDTO);
    }

    @Override
    public List<NewsTypeDto> getDetailedNewsById(int id) {

    List<NewsType> newsType=newsTypeRepo.findByType(id);
    List<TopNews> topNews=topNewsRepo.findAll();
    List<PopularNews> popularNews=popularNewsRepo.findAll();
    List<BookmarkNews> bookmarkNews=bookmarkRepo.findAll();

    List<NewsTypeDto> newsTypeDto=new ArrayList<>();
    List<DetailsDTO> detailsDTOS=new ArrayList<>();
    if(id==1){
    topNews.forEach(row->{
        final DetailsDTO dto=DetailsDTO.builder().id(row.getId()).title(row.getTitle()).description(row.getDescription()).thumbnail(row.getThumbnail()).build();

        detailsDTOS.add(dto);
    });
    }
    else if(id==2)
    {
    popularNews.forEach(row->{
        final DetailsDTO dto=DetailsDTO.builder().id(row.getId()).title(row.getTitle()).description(row.getDescription()).thumbnail(row.getThumbnail()).build();

        detailsDTOS.add(dto);
    });
    }
    else if(id==3){
    bookmarkNews.forEach(row->{
        final DetailsDTO dto=DetailsDTO.builder().id(row.getId()).title(row.getTitle()).description(row.getDescription()).thumbnail(row.getThumbnail()).build();

        detailsDTOS.add(dto);
    });
    }
        newsType.forEach(row->{
            final NewsTypeDto dto=NewsTypeDto.builder().id(row.getId()).channelId(row.getChannelId()).date(row.getDate()).status(row.getStatus()).type(row.getType())
                    .detailsDTOS(detailsDTOS).build();
            newsTypeDto.add(dto);
        });

        return newsTypeDto;
    }

    @Override
    public List<NewsTypeDto> getDetailedNewsByTypeAndId(int type, int id) {

        List<NewsType> newsType=newsTypeRepo.findByTypeAndId(type, id);
        Optional<TopNews> topNews=topNewsRepo.findById(id);
        Optional<PopularNews> popularNews=popularNewsRepo.findById(id);
        Optional<BookmarkNews> bookmarkNews=bookmarkRepo.findById(id);

        List<NewsTypeDto> newsTypeDto=new ArrayList<>();
        List<DetailsDTO> detailsDTOS=new ArrayList<>();

        if(id==1){

                final DetailsDTO dto=DetailsDTO.builder().id(topNews.get().getId()).title(topNews.get().getTitle()).description(topNews.get().getDescription())
                        .thumbnail(topNews.get().getThumbnail()).build();
        detailsDTOS.add(dto);
        }
        else if(id==2)
        { final DetailsDTO dto=DetailsDTO.builder().id(popularNews.get().getId()).title(popularNews.get().getTitle()).description(popularNews.get().getDescription())
                .thumbnail(popularNews.get().getThumbnail()).build();

            detailsDTOS.add(dto);
        }
        else if(id==3){
            final DetailsDTO dto=DetailsDTO.builder().id(bookmarkNews.get().getId()).title(bookmarkNews.get().getTitle()).description(bookmarkNews.get().getDescription())
                    .thumbnail(bookmarkNews.get().getThumbnail()).build();
            detailsDTOS.add(dto);
        }
        newsType.forEach(row->{
            final NewsTypeDto dto=NewsTypeDto.builder().id(row.getId()).channelId(row.getChannelId()).date(row.getDate()).status(row.getStatus()).type(row.getType())
                    .detailsDTOS(detailsDTOS).build();
            newsTypeDto.add(dto);
        });
        return  newsTypeDto;
    }

    @Override
    public List<BookMarkDTO> getbookmarks() {

    List<BookmarkNews> bookmarkNews=bookmarkRepo.findAll();
    List<BookMarkDTO> bookMarkDTO=new ArrayList<>();

    bookmarkNews.forEach(row->{
        final BookMarkDTO dto=BookMarkDTO.builder().id(row.getId()).title(row.getTitle()).type(row.getType()).description(row.getDescription()).thumbnail(row.getThumbnail()).build();
        bookMarkDTO.add(dto);
    });

        return bookMarkDTO;
    }


}




























