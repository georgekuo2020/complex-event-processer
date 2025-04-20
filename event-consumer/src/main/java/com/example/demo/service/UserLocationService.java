package com.example.demo.service;

import com.example.demo.entity.UserLocation;
import com.example.demo.model.bo.UserDataBO;
import com.example.demo.repository.UserLocationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserLocationService {

    @Autowired
    UserLocationRepository userLocationRepository;

    public void saveRecord(UserDataBO userDataBO) {

        UserLocation userLocation = new UserLocation();

        userLocation.setUserId(userDataBO.getUid());
        userLocation.setLocation(userDataBO.getLocation());

        userLocationRepository.save(userLocation);
    }
}
