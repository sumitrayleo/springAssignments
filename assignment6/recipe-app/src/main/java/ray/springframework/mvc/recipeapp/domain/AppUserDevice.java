package ray.springframework.mvc.recipeapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "appuser_device")
@IdClass(AppUserDevicePK.class)
public class AppUserDevice {
    private AppUser appUser;
    private Device device;
    private Boolean optIn;

    @Id
    @ManyToOne
    @JoinColumn(updatable = false)
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Id
    @ManyToOne
    @JoinColumn(updatable = false)
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Column(name = "optin")
    public Boolean getOptIn() {
        return optIn;
    }

    public void setOptIn(Boolean optIn) {
        this.optIn = optIn;
    }
}
