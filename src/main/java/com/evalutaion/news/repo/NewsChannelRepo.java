package com.evalutaion.news.repo;



import com.evalutaion.news.model.NewsChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsChannelRepo extends JpaRepository<NewsChannel, Integer> {

    @Query(value = "SELECT * FROM news.news_channel where id= :id ",nativeQuery = true)
    NewsChannel findByChannelId(@Param("id") int id);


}
