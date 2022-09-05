package sangSpring.firstProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sangSpring.firstProject.dto.ArticleForm;
import sangSpring.firstProject.entity.Article;
import sangSpring.firstProject.repository.ArticleRepository;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결! -> new를 해서 객체를 생성하지 않아도 된다!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm) {

    log.info(articleForm.toString());
    //println으로 테스트하는 것은 서버를 개발할 때 사용하지 않는다!
    //System.out.println(articleForm.toString()); --> 로깅기능으로 대체!

    // 1. DTO -> Entity로 변환
    Article article = articleForm.toEntity();
    log.info(article.toString());
    //System.out.println(article.toString());
    //2. Repository에게 Entity를 DB에 저장하게 함.
    Article saved = articleRepository.save(article);
//    System.out.println(saved.toString());
    log.info(saved.toString());
    return "";
    }
}
