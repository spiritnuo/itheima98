package cn.itcast.service;

import cn.itcast.dao.ArticleDao;
import cn.itcast.domain.Article;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    protected ArticleDao articleDao;

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public List<Article> findAll() {
        //通过findAll方法进行查询参数可以设置Sort排序Sort.by(Sort.Order.asc或desc,指定要排序的域
        Iterator<Article> iterator = articleDao.findAll(Sort.by(Sort.Order.desc("id"))).iterator();
        return Lists.newArrayList(iterator);
    }


    @Override
    public Article findOne(Long id) {
        Optional<Article> articleOptional = articleDao.findById(id);
        return articleOptional.get();
    }

    @Override
    public List<Article> findPage(Pageable pageable) {

        //Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        //pageable中设置是传入的分页参数
        Page<Article> page = articleDao.findAll(pageable);

        System.out.println("共记录数="+page.getTotalElements());
        System.out.println("当前页记录数="+page.getNumberOfElements());

        //page.getContent()是当前分页的所有数据

        return page.getContent();
    }

    @Override
    public void deleteOne(Long id) {
        articleDao.deleteById(id);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleDao.findByTitle(title);
    }

    @Override
    public Page<Article> findByTitle(String title, Pageable pageable) {
        return articleDao.findByTitle(title, pageable);
    }
}
