package com.lesk.test1.Id;
import java.util.Random;

public class Simple_Id{

	private String id;
        
	// получить случайный ID
	public Simple_Id() {
		//System.out.println("Simple_Id(): id = random value");
		id = String.valueOf(new Random().nextInt(2_000_000_000));
	}
        
	// получить ID в диапазоне 0..range
	public Simple_Id(int range) {
		//System.out.println("Simple_Id(int range="+range+")");
		id = String.valueOf(new Random().nextInt(range));
	}
        
	// установить ID
	public Simple_Id(String id) {
		//System.out.println("Simple_Id(String id="+id+")");
		this.id = id;
	}

	// getters
	public String getId() {return id;}

	// setters
	public void setId(String id) {this.id = id;}

	// для печати
	@Override
	public String toString() {
		return "(" + this.getClass().getSimpleName()+", id=" + id + ")";
	}

}
