package com.codegym.controller;

import com.codegym.exception.CustomerNotFound;
import com.codegym.model.Province;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/province")
@CrossOrigin("*")
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/list")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Province>> getAll(){
        Iterable<Province> provinces = provinceService.findAll();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Province> getById(@PathVariable Long id) throws CustomerNotFound {
        Province provinces = provinceService.findById(id).get();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Province> deleteById(@PathVariable Long id) throws CustomerNotFound {
        Province provinces = provinceService.findById(id).get();
        if (provinces != null) provinceService.remove(id);
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Province> editById(@PathVariable Long id, @RequestBody Province province) throws CustomerNotFound {
        province.setId(id);
        provinceService.save(province);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping("")
    public ResponseEntity<Province> create(@RequestBody Province province) throws CustomerNotFound {
        provinceService.save(province);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
