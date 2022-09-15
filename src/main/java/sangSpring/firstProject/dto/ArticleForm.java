package sangSpring.firstProject.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import sangSpring.firstProject.entity.Article;

//컨트롤러에서 폼 데이터를 받을 때 객체로 받는데 이것을 dto라고 한다.

@AllArgsConstructor
@ToString  //Lombok:annotation을 통해 효율적인 리팩토링
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
