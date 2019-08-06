package cn.itcast.test;

import cn.itcast.domain.Article;
import cn.itcast.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest {
    @Autowired
    protected ArticleService articleServer;
    @Test
    public void testAdd(){

        for (int i = 0; i < 100; i++) {
            Article article = new Article();
            article.setId((long)i);
            article.setTitle(i+"elasticSearch 3.0版本发布...更新");
            article.setContent(i+"ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜 索引擎，基于RESTful web接口");
            articleServer.save(article);
        }
        System.out.println("====");
    }

    //测试查询全部
    @Test
    public void testFindAll(){
        List<Article> list = articleServer.findAll();
        for (Article article : list) {
            System.out.println(article);
        }
    }


    //获取单个document对象
    @Test
    public void testFindOne(){
        Article article = articleServer.findOne(60l);
        System.out.println(article);
    }

    //修改测试，id一致就是修改操作
    @Test
    public void update(){
        Article article = new Article();
        article.setId(60l);
        article.setTitle("修改");
        article.setContent("修改");
        articleServer.save(article);
    }

    //删除测试,根据_id进行删除的操作
    @Test
    public void delete(){
        articleServer.deleteOne(60l);
    }

    //测试分页
    @Test
    public void testPage(){
        //PageRequest设置pageNo从第0也开始，pageSize，需要设置排序，设置的是id属性升序查询
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC,"id"));
        List<Article> list = articleServer.findPage(pageable);
        for (Article article : list) {
            System.out.println(article);
        }
    }


    /**
     * 测试命名规格的查询方式
     */
    @Test
    public void findByTitle(){
        List<Article> articles = articleServer.findByTitle("版本");
        System.out.println("共查到"+articles.size());
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /**条件分页查询*/
    @Test
    public void findByTitlePage(){
        String title = "版本";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC,"id"));
        Page<Article> page = articleServer.findByTitle(title,pageable);
        for(Article article:page.getContent()){
            System.out.println(article);
        }
    }
}
