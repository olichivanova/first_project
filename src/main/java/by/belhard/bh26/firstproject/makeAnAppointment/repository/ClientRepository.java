package by.belhard.bh26.firstproject.makeAnAppointment.repository;

import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {
Client getByAccount (int accountNumber, String password) throws SQLException;
    List<Date> checkAnAppointment(Client client)throws SQLException;
    void getAnAppointment(Client client, Doctor doctor, Date date) throws SQLException;
    void cancelAnAppointmentByDate(java.sql.Date date) throws SQLException ;
    void cancelAnAppointment(Client client)throws SQLException;
    Doctor chooseDoctor (int id) throws  SQLException;
    int checkDate(Doctor doctor, java.sql.Date date)throws SQLException;

}
