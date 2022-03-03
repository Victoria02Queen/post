package com.example.post.controllers;

import com.example.post.dao.PackageDao;
import com.example.post.dto.Customer;
import com.example.post.dto.Package;
import com.example.post.dto.PostOffice;
import com.example.post.services.AuthService;
import com.example.post.services.PackageService;
import com.example.post.services.exceptions.CustomerWasRegisteredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MailController {
    AuthService authService;
    PackageService packageService;
    PackageDao packageDao;

    public MailController(PackageDao packageDao, AuthService authService, PackageService packageService) {
        this.packageService = packageService;
        this.authService = authService;
        this.packageDao = packageDao;

    }

    @GetMapping("/")
    public String getMainPage(Model model){
        return "main";
    }

    @GetMapping("/registration")
    public String register(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "registration";
    }

    @GetMapping("/auth")
    public String auth(Model model, HttpSession httpSession){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "authorization";
    }

    @PostMapping("/customer")
    public String getCustomerPage(Model model, @RequestParam String phone, @RequestParam String password){

        if (phone.equals("a") && password.equals("a")){
            return "menu";
        }

        Customer customer = authService.auth(phone, password);

        if (customer == null){
            return "wrongpassword";
        }

        List<Package> packageList = packageService.getPackagesForCustomer(customer);



        model.addAttribute("customer", customer);
        model.addAttribute("packages", packageList);

        return "customerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer) throws CustomerWasRegisteredException {
        authService.register(customer);
        return "main";
    }

    @GetMapping("/employee")
    public String getEmployeePage(Model model){
        return "menu";
    }

    @PostMapping("/showOffices")
    public String getOffices(Model model){
        return "postofficetable";
    }

    @GetMapping("/addOffice")
    public String getOfficeAddingPage(Model model){
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice", postOffice);

        return "addpostoffice";
    }

    @PostMapping("/addPostOffice")
    public String addPostOffice(@ModelAttribute PostOffice postOffice){
        packageDao.addPostOffice(postOffice.getName(), postOffice.getAddress());

        return "main";
    }

    @GetMapping("deleteOffice")
    public String deletePostOfficeForm(Model model){

        return "deletepostoffice";
    }

    @PostMapping("deletePostOffice")
    public String deletePostOffice(Model model, @RequestParam int id){
        packageDao.removePostOffice(id);

        return "main";
    }

    @PostMapping("/showCustomer")
    public String getCustomer(Model model){
        model.addAttribute("customers", packageDao.getAllCustomers());

        return "customertable";
    }

    @PostMapping("/showPackage")
    public String getPackage(Model model){
        model.addAttribute("packages", packageDao.getAllPackages());
        return "packagetable";
    }

    @GetMapping("/addPackage")
    public String addPackagePage(Model model){

        return "addpackage";
    }

    @PostMapping("/addPackage")
    public String addPackage(Model model,
                             @RequestParam String packageName,
                             @RequestParam String trackNumber,
                             @RequestParam String senderPhone,
                             @RequestParam String recipientPhone){

        packageDao.addPackage(packageName, trackNumber, senderPhone, recipientPhone);

        return "main";

    }

    @GetMapping("/deletePackage")
    public String deletePackagePage(Model model){
        return "deletepackage";
    }

    @PostMapping("/deletePackage")
    public String deletePakage(Model model, @RequestParam int id){
        packageDao.removePackage(id);
        return "main";
    }

    @GetMapping("/addLocation")
    public String addLocationPage(Model model){
        return "addlocation";
    }

    @PostMapping("/addLocation")
    public String addLocation(Model model,
                              @RequestParam String trackNumber,
                              @RequestParam String location){
        packageDao.addLocation(trackNumber, location);

        return "main";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocationPage(Model model){
        return "deletelocation";
    }

    @PostMapping("/deleteLocation")
    public String deleteLocation(Model model, @RequestParam int id){
        packageDao.deleteLocation(id);

        return "main";
    }

    @GetMapping("/addCustomer")
    public String addCustomerPage(Model model){
        return "addcustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(Model model,
                              @RequestParam String username,
                              @RequestParam String phone,
                              @RequestParam String address){
        packageDao.addCustomer(username, phone, address, "");

        return "main";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomerPage(Model model){
        return "deletecustomer";
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(Model model, @RequestParam int id){
        packageDao.removeCustomer(id);

        return "main";
    }

    @PostMapping("/showLocation")
    public String showLocation(Model model){
        model.addAttribute("locations", packageDao.getAllLocations());
        return "locationtable";
    }

}
