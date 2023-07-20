package com.sales.sales.Controller;

import com.sales.sales.Model.holidayWiseSaleDTO;
import com.sales.sales.Model.monthWiseSaleDTO;
import com.sales.sales.Model.storeWiseWeeklysaleDTO;
import com.sales.sales.Service.StoreService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Validated
public class StoreController {
    @Autowired
    private StoreService service;
    @GetMapping("/api/v1/sales")
    public ResponseEntity<List<storeWiseWeeklysaleDTO>> getSales_storewise() {
         List<storeWiseWeeklysaleDTO> s=service.getWeeklysalesByStorewise();
          return ResponseEntity.ok().body(s);}
    @GetMapping("/api/v2/sales")
    public ResponseEntity<List<monthWiseSaleDTO>> getSales_monthwise(@RequestParam("storeId") @Positive(message = "Store ID must be greater than zero") Integer storeId,@RequestParam("year") @Positive(message = "Year must be greater than zero") Integer year) {
        List<monthWiseSaleDTO> s = service.getWeeklysalesByMonthwise(storeId, year);
        return ResponseEntity.ok().body(s);}
    @GetMapping("/api/v1/sales/holiday-vs-nonholiday-analysis")
    public ResponseEntity<List<holidayWiseSaleDTO>> getSales_holiday_vs_nonholiday()
    {
        List<holidayWiseSaleDTO> s=service.getWeeklysalesByHolidayAndNonHolidayWise();
        System.out.println("s"+s);
        return ResponseEntity.ok().body(s);}
   }
