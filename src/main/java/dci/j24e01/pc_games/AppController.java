package dci.j24e01.pc_games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
             return "index";
    }
}
