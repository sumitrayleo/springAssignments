package ray.springframework.mvc.recipeapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ray.springframework.mvc.recipeapp.dao.RegistrationDao;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.Device;
import ray.springframework.mvc.recipeapp.mapper.RegistrationMapper;
import ray.springframework.mvc.recipeapp.model.DeviceRQ;
import ray.springframework.mvc.recipeapp.model.Registration;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationDao registrationDao;

    public RegistrationServiceImpl(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Override
    public Registration save(Registration registration) {
        final AppUser appUser = RegistrationMapper.toAppUser(registration);
        final Set<Device> devices = toDevices(registration);

        AppUser savedAppUser = registrationDao.save(appUser, devices);
        return RegistrationMapper.toRegistration(savedAppUser, savedAppUser.getAppUserDevices());
    }

    private Set<Device> toDevices(Registration registration) {
        Set<Device> devices = new HashSet<>();
        for(DeviceRQ deviceRq : registration.getDevices()) {
            final Device device = RegistrationMapper.toDevice(deviceRq);
            if(StringUtils.isEmpty(device.getActive())) {
                // TODO: Defaulted to TRUE
                device.setActive(true);
            }
            devices.add(device);
        }
        return devices;
    }

    @Override
    public Registration getAppUser(String userId, String app) {
        final AppUser appUser = registrationDao.findAppUser(userId, app);
        return RegistrationMapper.toRegistration(appUser, appUser.getAppUserDevices());
    }

    /*@Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Registration save(Registration registration) {
        AppUser appUser = RegistrationMapper.toAppUser(registration);
        toAppUserDeviceSet(registration, appUser);
        AppUser savedAppUser = registrationDao.saveAppUser(appUser);

        return RegistrationMapper.toRegistration(savedAppUser, appUser.getAppUserDevices());
    }*/
}
