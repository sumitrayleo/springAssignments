package ray.springframework.mvc.recipeapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ray.springframework.mvc.recipeapp.domain.Device;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class Registration {
    private String userId;
    private String app;
    private Set<Device> devices;

    public Set<Device> getDevices() {
        if (devices == null) {
            devices = new HashSet<>();
        }
        return devices;
    }
}
