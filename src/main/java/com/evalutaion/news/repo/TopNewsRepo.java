package com.evalutaion.news.repo;

import com.evalutaion.news.model.TopNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopNewsRepo  extends JpaRepository<TopNews, Integer> {

}
