package com.fivestars.lab05_1711060794;


import java.io.Serializable;

public class Helper implements Serializable {
    private String Name;
    private String Address;
    private String Area;
    private String Country;
    private String ZipCode;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    @Override
    public String toString(){
        return (getName());
    }
}
