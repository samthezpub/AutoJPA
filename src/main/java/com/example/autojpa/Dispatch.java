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
import java.util.Objects;
import java.util.Random;

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

    void takeAutoSetNotFree(AutoEntity auto)
    {
        auto.setFree(false);
        autoService.updateAuto(auto);
    }

    private AutoEntity getAutoWithWeightNeededAndFree(RequestEntity request){
        AutoEntity auto;
        auto = autoService.findAutoEntityByWeightAndFree(request.getCargoQuantity());

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
        AutoEntity neededAuto = getAutoWithWeightNeededAndFree(notDoneRequest);

        //Устанавливаем водителю нужное авто
        neededDriver.setAuto(neededAuto);

        //Устанавливаем водителю запрос
        setDriverForRequest(notDoneRequest, neededDriver);

        driverService.updateDriver(neededDriver);
        takeAutoSetNotFree(neededAuto);
        takeRequestSetNotFree(notDoneRequest);
    }

    private void setDoneRequest(RequestEntity request){
        request.setDone(true);
        requestService.updateRequest(request);
    }

    private void setAutoFree(AutoEntity auto){
        auto.setFree(true);
        autoService.updateAuto(auto);
    }

    private void PAYPAY(DriverEntity driver){
        Random random = new Random();

        double payday = driver.getMoney() + random.nextDouble(100, 20000);
        driver.setMoney(payday);

        driverService.updateDriver(driver);
    }

    public void driverDoneRequest(Long request_id){
        DriverEntity driver;
        try {
            driver = driverService.findDriverEntityByRequestId(request_id)
                    .orElseThrow(() -> new NotFindException("Нет водителя с таким запросом!"));
        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }

        // Устанавливаем запрос как выполненный
        setDoneRequest(driver.getRequest());

        // Освобождаемся от всего
        driver.setRequest(null);
        setAutoFree(driver.getAuto());
        driver.setAuto(null);
        driver.setFree(true);

        PAYPAY(driver);
    }

};
