package by.belhard.bh26.firstproject.makeAnAppointment;


import by.belhard.bh26.firstproject.makeAnAppointment.controller.io.ConsoleIO;
import by.belhard.bh26.firstproject.makeAnAppointment.controller.io.IOInterface;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.ClientRepository;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.impl.ClientRepositoryImp;
import by.belhard.bh26.firstproject.makeAnAppointment.service.ChangeDateService;
import by.belhard.bh26.firstproject.makeAnAppointment.service.ClientService;
import by.belhard.bh26.firstproject.makeAnAppointment.service.impl.ChangeDateServiceImpl;
import by.belhard.bh26.firstproject.makeAnAppointment.service.impl.ClientServiceImpl;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class MakeAnAppointment {

    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final IOInterface ioInterface;
    private final ChangeDateService changeDateService;


    public MakeAnAppointment() {
        this.clientRepository = new ClientRepositoryImp();
        this.clientService = new ClientServiceImpl();
        this.ioInterface = new ConsoleIO();
        this.changeDateService = new ChangeDateServiceImpl();


    }

    public void start(){
        //infinity loop
while (true) {
    ///login
    System.out.println("Please login. Put your Account Number and Password");
    Client client = null;
    while (client == null){
        int accountNumber = 0;
        String password = "";
   try{ accountNumber = ioInterface.readIntValue();
     password = ioInterface.readLine();}
   catch (Exception e)
   {e.printStackTrace();}
    client = clientService.login(accountNumber, password);}
    ///main work
    if(client.getId() != 0)
    {boolean cont = true;
    while (cont){
        cont = mainProcess(client);
    }}
    else
        System.out.println("Your account number or password is wrong");

}
    }

    private boolean mainProcess(Client client) {
        System.out.println("Please, choose the option");
        System.out.println(IOInterface.MENU_LEGENT);
        String input = "";
        try{
        input = ioInterface.readLine();}
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        try{
        switch (input) {
            case "1" :
               try{ List<java.sql.Date> date = clientRepository.checkAnAppointment(client);
                   if (date.size() == 0)
                       System.out.println("No one appointment has been found");
                   else System.out.println("Date: " + date);
               }
               catch (SQLException e) {
                   System.out.println("No one appointment has been found");
               }

                break;
            case "2" :
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
                Date date1 = null;
                try {
                     date1 = ioInterface.readDateValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                java.sql.Date date = new java.sql.Date(date1.getTime());


                    try {
                   int appoin = clientService.checkDate(doctor, date);
                   if (appoin > 0) {
                       System.out.println("This date is unavailable. Please, choose another date or doctor");
                       System.out.println("'Press 1 to continue on; 0 Exit to maim menu");
                       count = ioInterface.readIntValue();
                   }
                   else
                   {clientRepository.getAnAppointment(client, doctor, date);
                   count = 0;}}
               catch (Exception e){
                   System.out.println("Please do again");
               }}}
                break;



            case "3" :
                try{ List<java.sql.Date> date = clientRepository.checkAnAppointment(client);

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
                    }
                    else if( date.size() == 1) { clientRepository.cancelAnAppointment(client);}
                    else if (date.size() == 0) {
                        System.out.println("No one appointment has been found.");
                    }
                }
                catch (SQLException e) {
                    System.out.println("No one appointment has been found");
                }
                break;
           case "4" :

changeDateService.makeChangeDate(client);
                break;

            case "e" :
                return false;
            default:
                System.out.println("Wrong input, please, press another option");
                break;
        }} catch (SQLException e){
            e.printStackTrace();
        }
        return true;

    }


}
