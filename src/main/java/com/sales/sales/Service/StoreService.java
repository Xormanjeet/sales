package com.sales.sales.Service;


import com.sales.sales.Exception.StoreDetailsFoundException;
import com.sales.sales.Model.Sales;
import com.sales.sales.Model.holidayWiseSaleDTO;
import com.sales.sales.Model.monthWiseSaleDTO;
import com.sales.sales.Model.storeWiseWeeklysaleDTO;
import com.sales.sales.Repository.StoreRepository;
import org.modelmapper.internal.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;
 public List<storeWiseWeeklysaleDTO> getWeeklysalesByStorewise(){
     List<storeWiseWeeklysaleDTO> s=repository.WeeklysalesByStorewise();
     return s;
 }public List<monthWiseSaleDTO> getWeeklysalesByMonthwise(Integer storeId, Integer year)
 {
        List<Sales> store= repository.findByStore(storeId);
        if(store.isEmpty())
        {
            throw new StoreDetailsFoundException("Store ID not found -->"+storeId);
        }
        Integer Year=repository.findByYear(year);
        if(Year==null)
        {
            throw new StoreDetailsFoundException("Year not found -->"+year);
        }
       List<monthWiseSaleDTO> s=repository.WeeklysalesByMonthwise(storeId,year);
       return s;
 }
 public List<holidayWiseSaleDTO> getWeeklysalesByHolidayAndNonHolidayWise(){
     List<holidayWiseSaleDTO> s=repository.WeeklysalesByHolidayAndNonHolidayWise();
     return s;
 }
 }




