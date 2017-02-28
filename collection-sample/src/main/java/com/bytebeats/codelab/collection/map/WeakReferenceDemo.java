package com.bytebeats.codelab.collection.map;

import com.bytebeats.codelab.collection.model.Car;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 13:59
 */
public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        new WeakReferenceDemo().testWeakReference();
    }

    public void testWeakReference(){

        Car car = new Car("Honda-Accord", 198000f,"white");

        WeakReference<Car> weakCar = new WeakReference<>(car);

        System.gc();

        int i=0;
        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
                sleep(1000);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

    private void sleep(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
