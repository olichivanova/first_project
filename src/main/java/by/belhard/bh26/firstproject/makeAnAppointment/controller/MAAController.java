package by.belhard.bh26.firstproject.makeAnAppointment.controller;

import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;

import java.util.Date;

public interface MAAController {

    Date checkAnAppointment(Client client);
    void getAnAppointment(Client client, Doctor doctor, Date date);
    void cancelAnAppointment(Client client);
    void changeDate(Client client, Doctor doctor, Date date);



}
