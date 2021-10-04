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
    //@JoinColumn(name = "appuser_id", referencedColumnName = "id")
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Id
    @ManyToOne
    //@JoinColumn(name = "device_id", referencedColumnName = "id")
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
