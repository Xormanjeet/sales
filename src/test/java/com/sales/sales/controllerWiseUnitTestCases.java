package com.sales.sales;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.sales.Controller.StoreController;
import com.sales.sales.Model.holidayWiseSaleDTO;
import com.sales.sales.Model.monthWiseSaleDTO;
import com.sales.sales.Model.storeWiseWeeklysaleDTO;
import com.sales.sales.Service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = StoreController.class)
public class controllerWiseUnitTestCases {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetWeeklysalesByStorewise() throws Exception {
        // write test cases here
        storeWiseWeeklysaleDTO s=new storeWiseWeeklysaleDTO();
        s.setStore_id(11);
        s.setWeekly_sales("10%");
        when(storeService.getWeeklysalesByStorewise()).thenReturn(List.of(s));
           mockMvc.perform(get("/api/v1/sales"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].store_id").value(11))
                    .andExpect(jsonPath("$[0].weekly_sales").value("10%"))
                    .andDo(print());
    }
    @Test
    public void testGetWeeklySalesByHolidayAndNonHoliday() throws Exception
    {
        holidayWiseSaleDTO h=new holidayWiseSaleDTO();
        h.setHoliday_sales(1234566l);
        h.setNon_holiday_sales(1234566l);
        when(storeService.getWeeklysalesByHolidayAndNonHolidayWise()).thenReturn(List.of(h));
        mockMvc.perform(get("/api/v1/sales/holiday-vs-nonholiday-analysis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].holiday_sales").value(1234566l))
                .andExpect(jsonPath("$[0].non_holiday_sales").value(1234566l))
                .andDo(print());

    }
    @Test
    public void testGetWeeklySalesByMonthwise() throws Exception{
        monthWiseSaleDTO m=new monthWiseSaleDTO();
        m.setMonth(7);
        m.setMonthlySales(1234566l);
        when(storeService.getWeeklysalesByMonthwise(11,2011)).thenReturn(List.of(m));
        mockMvc.perform(get("/api/v2/sales")
                .param("storeId","11")
                .param("year","2011"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].month").value(7))
                .andExpect(jsonPath("$[0].monthlySales").value(1234566l))
                .andDo(print());
    }
}
