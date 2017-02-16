package com.bytebeats.code.serialization.hessian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.bytebeats.code.serialization.model.Car;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

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
		
		List<Car> list = new ArrayList<>();
		Car car1 = new Car();
		car1.setName("X5");
		car1.setBrand("BMW");
		car1.setPrice(64.5);
		car1.setSpeed(200);
		list.add(car1);
		
		Car car2 = new Car();
		car2.setName("K5");
		car2.setBrand("KIA");
		car2.setPrice(24.5);
		car2.setSpeed(180);
		list.add(car2);
		
		System.out.println(list);
		
		//Serialization
		Hessian2Output out = null;
		try {
			
			out = new Hessian2Output(new FileOutputStream(objectFile));
			out.startMessage();
			//长度
			out.writeInt(list.size());

			for (Car car : list) {
				out.writeObject(car);
			}
			
			out.completeMessage();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

			int length = in.readInt();
			ArrayList<Car> list = new ArrayList<>(length);
			
			for (int i = 0; i < length; i++) {
			  list.add((Car) in.readObject());
			}

			in.completeMessage();
			
			System.out.println(list);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
