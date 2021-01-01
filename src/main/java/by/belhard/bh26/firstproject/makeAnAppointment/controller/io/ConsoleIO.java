package by.belhard.bh26.firstproject.makeAnAppointment.controller.io;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConsoleIO implements IOInterface {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public Date readDateValue() throws IOException, NumberFormatException {
        String input = reader.readLine();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date inputDate = null;
        try {
            inputDate = inputFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  inputDate;
    }

    @Override
    public int readIntValue() throws IOException, NumberFormatException {
       Integer id = Integer.parseInt(reader.readLine());
        return id;
    }
}
