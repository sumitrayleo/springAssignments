package ray.springframework.mvc.recipeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index.html", "index"})
    public String getIndexPage(){
        log.debug("Inside " + IndexController.class.getSimpleName() + ".getIndexPage()");
        return "index";
    }

}
