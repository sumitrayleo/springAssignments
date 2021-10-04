package ray.springframework.mvc.recipeapp.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.AppUserDevice;
import ray.springframework.mvc.recipeapp.domain.Device;
import ray.springframework.mvc.recipeapp.model.DeviceRQ;
import ray.springframework.mvc.recipeapp.model.Registration;

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
                registration.getDevices().add(toDeviceRq(appUserDevice.getDevice(), appUserDevice));
            }
        }
        return registration;
    }

    public Device toDevice(DeviceRQ deviceRq) {
        Device device = new Device();
        device.setName(deviceRq.getName());
        device.setPlatform(deviceRq.getPlatform());
        device.setToken(deviceRq.getToken());
        device.setActive(true);

        return device;
    }

    public DeviceRQ toDeviceRq(Device device, AppUserDevice appUserDevice) {
        DeviceRQ deviceRq = new DeviceRQ();
        deviceRq.setName(device.getName());
        deviceRq.setPlatform(device.getPlatform());
        deviceRq.setToken(device.getToken());
        deviceRq.setOptin(appUserDevice.getOptIn());

        return deviceRq;
    }

    /*public Set<AppUserDevice> toAppUserDeviceSet(Registration registration, AppUser appUser) {
        Set<AppUserDevice> appUserDevices = new HashSet<>();
        for(Device device : registration.getDevices()) {
            AppUserDevice appUserDevice = new AppUserDevice();
            appUserDevice.setAppUser(appUser);
            appUserDevice.setDevice(device);
            appUserDevice.setOptIn(true);

            appUserDevices.add(appUserDevice);
        }
        return appUserDevices;
    }*/
}
