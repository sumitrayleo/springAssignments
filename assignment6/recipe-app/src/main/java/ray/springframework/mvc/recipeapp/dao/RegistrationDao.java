package ray.springframework.mvc.recipeapp.dao;

import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.Device;

public interface RegistrationDao {
    AppUser saveAppUser(AppUser appUser);

    Device saveDevice(Device device);
}
