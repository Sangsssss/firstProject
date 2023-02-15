package sangSpring.firstProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sangSpring.firstProject.dto.ArticleForm;
import sangSpring.firstProject.entity.Article;
import sangSpring.firstProject.repository.ArticleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //서비스 선언 어노테이션 (서비스 객체를 스프링 부트에 생성)
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); //DB가 해당 객체를 인식하게 하기 위해서
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());

        //2. 대상 Entity 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리(대상이 null, id가 insame)
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청 id : {}, article : {}", id, article.toString());
            return null;
        }
        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return  updated;

    }

    public Article delete(Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //1-1. 잘못된 요청 처리
        if(target == null) {
            //400, 잘못된 요청 처리
            return null;
        }
        //2. 대상 삭제
        articleRepository.delete(target);
        return target;
    }
    @Transactional //해당 메소드를 트랜잭션으로 묶는다.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 엔티티 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //아래 코드를 stream 메소드를 활용 시 위와 같이 간략한 코드로 나타낼 수 있다.
//        List<Article> articleList = new ArrayList<>();
//        for(int i=0; i<dtos.size(); i++) {
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // entity묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("실패!")
        );
        // 결과값 반환
        return articleList;
    }
}
