package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping({"/list", "/"})
    public ModelAndView getAllCars() {
        ModelAndView mav = new ModelAndView("list-cars");
        mav.addObject("cars", carRepository.findAll());
        return mav;
    }

    @GetMapping("/addCarForm")
    public ModelAndView addCarForm() {
        ModelAndView mav = new ModelAndView("add-car-form");
        Car newCar = new Car();
        mav.addObject("car", newCar);
        return mav;
    }

    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long carId) {
        ModelAndView mav = new ModelAndView("add-car-form");
        Car car = carRepository.findById(carId).get();
        mav.addObject("car", car);
        return mav;
    }

    @GetMapping("/deleteCar")
    public String deleteEmployee(@RequestParam Long carId) {
        carRepository.deleteById(carId);
        return "redirect:/list";
    }
}