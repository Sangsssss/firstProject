package sangSpring.firstProject.repository;

import org.springframework.data.repository.CrudRepository;
import sangSpring.firstProject.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long>  {
    @Override
    ArrayList<Article> findAll();
}
