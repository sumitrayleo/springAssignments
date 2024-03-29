package ray.springframework.mvc.recipeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ray.springframework.mvc.recipeapp.domain.Device;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Device findByToken(String token);
}
