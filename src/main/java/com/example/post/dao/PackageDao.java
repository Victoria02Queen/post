package com.example.post.dao;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Slf4j
@Repository
public class PackageDao {

    DataSource dataSource;
    public PackageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addEmployee(String name, String phone, String postName){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);//положить dataSource в аргумент

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
}
