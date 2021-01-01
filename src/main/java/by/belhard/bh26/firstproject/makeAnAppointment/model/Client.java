package by.belhard.bh26.firstproject.makeAnAppointment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode (of = "id")
@AllArgsConstructor
@Builder
public class Client {


    private int id;
    private String lastname;
    private String firstname;
    private int accountNumber;
    private String password;
}
