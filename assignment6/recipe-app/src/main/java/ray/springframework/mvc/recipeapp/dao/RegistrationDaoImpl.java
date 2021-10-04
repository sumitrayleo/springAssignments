package ray.springframework.mvc.recipeapp.dao;

import org.springframework.stereotype.Component;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.Device;
import ray.springframework.mvc.recipeapp.repository.AppUserDeviceRepository;
import ray.springframework.mvc.recipeapp.repository.AppUserRepository;
import ray.springframework.mvc.recipeapp.repository.DeviceRepository;

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
    public AppUser getAppUser(String userId, String app) {
        return appUserRepository.findByUserIdAndApp(userId, app);
    }
}
