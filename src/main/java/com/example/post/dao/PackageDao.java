package com.example.post.dao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Slf4j
@Repository
public class PackageDao {
    public void addEmployee(String name, String phone, String postName){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(null);//положить dataSource в аргумент

        String sql = "INSERT INTO Employee (fullName, phone, id_post) VALUES (?, ?, (SELECT id FROM PostOffice WHERE name = ?))";
        log.debug("addEmployee = {} ", sql);

        jdbcTemplate.update(sql, name, phone, postName);
    }

    public void addPostOffice(String name, String address){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

        String sql = "INSERT INTO PostOffice (name, address) VALUES (?, ?)";
        log.debug("addPostOffice = {} ", sql);

        jdbcTemplate.update(sql, name, address);
    }

    public void addCustomer(String name, String phone, String address){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

        String sql = "INSERT INTO Customer (fullName, phone, address) VALUES (?, ?, ?)";
        log.debug("addCustomer = {} ", sql);

        jdbcTemplate.update(sql, name, phone, address);
    }

    public void addPackage(String name, String phone, String trackNumber, String senderPhone, String recipientPhone){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

        String sql = "INSERT INTO Package (name, phone, trackNumber, id_sender, id_recipient) VALUES (?, ?, ?, (SELECT id FROM Customer WHERE phone = ?), (SELECT id FROM Customer WHERE phone = ?))";
        log.debug("addPackage = {} ", sql);

        jdbcTemplate.update(sql, name, phone, trackNumber, senderPhone, recipientPhone);
    }
}
