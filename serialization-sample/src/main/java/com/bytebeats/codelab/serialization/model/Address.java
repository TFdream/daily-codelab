package com.bytebeats.codelab.serialization.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-22 12:18
 */
public class Address implements Serializable {

    private static final long serialVersionUID = -677602304925255450L;

    private String province;
    private String city;
    private String district;
    private String detail;

    public Address() {

    }
    public Address(String province, String city, String district, String detail) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.detail = detail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeUTF("Hello World");
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        String value = input.readUTF();
        System.out.println(value);
    }
}
