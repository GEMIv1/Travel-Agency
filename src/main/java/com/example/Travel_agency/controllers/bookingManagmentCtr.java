package com.example.Travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookingManagmentCtr {

    @Autowired
    // p arr 2 bur 6
    // p arr 3 bur 4
    // 55
    /*
     *
     * search [hotls] (@RequestParam hotel_name, @RequestParam minPrice, @RequestParam maxPrice, @RequestParam room_type)
     * search [events] (@RequestParam)
     * book hotels ->  start date , end date , calculate price , hotel name , room type
     * book events ->
     * 
    */

    @GetMapping("/travel_agency/searh_hotels")
    public void searchHotels(
        @RequestParam String hotel_name,
        @RequestParam(required = false) String minPrice,
        @RequestParam(required = false) String maxPrice,
        @RequestParam(required = false) String roomType,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate) {

        


    }
}