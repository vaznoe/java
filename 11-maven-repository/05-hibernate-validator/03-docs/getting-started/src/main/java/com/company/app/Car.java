package com.company.app;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Run CarTest under Test folder
// http://hibernate.org/validator/documentation/getting-started/
public class Car {
  @NotNull
  private String manufacturer;

  @NotNull
  @Size(min = 2, max = 14)
  private String licensePlate;

  @Min(2)
  private int seatCount;

  public Car(String manufacturer, String licensePlate, int seatCount){
    this.manufacturer = manufacturer;
    this.licensePlate = licensePlate;
    this.seatCount = seatCount;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public int getSeatCount() {
    return seatCount;
  }

  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }
}
