package com.evalutaion.news.repo;



import com.evalutaion.news.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsTypeRepo  extends JpaRepository<NewsType, Integer> {


    @Query(value="SELECT * FROM news.news_type where  type=:id",nativeQuery = true)
    List<NewsType> findByType (@Param("id") int id);

    @Query(value="SELECT * FROM news.news_type where  type=:type and id=:id",nativeQuery = true)
    List<NewsType> findByTypeAndId (@Param("type") int type,@Param("id") int id);

}
