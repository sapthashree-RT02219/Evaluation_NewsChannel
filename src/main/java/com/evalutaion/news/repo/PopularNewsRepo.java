package com.evalutaion.news.repo;

import com.evalutaion.news.model.PopularNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularNewsRepo  extends JpaRepository<PopularNews, Integer> {
}
