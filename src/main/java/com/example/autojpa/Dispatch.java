package com.example.autojpa;

import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Exception.NotFindException;
import com.example.autojpa.Service.impl.DriverServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.context.ApplicationContext;

import java.io.NotActiveException;

public class Dispatch {
    DriverServiceImpl driverService;
    RequestServiceImpl requestService;

    public Dispatch(ApplicationContext applicationContext) {
        this.driverService = applicationContext.getBean(DriverServiceImpl.class);
        this.requestService = applicationContext.getBean(RequestServiceImpl.class);
    }

    public void findDriverAndSetForRequest(Long requestId, Integer experience) {
        DriverEntity driver = null ;
        RequestEntity request = null;
        try {
            request = requestService.findById(requestId).orElseThrow(() -> new NotFindException("Не найдена запись о запросе!"));
        } catch (NotFindException e) {
            System.out.println(e.getMessage());
        }

        try {
            driverService.findDriverByExperience(experience).orElseThrow(() -> new NotFindException("Не найден водитель с подходящим опытом!"));
        }catch (NotFindException e){
            System.out.println(e.getMessage());
        }

        driver.setRequest(request);

        driverService.updateDriver(driver);
    }
}
