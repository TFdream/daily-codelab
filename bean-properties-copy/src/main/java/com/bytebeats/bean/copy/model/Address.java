package com.bytebeats.bean.copy.model;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-22 21:57
 */
public class Address {
    private String province;
    private String city;
    private String district;
    private String detail;

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

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
