package com.bytebeats.codelab.bean.validation.model;

import com.bytebeats.codelab.bean.validation.annotation.Price;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-07 00:21
 */
public class Product {
    // 必须非空
    @NotEmpty
    private String name;
    // 必须在 8000 至 10000 的范围内
    // @Price 是一个定制化的 constraint
    @Price
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
