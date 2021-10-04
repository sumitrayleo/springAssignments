package ray.springframework.mvc.recipeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ray.springframework.mvc.recipeapp.domain.AppUserDevice;
import ray.springframework.mvc.recipeapp.domain.AppUserDevicePK;

@Repository
public interface AppUserDeviceRepository extends JpaRepository<AppUserDevice, AppUserDevicePK> {

}
