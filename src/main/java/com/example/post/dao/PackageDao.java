package com.example.post.dao;

import com.example.post.dto.Customer;
import com.example.post.dto.Employee;
import com.example.post.dto.Package;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;


@Slf4j
@Repository
public class PackageDao {

    DataSource dataSource;
    public PackageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Customer findCustomerByPhone(String phone){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM Customer WHERE phone = ?";

        List<Customer> customers = jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Customer(
                                rs.getLong("id"),
                                rs.getString("fullName"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getString("password")
                        ),
                phone
                );
        if (customers.size() > 0){
            return customers.get(0);
        }
        else {
            return null;
        }
    }

    public void addEmployee(String name, String phone, String postName){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO Employee (fullName, phone, id_post) VALUES (?, ?, (SELECT id FROM PostOffice WHERE name = ?))";
        log.debug("addEmployee = {} ", sql);

        jdbcTemplate.update(sql, name, phone, postName);
    }

    public void addPostOffice(String name, String address){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO PostOffice (name, address) VALUES (?, ?)";
        log.debug("addPostOffice = {} ", sql);

        jdbcTemplate.update(sql, name, address);
    }

    public void removePostOffice(int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM postoffice WHERE id = ?";
        log.debug("addCustomer = {} ", sql);

        jdbcTemplate.update(sql, id);
    }

    public void addCustomer(String name, String phone, String address, String password){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO Customer (fullName, phone, address, password) VALUES (?, ?, ?, ?)";
        log.debug("addCustomer = {} ", sql);

        jdbcTemplate.update(sql, name, phone, address, password);
    }

    public void addPackage(String name, String phone, String trackNumber, String senderPhone, String recipientPhone){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO Package (name, phone, trackNumber, id_sender, id_recipient) VALUES (?, ?, ?, (SELECT id FROM Customer WHERE phone = ?), (SELECT id FROM Customer WHERE phone = ?))";
        log.debug("addPackage = {} ", sql);

        jdbcTemplate.update(sql, name, phone, trackNumber, senderPhone, recipientPhone);
    }

    public void changePostOffice(String name, String address, int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE PostOffice SET name = ?,  address = ? WHERE id = ?";
        log.debug("addEmployee = {} ", sql);

        jdbcTemplate.update(sql, name, address, id);
    }

    public void changeEmployee(String name, String phone, String postName, int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE Employee SET fullName = ?,  phone = ?,  id_post = (SELECT id FROM PostOffice WHERE name = ?) WHERE id = ?";
        log.debug("addEmployee = {} ", sql);

        jdbcTemplate.update(sql, name, phone, postName, id);
    }

    public List<Package> getPackagesByPhone(String phone){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT name, Package.tracknumber, location, Employee.fullName as employeeName, c.fullName as senderName, c2.fullName as recipientName " +
                "from package inner join employee on package.id_employee = Employee.id " +
                "inner join Customer c on Package.id_sender = c.id " +
                "inner join Customer c2 on Package.id_recipient = c2.id " +
                "inner join location on Package.trackNumber = Location.trackNumber " +
                 "where c2.phone = ?;";

        List<Package> packages = jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                       new Package(
                               rs.getString("name"),
                               rs.getString("trackNumber"),
                               new Customer(null, rs.getString("recipientName"), null, null, null),
                               new Employee(null, rs.getString("employeeName"), null, null),
                               new Customer(null, rs.getString("senderName"), null, null, null),
                               rs.getString("location")
                       ),
                phone
        );

        return packages;
    }

    public void changeLocationForTrackNumber(String track){

    }
}
