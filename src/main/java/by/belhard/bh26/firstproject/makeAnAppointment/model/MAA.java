package by.belhard.bh26.firstproject.makeAnAppointment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class MAA {
    private int id;
    private Client client;
    private Doctor doctor;
    private Date date;

    public MAA() {

    }
}
