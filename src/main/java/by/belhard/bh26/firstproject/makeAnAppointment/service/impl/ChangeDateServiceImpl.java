package by.belhard.bh26.firstproject.makeAnAppointment.service.impl;

import by.belhard.bh26.firstproject.makeAnAppointment.controller.io.ConsoleIO;
import by.belhard.bh26.firstproject.makeAnAppointment.controller.io.IOInterface;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.ClientRepository;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.impl.ClientRepositoryImp;
import by.belhard.bh26.firstproject.makeAnAppointment.service.ChangeDateService;
import by.belhard.bh26.firstproject.makeAnAppointment.service.ClientService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ChangeDateServiceImpl implements ChangeDateService {
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final IOInterface ioInterface;

    public ChangeDateServiceImpl() {
        this.clientRepository = new ClientRepositoryImp();
        this.clientService = new ClientServiceImpl();
        this.ioInterface = new ConsoleIO();
    }

    @Override
    public void makeChangeDate(Client client) {
try{
        try{ List<Date> date = clientRepository.checkAnAppointment(client);

        if(date.size()>1){
            System.out.println("Choose the date, which you want to change");
            System.out.println("Date: " + date);
            java.util.Date date1 = null;
            try {
                date1 = ioInterface.readDateValue();
            } catch (IOException e) {
                e.printStackTrace();
            }
            java.sql.Date date2 = new java.sql.Date(date1.getTime());
            clientRepository.cancelAnAppointmentByDate(date2);
            int count = 1;
            while (count == 1){
                System.out.println("Choose a doctor  by pressing number");
                System.out.println(clientService.getlist().toString());
                int id = 0;
                try {
                    id = ioInterface.readIntValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (id>clientService.getlist().size())
                    System.out.println("Wrong input");
                else {
                Doctor doctor = clientRepository.chooseDoctor(id);
                System.out.println("Write down your available date");
                java.util.Date date4 = null;
                try {
                    date4 = ioInterface.readDateValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                java.sql.Date date3 = new java.sql.Date(date4.getTime());

                try {
                    int appoin = clientService.checkDate(doctor, date3);
                    if (appoin > 0) {
                        System.out.println("This date is unavailable. Please, choose another date or doctor");
                        System.out.println("'Press 1 to continue on; 0 Exit to maim menu");
                        count = ioInterface.readIntValue();
                    }
                    else
                    {clientRepository.getAnAppointment(client, doctor, date3);
                        count = 0;}}
                catch (Exception e){
                    System.out.println("Please do again");
                }}}

        }
        else if( date.size() == 1) { clientRepository.cancelAnAppointment(client);
            int count = 1;
            while (count == 1){
                System.out.println("Choose a doctor  by pressing number");
                System.out.println(clientService.getlist().toString());
                int id = 0;
                try {
                    id = ioInterface.readIntValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (id > clientService.getlist().size())
                    System.out.println("Wrong input");
                else {
                Doctor doctor = clientRepository.chooseDoctor(id);
                System.out.println("Write down your available date");
                java.util.Date date1 = null;
                try {
                    date1 = ioInterface.readDateValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                java.sql.Date date2 = new java.sql.Date(date1.getTime());

                try {
                    int appoin = clientService.checkDate(doctor, date2);
                    if (appoin > 0) {
                        System.out.println("This date is unavailable. Please, choose another date or doctor");
                        System.out.println("'Press 1 to continue on on; 0 Exit to maim menu");
                        count = ioInterface.readIntValue();
                    }
                    else
                    {clientRepository.getAnAppointment(client, doctor, date2);
                        count = 0;}}
                catch (Exception e){
                    System.out.println("Please do again");
                }}}}
        else if (date.size() == 0) {
            System.out.println("No one appointment has been found.");

        }

        }
        catch (SQLException e) {
            System.out.println("No one appointment has been found");
        }

     }
        catch (Exception e){
            e.printStackTrace();
        }


}}
