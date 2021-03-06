package com.codegym.controller;

import com.codegym.exception.CustomerNotFound;
import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.model.Province;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/demo")
@PropertySource("classpath:upload_file.properties")
public class DemoController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @Value("${file-upload}")
    private String fileUpload;

    @ModelAttribute("dstinh")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @GetMapping()
    public ModelAndView demo(){
        Iterable<Customer> customers = customerService.findAll();
        return new ModelAndView("home", "customers", customers);
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Long id) throws CustomerNotFound {
        ModelAndView modelAndView = new ModelAndView("detail");
        Customer customer = customerService.findById(id).get();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ModelAndView showNotFound(){
        return new ModelAndView("notfound");
    }

    @PostMapping("/create")
    public ModelAndView createNewCustomer(@Validated @ModelAttribute("customer") CustomerForm customerForm,BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()){

            //lay file anh
            MultipartFile file = customerForm.getAvatar();
            //lay ten file
            String fileName = file.getOriginalFilename();

            //lay thong tin cua customer
            String name = customerForm.getFirstName();
            String lastName = customerForm.getLastName();
            Province province = customerForm.getProvince();

            //coppy file
//            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload+ fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            Customer customer = new Customer(name,lastName, fileName, province);

            customerService.save(customer);
            return new ModelAndView("create", "mess", "Tao moi thanh cong");
        }
        return new ModelAndView("create");
    }

}
