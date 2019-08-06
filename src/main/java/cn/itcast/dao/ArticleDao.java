package cn.itcast.dao;

import cn.itcast.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleDao extends ElasticsearchRepository<Article,Long> {
    //根据标题查询  findBy+查询的域名(注意大写)
    List<Article> findByTitle(String title);

    //根据标题查询分页
    Page<Article> findByTitle(String title, Pageable pageable);
}
