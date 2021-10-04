package ray.springframework.mvc.recipeapp.dao;

import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.Device;

import java.util.Set;

public interface RegistrationDao {
    AppUser saveAppUser(AppUser appUser);

    Device saveDevice(Device device);

    AppUser findAppUser(String userId, String app);

    //Device findDevice(String name, String token);

    AppUser save(AppUser appUser, Set<Device> devices);
}
