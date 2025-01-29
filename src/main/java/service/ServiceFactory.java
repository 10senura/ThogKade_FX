package service;

import Controller.util.ServiceType;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;


public class ServiceFactory {

    public static ServiceFactory insterns;

    private ServiceFactory() {
    }

    public static ServiceFactory getInsterns() {
        return insterns == null ? insterns = new ServiceFactory() : insterns;

    }
    public <T extends SuperService>T getServiceType(ServiceType ServiceType){

        switch (ServiceType){
            case CUSTOMER:return (T) CustomerServiceImpl.getInstance();
            case ITEM:return (T)  new ItemServiceImpl();
        }
        return null;
    }
}
