package models;

import enumerations.ServiceType;

import java.util.Date;
import java.util.List;


public class ServiceProvider extends Person{
    private List <Service> services;
    private List<Date> bookedDates;

}
