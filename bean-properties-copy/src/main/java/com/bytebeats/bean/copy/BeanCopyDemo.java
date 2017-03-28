package com.bytebeats.bean.copy;

import com.bytebeats.bean.copy.model.Address;
import com.bytebeats.bean.copy.model.Customer;
import com.bytebeats.bean.copy.model.Employee;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class BeanCopyDemo {

    private int count = 100000;

    public static void main( String[] args ) throws Exception {

        new BeanCopyDemo().testTime("SpringBeanUtils");
    }

    public void testTime(String name) throws Exception {
        Customer customer = getCustomer();
        Employee employee = new Employee();

        long start = System.currentTimeMillis();
        switch (name){
            case "ApacheBeanUtils":
                testApacheBeanUtils(customer, employee);
                break;
            case "ApachePropertyUtils":
                testApachePropertyUtils(customer, employee);
                break;
            case "SpringBeanUtils":
                testSpringBeanUtils(customer, employee);
                break;
            case "CglibBeanCopier":
                testCglibBeanCopier(customer, employee);
                break;
        }

        System.out.println(name+" count: "+count+" cost: "+(System.currentTimeMillis() - start)+" ms");
    }

    public void testCglibBeanCopier(Object src, Object target){

        BeanCopier copier = BeanCopier.create(src.getClass(), target.getClass(), false);
        copier.copy(src, target, null);
    }

    public void testSpringBeanUtils(Object src, Object target){
        for(int i=0; i<count; i++){
            org.springframework.beans.BeanUtils.copyProperties(src, target);
        }
    }

    public void testApacheBeanUtils(Object src, Object dest) throws InvocationTargetException, IllegalAccessException {
        for(int i=0; i<count; i++){
            BeanUtils.copyProperties(dest, src);
        }
    }

    public void testApachePropertyUtils(Object src, Object dest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for(int i=0; i<count; i++){
            PropertyUtils.copyProperties(dest, src);
        }
    }

    private Customer getCustomer(){

        Customer customer = new Customer();
        customer.setId(15L);
        customer.setName("ricky");
        customer.setAge(28);

        List<String> hobbies = new ArrayList<>(4);
        hobbies.add("汽车");
        hobbies.add("旅游");
        hobbies.add("体育");
        hobbies.add("NBA");
        customer.setHobbies(hobbies);

        Address address = new Address();
        address.setProvince("湖北省");
        address.setCity("武汉市");
        address.setDistrict("武昌区");
        address.setDetail("欢乐大道28号");
        customer.setAddress(address);

        return customer;
    }
}
