package sangSpring.firstProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity//  DB가 해당 객체를 인식 가능!! (해당 클래스로 테이블을 생성)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id // 대표값을 지정! like 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 annotation!
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
