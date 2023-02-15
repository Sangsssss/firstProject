package sangSpring.firstProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestApi용 컨틀롤러, JSON을 반환
//일반 컨트롤러는 일반 페이지의 뷰템플릿을 반환!, Rest Controller는 JSON, 혹은 텍스트를 반환
public class firstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
