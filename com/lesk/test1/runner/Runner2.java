package com.lesk.test1.runner;

import com.lesk.test1.Id.Simple_Id;
import com.lesk.test1.meter.Meter_Id;

public class Runner2 {

	public static void main(String[] args) {

		Simple_Id id = new Simple_Id(1);
		System.out.println(id);
		
		Meter_Id meterId = new Meter_Id();
		System.out.println(meterId);
		

	}

}
