package sangSpring.firstProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sangSpring.firstProject.dto.ArticleForm;
import sangSpring.firstProject.entity.Article;
import sangSpring.firstProject.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id );

        //1: id로 데이터를 가져옴
        //optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2: 가져온 데이터를 Model에 등록
        model.addAttribute("article", articleEntity);
        //3: 보여줄 페이지를 설정
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        //1: 모든 Article을 가져온다.
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        //2: 가져온 Article 묶음을 뷰로 전달.
        model.addAttribute("articleList", articleEntityList);
        //3: 뷰 페이지를 설정.
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정

    return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        //1. DTO -> Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //2. Entity -> DB에 저장
        //2-1. DB에서 기존 데이터를 가져온다.

        Optional<Article> target = articleRepository.findById(article.getId());
        //2-2. 기존 데이터에 값을 갱신한다.
        if(target != null){
            articleRepository.save(article);
        }

        //수정 결과 페이지를 Redirect
        return "redirect:/articles/" + article.getId();
    }
}
