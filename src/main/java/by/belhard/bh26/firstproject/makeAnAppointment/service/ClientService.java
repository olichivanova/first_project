package by.belhard.bh26.firstproject.makeAnAppointment.service;

import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ClientService {
    Client login(int accountNumber, String password);
    List<java.sql.Date> checkAnAppointment(Client client) throws SQLException;
    void getAnAppointment(Client client, Doctor doctor, Date date) throws SQLException ;
    Doctor chooseDoctor (int id) throws  SQLException;
    void cancelAnAppointment(Client client) throws SQLException ;
    void cancelAnAppointmentByDate(java.sql.Date date) throws SQLException ;
    List<Doctor> getlist() throws SQLException;
    int checkDate(Doctor doctor, java.sql.Date date)throws SQLException;

}
