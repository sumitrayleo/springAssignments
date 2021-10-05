package ray.springframework.mvc.recipeapp.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.AppUserDevice;
import ray.springframework.mvc.recipeapp.domain.Device;
import ray.springframework.mvc.recipeapp.repository.AppUserDeviceRepository;
import ray.springframework.mvc.recipeapp.repository.AppUserRepository;
import ray.springframework.mvc.recipeapp.repository.DeviceRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class RegistrationDaoImpl implements RegistrationDao {

    private final AppUserRepository appUserRepository;
    private final DeviceRepository deviceRepository;
    private final AppUserDeviceRepository appUserDeviceRepository;

    public RegistrationDaoImpl(AppUserRepository appUserRepository, DeviceRepository deviceRepository, AppUserDeviceRepository appUserDeviceRepository) {
        this.appUserRepository = appUserRepository;
        this.deviceRepository = deviceRepository;
        this.appUserDeviceRepository = appUserDeviceRepository;
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public AppUser findAppUser(String userId, String app) {
        return appUserRepository.findByUserIdAndApp(userId, app);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AppUser save(AppUser appUser, Set<Device> devices) {
        final AppUser savedAppUserFromDB = appUserRepository.findByUserIdAndApp(appUser.getUserId(), appUser.getApp());

        AppUser savedAppUser = null;
        if(savedAppUserFromDB == null) {
            // Create
            savedAppUser = createRegistration(appUser, devices);
        } else {
            // Update
            savedAppUser = updateRegistration(savedAppUserFromDB, devices);
        }

        if(savedAppUser == null) {
            // TODO: throw some exception
        }
        return appUserRepository.save(savedAppUser);
    }

    private AppUser createRegistration(AppUser appUser, Set<Device> devices) {
        Set<AppUserDevice> appUserDevices = new HashSet<>();
        AppUser savedAppUser = appUserRepository.save(appUser);

        for(Device device : devices) {
            //final Device savedDeviceFromDB = deviceRepository.findByNameAndToken(device.getName(), device.getToken());
            final Device savedDevice = deviceRepository.save(device);

            final AppUserDevice appUserDevice = new AppUserDevice();
            appUserDevice.setAppUser(savedAppUser);
            appUserDevice.setDevice(savedDevice);
            // TODO: Need to see how to propagate this from DTO
            appUserDevice.setOptIn(true);

            appUserDevices.add(appUserDevice);
        }
        savedAppUser.setAppUserDevices(appUserDevices);
        return appUserRepository.save(savedAppUser);
    }

    private AppUser updateRegistration(AppUser savedAppUserfromDB, Set<Device> devices) {
        Set<AppUserDevice> appUserDevices = new HashSet<>();
        for(Device device : devices) {
            Device savedDeviceFromDB = deviceRepository.findByToken(device.getToken());
            if(savedDeviceFromDB == null) {
                // Create Device
                savedDeviceFromDB = new Device();
                savedDeviceFromDB.setToken(device.getToken());
                savedDeviceFromDB.setPlatform(device.getPlatform());
                savedDeviceFromDB.setActive(device.getActive());
            }
            savedDeviceFromDB.setName(device.getName());
            final Device updatedDeviceFromDB = deviceRepository.save(savedDeviceFromDB);

            AppUserDevice appUserAndDeviceFromDB = appUserDeviceRepository.findByAppUserAndDevice(savedAppUserfromDB, updatedDeviceFromDB);
            if(appUserAndDeviceFromDB == null) {
                appUserAndDeviceFromDB = new AppUserDevice();
                appUserAndDeviceFromDB.setAppUser(savedAppUserfromDB);
                appUserAndDeviceFromDB.setDevice(updatedDeviceFromDB);
            }

            // TODO: Need to see how to propagate this from DTO
            appUserAndDeviceFromDB.setOptIn(false);
            appUserDevices.add(appUserAndDeviceFromDB);
        }
        savedAppUserfromDB.setAppUserDevices(appUserDevices);
        return appUserRepository.save(savedAppUserfromDB);
    }
}
