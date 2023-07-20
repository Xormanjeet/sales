package com.sales.sales;

import com.sales.sales.Exception.StoreDetailsFoundException;
import com.sales.sales.Model.Sales;
import com.sales.sales.Model.holidayWiseSaleDTO;
import com.sales.sales.Model.monthWiseSaleDTO;
import com.sales.sales.Model.storeWiseWeeklysaleDTO;
import com.sales.sales.Repository.StoreRepository;
import com.sales.sales.Service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class serviceWiseUnitTestCases {
    @Mock
    private StoreRepository salesRepository;
    @InjectMocks
    private StoreService storeService;

    @Test
    public void testGetWeeklysalesByStorewise()
    {
        storeWiseWeeklysaleDTO s=new storeWiseWeeklysaleDTO();
        s.setStore_id(11);
        s.setWeekly_sales("10%");
        when(salesRepository.WeeklysalesByStorewise()).thenReturn(List.of(s));
        List<storeWiseWeeklysaleDTO> Lists=storeService.getWeeklysalesByStorewise();
        assertEquals(List.of(s),Lists);
    }
  @Test
    public void testGetWeeklysalesByMonthwise()
   {
    monthWiseSaleDTO m=new monthWiseSaleDTO();
    m.setMonth(7);
    m.setMonthlySales(1234566l);

    when(salesRepository.findByStore(11)).thenReturn(List.of(new Sales(1,11,"abc", LocalDate.now(),1234566l,"0")));
    List<Sales> s= salesRepository.findByStore(11);
    Integer id=s.get(0).getStore();

    when(salesRepository.findByYear(2010)).thenReturn(2010);
    Integer year=salesRepository.findByYear(2010);

    when(salesRepository.WeeklysalesByMonthwise(id,year)).thenReturn(List.of(m));
    List<monthWiseSaleDTO> Lists=storeService.getWeeklysalesByMonthwise(id,year);
    assertEquals(List.of(m),Lists);
   }
 @Test
 public void testGetWeeklysalesByMonthwiseStoreIdNotFound() {
        int storeId=11;
     when(salesRepository.findByStore(11)).thenThrow(new StoreDetailsFoundException("Store ID not found -->"+storeId));
     try {
         storeService.getWeeklysalesByMonthwise(11, 2010);
     } catch (Exception e) {
         assertEquals("Store ID not found -->"+storeId, e.getMessage());
     }
 }
 @Test
 public void testGetWeeklysalesByMonthwiseYearNotFound() {
     int year = 2010;
     int storeId=11;
     when(salesRepository.findByStore(11)).thenReturn(List.of(new Sales(1,11,"abc", LocalDate.now(),1234566l,"0")));
     when(salesRepository.findByYear(2010)).thenThrow(new StoreDetailsFoundException("Year not found -->" + year));
     try {
         storeService.getWeeklysalesByMonthwise(11, 2010);
     } catch (Exception ex) {
         assertEquals("Year not found -->" + year, ex.getMessage());
     }
 }
   @Test
   public void testGetWeeklysalesByHolidayAndNonHolidayWise()
   {
       holidayWiseSaleDTO h=new holidayWiseSaleDTO();
       h.setHoliday_sales(1234566l);
       h.setNon_holiday_sales(1234566l);
       when(salesRepository.WeeklysalesByHolidayAndNonHolidayWise()).thenReturn(List.of(h));
       List<holidayWiseSaleDTO> Lists=storeService.getWeeklysalesByHolidayAndNonHolidayWise();
       assertEquals(List.of(h),Lists);
   }
}
