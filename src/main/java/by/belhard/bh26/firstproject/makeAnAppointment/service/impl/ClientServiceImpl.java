package by.belhard.bh26.firstproject.makeAnAppointment.service.impl;

import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;

import by.belhard.bh26.firstproject.makeAnAppointment.repository.ClientRepository;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.impl.ClientRepositoryImp;
import by.belhard.bh26.firstproject.makeAnAppointment.service.ClientService;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;



    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImp();
    }

    @Override
    public Client login(int accountNumber, String pass) {
        Client client = null;
        try {
           client = clientRepository.getByAccount(accountNumber, pass);
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (client != null && client.getAccountNumber()==accountNumber && client.getPassword().equals(pass))
        return client;

        return null;
    }

    @Override
    public List<java.sql.Date> checkAnAppointment(Client client) throws SQLException {

return clientRepository.checkAnAppointment (client);
    }

    @Override
    public void getAnAppointment(Client client, Doctor doctor, Date date) throws SQLException  {
clientRepository.getAnAppointment(client, doctor, (java.sql.Date) date);
    }

    @Override
    public void cancelAnAppointment(Client client) throws SQLException  {

clientRepository.cancelAnAppointment(client);
    }

    @Override
    public Doctor chooseDoctor(int id) throws SQLException {
       Doctor doctor = null;
        try {
            doctor = clientRepository.chooseDoctor(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (doctor != null && doctor.getId() == id)
            return doctor;

        return null;
    }

    @Override
    public List<Doctor> getlist() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        Doctor doctor = null;

        try {
            for (int id = 1; id < 7; id++){
            doctor = clientRepository.chooseDoctor(id);
            if (doctor != null)
            list.add(doctor);}
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int checkDate(Doctor doctor, java.sql.Date date) throws SQLException {
        Date localDate = new Date();
        java.sql.Date date1 = new java.sql.Date(localDate.getTime());
        int id = 0;
        if (date.compareTo(date1) == 0 || date.compareTo(date1)< 0)
        { System.out.println("Please, choose correct date");
        return id = 1;}
        else id = clientRepository.checkDate(doctor, date);
        return id;


    }

    @Override
    public void cancelAnAppointmentByDate(java.sql.Date date) throws SQLException {
        clientRepository.cancelAnAppointmentByDate(date);
    }
}
