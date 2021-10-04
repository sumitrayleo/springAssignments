package ray.springframework.mvc.recipeapp.controller;

import org.springframework.web.bind.annotation.*;
import ray.springframework.mvc.recipeapp.model.Registration;
import ray.springframework.mvc.recipeapp.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public Registration register(@RequestParam String userId, @RequestParam String app){
        return registrationService.getAppUser(userId, app);
    }

    @PutMapping
    public Registration register(@RequestBody Registration registration){
        return registrationService.save(registration);
    }
}
