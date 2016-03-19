package com.lesk.test1.Entity;

import java.sql.Date;

// Этот класс хранить характеристику
public class Char implements Comparable<Char>{
	String charType;
	String charVal;
	Date charDate;
	//Simple_Id meterId;

	// Конструкторы
	Char(String charType, String charVal, Date charDate) { //, Simple_Id meterId) {
		this.charType = charType;
		this.charVal = charVal;
		this.charDate = charDate;
		//this.meterId = meterId;         
	}
        
	// Getters
	public String getCharType()     {return charType;       }
	public String getCharVal()      {return charVal;        }
	public Date getCharDate()       {return charDate;       }
	//public Simple_Id getMeterId()   {return meterId;        }
        
	// Setters
	public void setCharType(String charType) {this.charType = charType;     }
	public void setCharVal(String charVal)   {this.charVal = charVal;       }
	public void setCharDate(Date charDate)   {this.charDate = charDate;     }
	//public void setId(Simple_Id meterId)     {this.meterId = meterId;   }

	@Override
	public String toString() {
		return "Char [charType=" + charType + ", charVal=" + charVal + ", charDate=" + charDate + "]";
	}
	
	public String toStringWithoutVal() {
		return "Char [charType=" + charType + ", charDate=" + charDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charDate == null) ? 0 : charDate.hashCode());
		result = prime * result + ((charType == null) ? 0 : charType.hashCode());
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
		Char other = (Char) obj;
		if (charDate == null) {
			if (other.charDate != null)
				return false;
		} else if (!charDate.equals(other.charDate))
			return false;
		if (charType == null) {
			if (other.charType != null)
				return false;
		} else if (!charType.equals(other.charType))
			return false;
		return true;
	}

	@Override
	public int compareTo(Char o) {
		return this.getCharDate().compareTo(o.getCharDate());
	}

}
