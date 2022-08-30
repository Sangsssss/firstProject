package sangSpring.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")  // hi라는 URL을 입력받았을 때, greetings라는 Controller를 반환해준다.
    public String niceToMeetyou(Model model) {
        model.addAttribute("username", "상원");
        return "greetings"; // templates/greetings.mustache -> 브라우저로 전송!
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "sangwon");
        return "goodbye";
    }
}
