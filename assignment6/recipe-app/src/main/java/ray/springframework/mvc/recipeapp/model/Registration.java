package ray.springframework.mvc.recipeapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class Registration {
    private String userId;
    private String app;
    private Set<DeviceRQ> devices;

    public Set<DeviceRQ> getDevices() {
        if(this.devices == null) {
            this.devices = new HashSet<>();
        }
        return devices;
    }
}
