package com.lesk.test1.meterread;

import java.sql.Date;

import com.lesk.test1.Entity.Entity;
import com.lesk.test1.Source.Source;

// Класс для работы с ПОКАЗАНИЯМИ
public class MeterRead extends Entity {

	private MeterRead_Id id;   // Id показаний
	private Date mtrDt;        // Дата показаний
	private Source source;     // Источник показаний + приоритет
	private double val;        // Значение показаний
	
	//public CharList chList; // Характеристики
	
	public MeterRead() {
		this.id = new MeterRead_Id();
		//chList = new CharList(id);
	}
        
	public MeterRead(double val) {
		this.id = new MeterRead_Id();
		this.val = val;
		//chList = new CharList(id);
	}
        
	@Override
	public String toString() {
		return "MeterRead [id=" + id + ", mtrDt=" + mtrDt + ", source=" + source + ", val=" + val + "]";
	}

	// getters
	public MeterRead_Id getId() {return id;}
	public Date getMtrDt() {return mtrDt;}
	public Source getSource() {return source;}
	public double getVal() {return val;}

    // setters
	public void setId(MeterRead_Id id) {this.id = id;}
	public void setMtrDt(Date mtrDt) {this.mtrDt = mtrDt;}
	public void setSource(Source source) {this.source = source;}
	public void setVal(double val) {this.val = val;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mtrDt == null) ? 0 : mtrDt.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		MeterRead other = (MeterRead) obj;
		if (mtrDt == null) {
			if (other.mtrDt != null)
				return false;
		} else if (!mtrDt.equals(other.mtrDt))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
}
