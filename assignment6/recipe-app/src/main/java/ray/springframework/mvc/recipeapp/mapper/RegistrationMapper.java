package ray.springframework.mvc.recipeapp.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.AppUserDevice;
import ray.springframework.mvc.recipeapp.domain.Device;
import ray.springframework.mvc.recipeapp.model.Registration;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class RegistrationMapper {

    public AppUser toAppUser(Registration registration){
        AppUser appUser = new AppUser();
        appUser.setUserId(registration.getUserId());
        appUser.setApp(registration.getApp());

        return appUser;
    }

    public Registration toRegistration(AppUser appUser, Set<AppUserDevice> appUserDevices){
        Registration registration = new Registration();
        registration.setApp(appUser.getApp());
        registration.setUserId(appUser.getUserId());

        if (!CollectionUtils.isEmpty(appUserDevices)) {
            for (AppUserDevice appUserDevice : appUserDevices) {
                registration.getDevices().add(appUserDevice.getDevice());
            }
        }
        return registration;
    }

    public Set<AppUserDevice> toAppUserDeviceSet(Registration registration, AppUser appUser) {
        Set<AppUserDevice> appUserDevices = new HashSet<>();
        for(Device device : registration.getDevices()) {
            AppUserDevice appUserDevice = new AppUserDevice();
            appUserDevice.setAppUser(appUser);
            appUserDevice.setDevice(device);
            appUserDevice.setOptIn(true);

            appUserDevices.add(appUserDevice);
        }
        return appUserDevices;
    }
}
