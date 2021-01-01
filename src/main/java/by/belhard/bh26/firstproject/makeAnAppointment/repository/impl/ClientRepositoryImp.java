package by.belhard.bh26.firstproject.makeAnAppointment.repository.impl;

import by.belhard.bh26.firstproject.makeAnAppointment.model.Client;
import by.belhard.bh26.firstproject.makeAnAppointment.model.Doctor;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.ClientRepository;
import by.belhard.bh26.firstproject.makeAnAppointment.repository.ConnectionImpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientRepositoryImp implements ClientRepository {
    private static final String GET_BY_ACCOUNT_NUMBER_QUERY =
            "select * from client where account_number = ? and password = ?";
    private static final String CHECK_AN_APPOINTMENT_QUERY =
            "select date from appointment where client_id = ? ";
    private static final String ADD_NEW_APPOINTMENT =
            "insert into appointment (id, client_id, doctor_id, date) values (null, ?, ?, ?) ";
    private static final String DELETE_BY_ID =
            "delete from appointment where client_id = ?";
    private static final String GET_DOCTOR=
            "select * from specialist where id_sp = ?";
    private static final String CHECK_DATE=
            "select * from appointment where doctor_id = ? and date = ? ";
    private static final String DELETE_BY_DATE =
            "delete from appointment where date = ?";

    @Override
    public Client getByAccount(int accountNumber, String password) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BY_ACCOUNT_NUMBER_QUERY);
        statement.setInt(1, accountNumber);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        String lastname = "";
        String firstname = "";

        while (resultSet.next()){
            id = resultSet.getInt("id");
            lastname = resultSet.getString("lastname");
            firstname = resultSet.getString("firstname");
        }

        return Client.builder().id(id).lastname(lastname).firstname(firstname).accountNumber(accountNumber).password(password).build();
    }

    @Override
    public List<Date> checkAnAppointment(Client client) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(CHECK_AN_APPOINTMENT_QUERY);
        statement.setInt(1, client.getId());
        ResultSet resultSet = statement.executeQuery();
        List<Date> list = new ArrayList<>();
        while (resultSet.next()) {
            Date date = resultSet.getDate("date");
            list.add(date);
        }
        if (list != null)
            return list;
        else return null;

    }

    @Override
    public void getAnAppointment(Client client, Doctor doctor, Date date) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_NEW_APPOINTMENT);
       statement.setInt(1, client.getId());
        statement.setInt(2, doctor.getId());
        statement.setDate(3, date);
        int row = statement.executeUpdate();
        if(row > 0)
        {System.out.printf("%d appointment was successfully made", row);
            System.out.println();}
        else
            System.out.println("Wrong input");


    }

    @Override
    public void cancelAnAppointment(Client client) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setInt(1, client.getId());
        int row = statement.executeUpdate();
        if(row > 0)
        {System.out.printf("%d appointment was successfully canceled ", row);
        System.out.println();}
        else
            System.out.println("Check,please your appointment");
    }

    @Override
    public Doctor chooseDoctor(int id) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_DOCTOR);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        int id_sp = 0;
        String lastname = "";
        String firstname = "";
        String speciality = "";

        while (resultSet.next()){
            id_sp = resultSet.getInt("id_sp");
            lastname = resultSet.getString("lastname_sp");
            firstname = resultSet.getString("firstname_sp");
            speciality = resultSet.getString("specialty");
        }

        return Doctor.builder().id(id_sp).lastname(lastname).firstname(firstname).speciality(speciality).build();
    }

    @Override
    public int checkDate(Doctor doctor, Date date) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(CHECK_DATE);
        statement.setInt(1, doctor.getId());
        statement.setDate(2, date);
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        while (resultSet.next()){
             id = resultSet.getInt("id");
        }

         return id;
    }

    @Override
    public void cancelAnAppointmentByDate(Date date) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_DATE);
        statement.setDate(1, date);
        int row = statement.executeUpdate();
        if(row > 0)
        {System.out.printf("%d appointment was successfully canceled ", row);
            System.out.println();}
        else
            System.out.println("Check,please your appointment");
    }
}
