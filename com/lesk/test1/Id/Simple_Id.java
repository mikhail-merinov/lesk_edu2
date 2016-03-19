package com.lesk.test1.Id;
import java.util.Random;

public abstract class Simple_Id{

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Simple_Id other = (Simple_Id) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
