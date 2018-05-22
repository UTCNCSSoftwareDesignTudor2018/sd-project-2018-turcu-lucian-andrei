package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Article;
import com.example.utcn.medpat.persistence.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public List<Article> getArticleByMedicId(Long id) {
        return articleRepository.findArticleByAuthor(id);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleByTitle(String title) {
        return articleRepository.findArticleByTitle(title);
    }

    public void postArticle(Article article) {
        Date dt = new java.util.Date();
        article.setCreationDate(sdf.format(dt));
        articleRepository.save(article);
    }
}
