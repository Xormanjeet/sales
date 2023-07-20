package com.sales.sales.Repository;

import com.sales.sales.Model.holidayWiseSaleDTO;
import com.sales.sales.Model.monthWiseSaleDTO;
import com.sales.sales.Model.Sales;
import java.util.List;

import com.sales.sales.Model.storeWiseWeeklysaleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Sales, Integer> {
    List<Sales> findByStore(Integer store);

    @Query("SELECT distinct YEAR(m.date) from Sales m where YEAR(m.date)=:year")
    Integer findByYear(Integer year);

    @Query("SELECT new com.sales.sales.Model.storeWiseWeeklysaleDTO(m.store,concat(round(SUM(m.weekly_sales_amount)*100/(select sum(t.weekly_sales_amount) from Sales t)),'%')) "
            +"FROM Sales AS m GROUP BY m.store")
    List<storeWiseWeeklysaleDTO> WeeklysalesByStorewise();

    @Query("SELECT new com.sales.sales.Model.monthWiseSaleDTO(month(m.date),round(sum(m.weekly_sales_amount))) "
            +"FROM Sales AS m WHERE m.store=:storeId and YEAR(m.date)=:year GROUP BY month(m.date) order by month(m.date)")
    List<monthWiseSaleDTO> WeeklysalesByMonthwise(Integer storeId, Integer year);

    @Query("SELECT new com.sales.sales.Model.holidayWiseSaleDTO(m.store,round(SUM(CASE WHEN holiday_flag= '0' THEN (m.weekly_sales_amount) ELSE 0 END)),"
            +" round(SUM(CASE WHEN holiday_flag= '1' THEN (m.weekly_sales_amount) ELSE 0 END))) "
            +"FROM Sales AS m GROUP BY m.store ORDER BY m.store")
    List<holidayWiseSaleDTO> WeeklysalesByHolidayAndNonHolidayWise();

}
