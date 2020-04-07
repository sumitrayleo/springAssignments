package rays.assignment.jokes.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rays.assignment.jokes.app.service.JokesService;

@Controller
public class JokeController {

    private JokesService jokesService;

    public JokeController(JokesService jokesService) {
        this.jokesService = jokesService;
    }
    @RequestMapping(value = "/joke")
    public String getRandomQuote(Model model){
        model.addAttribute("joke", jokesService.getRandomQuote());
        return "chucknorris";
    }

}
