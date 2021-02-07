package com.evalutaion.news.repo;

import com.evalutaion.news.model.BookmarkNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepo  extends JpaRepository<BookmarkNews, Integer> {
}
