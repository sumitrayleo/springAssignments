package ray.springframework.mvc.recipeapp.service;

import ray.springframework.mvc.recipeapp.model.Registration;

public interface RegistrationService {

    Registration save(Registration registration);

    Registration getAppUser(String userId, String app);
}
