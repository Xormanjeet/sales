package com.sales.sales.Model;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sales {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer store;
    @Column
    private String store_name;
    @Column
    private LocalDate date;
    @Column
    private Long weekly_sales_amount;
    @Column
    private String holiday_flag;}
