package com.example.autojpa;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Exception.NotFindException;
import com.example.autojpa.Service.impl.AutoServiceImpl;
import com.example.autojpa.Service.impl.DriverServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dispatch {
    private final DriverServiceImpl driverService;
    private final AutoServiceImpl autoService;

    private final RequestServiceImpl requestService;

    public Dispatch(ApplicationContext applicationContext) {
        this.driverService = applicationContext.getBean(DriverServiceImpl.class);
        this.requestService = applicationContext.getBean(RequestServiceImpl.class);
        this.autoService = applicationContext.getBean(AutoServiceImpl.class);
    }


    private RequestEntity getNotDoneRequest(){
        List<RequestEntity> requests;
        try {
            requests = requestService.findAllNotDoneRequestsAndFree().orElseThrow(()-> new NotFindException("Нет входящих запросов!"));
        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }

        if (requests.isEmpty()) {
            throw new RuntimeException("Список запросов пуст!");
        }

        return requests.get(0);
    }

    void takeRequestSetNotFree(RequestEntity request){
        request.setFree(false);
        requestService.updateRequest(request);
    }

    private AutoEntity getAutoWithWeightNeeded(RequestEntity request){
        AutoEntity auto;
        auto = autoService.findAutoEntityByWeight(request.getCargoQuantity());

        return auto;
    }

    private DriverEntity getDriverWithExperienceNeededAndFree(RequestEntity request){
        DriverEntity driver;
        try {
            driver= driverService.findDriverByExperienceAndFree(request.getExperience())
                    .orElseThrow(()->new NotFindException("Нет подходящих водителей!"));
        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    private void setDriverForRequest(RequestEntity request,DriverEntity driver){
        driver.setRequest(request);
        driver.setFree(false);
    }

    public void findRequestAlgorithm(){
        // Получаем не выполненные запросы
        RequestEntity notDoneRequest = getNotDoneRequest();

        //Находим водителя с подходящим стажем
        DriverEntity neededDriver = getDriverWithExperienceNeededAndFree(notDoneRequest);

        // Находим авто с нужным весом
        AutoEntity neededAuto = getAutoWithWeightNeeded(notDoneRequest);

        //Устанавливаем водителю нужное авто
        neededDriver.setAuto(neededAuto);

        //Устанавливаем водителю запрос
        setDriverForRequest(notDoneRequest, neededDriver);

        driverService.updateDriver(neededDriver);
        takeRequestSetNotFree(notDoneRequest);
    }

    public void driverDoneRequest(){

    }

};
