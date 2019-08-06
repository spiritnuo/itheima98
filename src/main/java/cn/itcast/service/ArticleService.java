package cn.itcast.service;

import cn.itcast.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    //保存
    void save(Article article);
    //查询全部
    List<Article> findAll();

    //查询单个
    Article findOne(Long id);

    //分页查询
    List<Article> findPage(Pageable pageable);

    //删除
    void deleteOne(Long id);


    //==============以下两个方法是通过命名规范进行查询================
    //根据标题查询  findBy+查询的域名(注意大写)
    List<Article> findByTitle(String title);

    //根据标题查询分页
    Page<Article> findByTitle(String title, Pageable pageable);

}
