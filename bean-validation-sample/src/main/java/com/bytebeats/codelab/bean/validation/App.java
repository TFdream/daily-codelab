package com.bytebeats.codelab.bean.validation;

import com.bytebeats.codelab.bean.validation.model.Order;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        //调用JSR303验证工具，校验参数
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Order order = new Order();
        order.setOrderId("12345");
        order.setCustomer("ricky");
        order.setEmail("ricky");
        order.setAddress("北京市朝阳区");
        order.setStatus("DELV");
        order.setCreateDate(new Date());

        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        for(ConstraintViolation<Order> violation: violations) {
            System.out.println(violation.getPropertyPath()+"\t"+ violation.getMessage());
        }
    }
}
