package com.bytebeats.codelab.serialization.hessian;

import com.bytebeats.codelab.serialization.model.Car;
import com.bytebeats.codelab.serialization.util.IoUtils;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * http://www.caucho.com/resin-3.1/examples/hessian-serialize/index.xtp
 * @author Ricky
 *
 */
public class HessianByteSerializationDemo {
	
	public static void main(String[] args) {
		
		HessianByteSerializationDemo demo = new HessianByteSerializationDemo();
		byte[] data = demo.serialize();
		System.out.println(data.length);
		demo.deserialize(data);
	}
	
	public byte[] serialize(){
		
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
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			out = new Hessian2Output(bos);
			out.startMessage();
			//长度
			out.writeInt(list.size());

			for (Car car : list) {
				out.writeObject(car);
			}
			
			out.completeMessage();
			out.flush();
			
			return bos.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void deserialize(byte[] data){
		
		ByteArrayInputStream bin = null;
		Hessian2Input in = null;
		try {
			bin = new ByteArrayInputStream(data);
			in = new Hessian2Input(bin);

			in.startMessage();

			int length = in.readInt();
			ArrayList<Car> list = new ArrayList<>(length);
			
			for (int i = 0; i < length; i++) {
			  list.add((Car) in.readObject());
			}

			in.completeMessage();
			
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			IoUtils.closeQuietly(bin);
		}
	}
}
