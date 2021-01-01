package by.belhard.bh26.firstproject.makeAnAppointment.controller.io;

import java.io.IOException;
import java.util.Date;

public interface IOInterface {

    String MENU_LEGENT =
            "\t1. chech an appointment\n" +
            "\t2. make an appointment\n" +
            "\t3. cancele an appointment\n" +
            "\t4. change the date\n" +
            "\te. exit\n";

    String MENU_SPECIALITIES =
                    "\t1. chech an appointment\n" +
                    "\t2. make an appointment\n" +
                    "\t3. cancele an appointment\n" +
                    "\t4. change the date\n" +
                    "\te. exit\n";

    String readLine() throws IOException;
    Date readDateValue() throws IOException, NumberFormatException;
    int readIntValue () throws IOException, NumberFormatException;
}
