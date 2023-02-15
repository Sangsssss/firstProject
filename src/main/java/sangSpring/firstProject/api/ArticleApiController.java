package sangSpring.firstProject.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sangSpring.firstProject.dto.ArticleForm;
import sangSpring.firstProject.entity.Article;
import sangSpring.firstProject.repository.ArticleRepository;
import sangSpring.firstProject.service.ArticleService;

import java.util.List;

@Slf4j // log
@RestController // RestAPI용 컨트롤러, 데이터(JSON)을 반환
public class ArticleApiController {

    @Autowired // Dependency Injection, Spring boot에서 땡겨와야 함?!, 생성 객체를 가져와 연결
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }
    //POST
    @PostMapping("/api/articles")
    //RestAPI에서는 요청된 body 부분을 가져오는 annotation이 필요하다. -->
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //UPDATE, PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //ResponseEntity로 담아서 보내주면, 상태코드를 함께 보낼 수 있다.
        Article updated = articleService.update(id, dto);
        return  (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
