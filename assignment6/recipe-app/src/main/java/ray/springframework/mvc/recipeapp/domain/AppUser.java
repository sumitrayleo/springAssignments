package ray.springframework.mvc.recipeapp.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class AppUser {

    private UUID id;
    private String userId;
    private String app;
    private Set<AppUserDevice> appUserDevices;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    public Set<AppUserDevice> getAppUserDevices() {
        if (this.appUserDevices == null) {
            this.appUserDevices = new HashSet<>();
        }
        return appUserDevices;
    }

    public void setAppUserDevices(Set<AppUserDevice> appUserDevices) {
        this.appUserDevices = appUserDevices;
    }
}
