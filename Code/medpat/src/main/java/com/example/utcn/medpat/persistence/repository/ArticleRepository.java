package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleByTitle(String title);
    List<Article> findArticleByAuthor(Long MedicId);
    List<Article> findAll();

}
