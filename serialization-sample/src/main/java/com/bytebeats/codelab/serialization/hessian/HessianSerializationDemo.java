package com.bytebeats.codelab.serialization.hessian;

import com.bytebeats.codelab.serialization.model.Car;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.*;

/**
 * http://www.caucho.com/resin-3.1/examples/hessian-serialize/index.xtp
 * @author Ricky
 *
 */
public class HessianSerializationDemo {
	
	File objectFile = new File("car.bin");
	
	public static void main(String[] args) {
		
		HessianSerializationDemo demo = new HessianSerializationDemo();
		demo.serialize();
		demo.deserialize();
	}
	
	public void serialize(){

		Car car = new Car();
		car.setName("X5");
		car.setBrand("BMW");
		car.setPrice(64.5);
		car.setSpeed(200);

		System.out.println("序列化:"+car);

		//Serialization
		Hessian2Output out = null;
		try {
			
			out = new Hessian2Output(new FileOutputStream(objectFile));
			out.startMessage();
			out.writeObject(car);
			out.completeMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deserialize(){
		
		InputStream bin = null;
		Hessian2Input in = null;
		try {
			bin = new FileInputStream(objectFile);
			in = new Hessian2Input(bin);

			in.startMessage();
			Car car = (Car) in.readObject();
			in.completeMessage();
			
			System.out.println("反序列化:"+car);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
