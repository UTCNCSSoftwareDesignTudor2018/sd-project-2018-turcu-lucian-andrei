package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Article;
import com.example.utcn.medpat.persistence.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

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
        articleRepository.save(article);
    }
}
