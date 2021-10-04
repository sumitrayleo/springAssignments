package ray.springframework.mvc.recipeapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ray.springframework.mvc.recipeapp.dao.RegistrationDao;
import ray.springframework.mvc.recipeapp.domain.AppUser;
import ray.springframework.mvc.recipeapp.domain.AppUserDevice;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Registration save(Registration registration) {
        Set<AppUserDevice> appUserDevices = new HashSet<>();
        AppUser savedAppUser = registrationDao.saveAppUser(RegistrationMapper.toAppUser(registration));

        for(DeviceRQ device : registration.getDevices()) {
            final Device savedDevice = registrationDao.saveDevice(RegistrationMapper.toDevice(device));

            final AppUserDevice appUserDevice = new AppUserDevice();
            appUserDevice.setAppUser(savedAppUser);
            appUserDevice.setDevice(savedDevice);
            appUserDevice.setOptIn(device.getOptin());

            appUserDevices.add(appUserDevice);
        }
        savedAppUser.setAppUserDevices(appUserDevices);
        registrationDao.saveAppUser(savedAppUser);

        return RegistrationMapper.toRegistration(savedAppUser, appUserDevices);
    }

    @Override
    public Registration getAppUser(String userId, String app) {
        AppUser appUser = registrationDao.getAppUser(userId, app);
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
