package sangSpring.firstProject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity//  DB가 해당 객체를 인식 가능!!
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    @Id // 대표값을 지정! like 주민등록번호
    @GeneratedValue // 1,2,3,... 자동 생성 annotation!
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

}
