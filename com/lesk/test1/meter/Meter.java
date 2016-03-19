package com.lesk.test1.meter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lesk.test1.Entity.Entity;
import com.lesk.test1.Source.Source;
import com.lesk.test1.meterread.MeterRead;

// Класс для работы со СЧЁТЧИКАМИ
public class Meter extends Entity {

	// === properties ===
	private String badgeNumber; // Шильдик
	private Meter_Id id; // Id счётчика
	private Date receiveDt; // Дата установки
	private Date retireDt; // Дата снятия
	private int digitsLeft; // Цифр слева от запятой
	private int digitsRight; // Цифр справа от запятой
	private double regConst; // Коэффициент трансформации
	private List<MeterRead> mrList; // Список показаний

	// === getter ===
	public String getBadgeNumber() {return badgeNumber;	}
	public Meter_Id getId() {		return id;			}
	public Date getReceiveDt() {	return receiveDt;	}
	public Date getRetireDt() {		return retireDt;	}
	public int getDigitsLeft() {	return digitsLeft;	}
	public int getDigitsRight() {	return digitsRight;	}
	public double getRegConst() {	return regConst;	}

	// === setter ===
	public void setBadgeNumber(String badgeNumber) 	{	this.badgeNumber = badgeNumber;	}
	public void setId(Meter_Id id) 					{	this.id = id;					}
	public void setReceiveDt(Date receiveDt) 		{	this.receiveDt = receiveDt;		}
	public void setRetireDt(Date retireDt) 			{	this.retireDt = retireDt;		}
	public void setDigitsLeft(int digitsLeft) 		{	this.digitsLeft = digitsLeft;	}
	public void setDigitsRight(int digitsRight) 	{	this.digitsRight = digitsRight;	}
	public void setRegConst(double regConst) 		{	this.regConst = regConst;		}

	// === inits ===
	private void initDefault() {
		badgeNumber = "";
		id = new Meter_Id();
		receiveDt = null;
		retireDt = null;
		digitsLeft = 5;
		digitsRight = 1;
		regConst = 1.0;
	}

	// === constructors ===
	public Meter() {
		super();
		initDefault();
	}

	public Meter(String badgeNumber) {
		super();
		initDefault();
		this.badgeNumber = badgeNumber;
	}

	// === methods ===
	public void on(Date d) {
		if (receiveDt == null) {
			// Задаём дату установки
			setReceiveDt(d);
			mrList = new ArrayList<MeterRead>();
		}
	}

	public void off(Date d) {
		if ((receiveDt != null) && // Дата установки ДОЛЖНА быть указана
				(retireDt == null) && // Дата снятия НЕ ДОЛЖНА быть указана
				(d.compareTo(receiveDt) > 0)) // Дата снятия ДОЛЖНА быть больше
												// даты установки
		{
			// Задаём дату снятия
			setRetireDt(d);
		}
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", badgeNumber=" + this.badgeNumber + "]";
	}

	public void addMeterRead(MeterRead mr) {
		if (mrList != null) {
			if (mrList.contains(mr)) {
				System.out.println("Показания УЖЕ ИМЕЮТСЯ: " + mr);
			} else {
				mrList.add(mr);
				System.out.println("Показания ДОБАВЛЕНЫ");
			}
		} else {
			System.out.println("Счётчик не подключен!");
		}
	}

	public void addMeterRead(Date dt, double val, String source, int priority) {
		MeterRead mr = new MeterRead();
		mr.setMtrDt(dt);
		mr.setVal(val);
		mr.setSource(new Source(source, priority));
		addMeterRead(mr);
	}

	/**
	 * Вернуть список показаний, отсортированный или нет по дате показаний
	 * @param sort <code>true</code>, сортировать список по дате показаний;
	 * 			   <code>false</code>, не сортировать список
	 */
	public void printMRs(boolean sort) {
		List<MeterRead> printList = new ArrayList<MeterRead>();
		printList.addAll(mrList);
		
		if (mrList == null) {
			System.out.println("Показаний нет, счётчик не подключен!");
		} else {
			if(sort) {
				Collections.sort(printList);
				System.out.println("Список, отсортированных по дате, показаний абонента:");
			} else {
				System.out.println("Список показаний абонента:");
			}
			System.out.println("--------------------------");
			for (MeterRead mr : printList) {
				System.out.println(mr);
			}
			System.out.println("--------------------------");
		}
	}

//	public void printSortMRs() {
//		if (mrList == null) {
//			System.out.println("Показаний нет, счётчик не подключен!");
//		} else {
//			Collections.sort(mrList, new CustomComparator());
//
//			System.out.println("Список, отсортированных по дате, показаний абонента:");
//			System.out.println("--------------------------");
//			for (MeterRead mr : mrList) {
//				System.out.println(mr);
//			}
//			System.out.println("--------------------------");
//		}
//	}

	// Класс для сортировки списка показаний
//	public class CustomComparator implements Comparator<MeterRead> {
//		@Override
//		public int compare(MeterRead arg0, MeterRead arg1) {
//			return arg0.getMtrDt().compareTo(arg1.getMtrDt());
//		}
//	}

	public MeterRead getLastMeterRead() {
		return Collections.max(mrList);
	}

	// -----------------------------------------------------------------------------

	public MeterRead getMeterReadByDate(Date d) {
		for (MeterRead mr : mrList) {
			if (d.equals(mr.getMtrDt())) {
				return mr;
			}
		}
		return null;
	}

	public List<MeterRead> getAllMeterReads() {
		return this.mrList;
	}

	public void removeMeterRead(MeterRead mr) {
		mrList.remove(mr);
		// for(MeterRead mrIter: mrList) {
		// if( mrIter.equals(mr)) {
		// mrList.remove(mrIter);
		// break;
		// }
		// }
	}

	public class ComparatorPriority implements Comparator<MeterRead> {
		@Override
		public int compare(MeterRead o1, MeterRead o2) {
			return o1.getSource().getPriority() - o2.getSource().getPriority();
		}
	}

	/**
	 * Возвращает список MeterRead, отсортированных по значению приоритета, в
	 * указанном диапазоне дат
	 * 
	 * @param startDt
	 *            - Начальная дата
	 * @param endDt
	 *            - Конечная дата
	 * @return Список <code>List<MeterRead></code>
	 */
	public List<MeterRead> getMeterReadList(Date startDt, Date endDt) {
		ArrayList<MeterRead> list = new ArrayList<MeterRead>();

		// Формируем новый список в указанном диапазоне дат
		for (MeterRead mr : mrList) {
			Date dt = mr.getMtrDt();
			if ((dt.compareTo(startDt) >= 0) && (dt.compareTo(endDt) <= 0)) {
				list.add(mr);
			}
		}

		// Сортируем новый список
		// Collections.sort(list, new ComparatorPriority());

		list.sort(new ComparatorPriority());

		return list;
	}

}
