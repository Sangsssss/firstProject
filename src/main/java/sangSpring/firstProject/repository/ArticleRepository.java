package sangSpring.firstProject.repository;

import org.springframework.data.repository.CrudRepository;
import sangSpring.firstProject.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>  {

}
