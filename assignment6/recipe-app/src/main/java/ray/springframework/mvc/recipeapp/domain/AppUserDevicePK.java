package ray.springframework.mvc.recipeapp.domain;

import java.io.Serializable;
import java.util.Objects;

public class AppUserDevicePK implements Serializable {

    private AppUser appUser;
    private Device device;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUserDevicePK)) return false;
        AppUserDevicePK that = (AppUserDevicePK) o;
        return appUser.equals(that.appUser) &&
                device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUser, device);
    }
}
