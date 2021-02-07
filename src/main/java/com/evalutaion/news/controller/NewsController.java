package com.evalutaion.news.controller;
;
import com.evalutaion.news.common.HttpStatusString;
import com.evalutaion.news.dto.BookMarkDTO;
import com.evalutaion.news.dto.ErrorResponse;
import com.evalutaion.news.dto.NewsDTO;
import com.evalutaion.news.dto.NewsTypeDto;
import com.evalutaion.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;




@Slf4j
@RestController
@RequestMapping(path = "/v1/news")
@RequiredArgsConstructor
public class NewsController {

@Autowired
private NewsService newsService;
    private static final Integer DEFAULT_SIZE = 10;
    @GetMapping(path = "/homescreen",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Home screen to display all news",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusString.OK),
                    @ApiResponse(
                            responseCode = HttpStatusString.BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.UNAUTHORIZED,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.FORBIDDEN,
                             content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.TOO_MANY_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<NewsDTO> homeScreen(

                    @RequestParam(name = "offset", required = false) Long offset,
                    @Parameter(description = "pagination sort by asc or desc")
                    @RequestParam(name = "orderBy", required = false) SortOrder sortOrder,
                    @Parameter(schema = @Schema(minimum = "1"))
                    @Min(1) @Max(10)
                    @RequestParam(required = false) Integer size
         )
    {
        if(size == null){
            size = DEFAULT_SIZE;
        }
        if (offset == null){
            offset = 0L;
        }
        if (sortOrder == null){
            sortOrder = SortOrder.DESCENDING;
        }
        Pageable paging = PageRequest.of(offset.intValue(), size, Sort.by("id").ascending());
        if (SortOrder.DESCENDING.equals(sortOrder)){
            paging = PageRequest.of(offset.intValue(), size, Sort.by("id").descending());
        }
        return newsService.getNews(paging);
    }

    @GetMapping(path ="/homescreen/{type}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Getting type of news among topNews,popularNews,BookmarkNews by the type",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusString.OK),
                    @ApiResponse(
                            responseCode = HttpStatusString.BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.UNAUTHORIZED,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.FORBIDDEN,
                             content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.TOO_MANY_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NewsTypeDto> detailedScreenByType(@PathVariable  int  type)

    {
        return newsService.getDetailedNewsById(type);
    }


    @GetMapping(path ="/homescreen/{type}/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "getting detailed screen by the perticular type of the news and id of the news while tapping on the article",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusString.OK),
                    @ApiResponse(
                            responseCode = HttpStatusString.BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.UNAUTHORIZED,
                             content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.FORBIDDEN,
                             content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.TOO_MANY_REQUEST,
                              content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NewsTypeDto> detailedScreenByTypeAndId(@PathVariable  int  type,@PathVariable  int  id)

    {
        return newsService.getDetailedNewsByTypeAndId(type,id);
    }

    @GetMapping(path ="/homescreen/bookmarks",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "getting all content of the bookmark screen",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusString.OK),
                    @ApiResponse(
                            responseCode = HttpStatusString.BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.UNAUTHORIZED,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.FORBIDDEN,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = HttpStatusString.TOO_MANY_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookMarkDTO> bookmarkscreen()

    {
        return newsService.getbookmarks();
    }

}
