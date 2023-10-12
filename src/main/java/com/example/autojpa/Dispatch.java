package com.example.autojpa;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RepairRequestEntity;
import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Exception.NotFindException;
import com.example.autojpa.Service.impl.AutoServiceImpl;
import com.example.autojpa.Service.impl.DriverServiceImpl;
import com.example.autojpa.Service.impl.RepairRequestServiceImpl;
import com.example.autojpa.Service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Dispatch {


    @Autowired
    private final DriverServiceImpl driverService;

    @Autowired
    private final AutoServiceImpl autoService;

    @Autowired
    private final RequestServiceImpl requestService;

    @Autowired
    private final RepairRequestServiceImpl repairRequestService;

    public Dispatch(DriverServiceImpl driverService, AutoServiceImpl autoService, RequestServiceImpl requestService, RepairRequestServiceImpl repairRequestService) {
        this.driverService = driverService;
        this.autoService = autoService;
        this.requestService = requestService;
        this.repairRequestService = repairRequestService;
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

    private void takeRequestSetNotFree(RequestEntity request){
        request.setFree(false);
        requestService.updateRequest(request);
    }

    private void takeAutoSetNotFree(AutoEntity auto)
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

    public void doRepairRequest(Long driver_id){
        RepairRequestEntity repairRequest = new RepairRequestEntity();
        DriverEntity driver = new DriverEntity();
        try {
            driver = driverService.findById(driver_id)
                    .orElseThrow(()->new NotFindException("Не найден такой водитель!"));
        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }


        repairRequest.setAuto(driver.getAuto());
        repairRequest.setDriver(driver);

        repairRequestService.addRequest(repairRequest);
    }

};
