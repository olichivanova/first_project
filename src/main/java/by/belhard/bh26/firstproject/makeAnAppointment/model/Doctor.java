package by.belhard.bh26.firstproject.makeAnAppointment.model;

import lombok.*;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Doctor {

    private int id;
    private String lastname;
    private String firstname;
    private String speciality;

    @Override
    public String toString() {
        return
                "\n " + id + " " +
                  lastname + " "
               + firstname + " "
                + speciality ;

    }
}
