package com.bytebeats.codelab.bean.validation.model;

import com.bytebeats.codelab.bean.validation.annotation.Status;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Order {
    @NotNull(message = "orderId必须不为null")
    @Size(min = 10, max = 10)
    private String orderId;

    @NotEmpty(message = "customer须不为空")
    private String customer;

    @Email(message = "email必须是一个电子信箱地址")
    private String email;

    @NotEmpty(message = "address必须不为空")
    private String address;
    // 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
    // @Status 是一个定制化的 contraint
    @NotNull(message = "status必须不为 null")
    @Status
    private String status;

    @NotNull(message = "createDate必须不为 null")
    private Date createDate;

    // 嵌套验证
    @Valid
    private Product product;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}