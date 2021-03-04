package com.example.Flights.model;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Flight {

  @CsvBindByPosition(position = 0)
  private String code;

  @CsvBindByPosition(position = 1)
  private String src;

  @CsvBindByPosition(position = 2)
  private String des;

  @CsvBindByPosition(position = 3)
  private int dep;

  @CsvBindByPosition(position = 4)
  private int arr;

}
