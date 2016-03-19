package com.lesk.test1.runner;

import java.sql.Date;
import java.util.List;

import com.lesk.test1.Entity.Char;
import com.lesk.test1.Entity.Entity;
import com.lesk.test1.Source.Source;
import com.lesk.test1.meter.Meter;
import com.lesk.test1.meter.MeterChar;
import com.lesk.test1.meterread.MeterRead;;

// TEST COMMENT
public class Runner {

	static void title(String s) {
		System.out.println();
		System.out.println(s);
		for(int i=0;i<s.length();i++) System.out.print('=');
		System.out.println();
	}
	
	public static void main(String[] args) {
		// Проверка характеристик
		Entity chl = new Entity();
		Char ch;

		title("*** Добавление характеристики два раза ***");
		// Проверка на добавление характеристики два раза
		chl.addChar("Площадь", "54.5", Date.valueOf("2016-02-01"));
		chl.addChar("Площадь", "54.5", Date.valueOf("2016-02-01"));

		title("*** Проверка значений характеристики ***");
		// Проверка на значение характеристики на указанную дату
		chl.addChar("Проживает", "8", Date.valueOf("2016-03-15"));
		chl.addChar("Проживает", "5", Date.valueOf("2016-03-10"));
		chl.addChar("Проживает", "1", Date.valueOf("2016-03-03"));

		ch = chl.getChar("Проживает", Date.valueOf("2016-03-03"));
		System.out.println("Найденное значение: " + ch); // должно быть 1
		ch = chl.getChar("Проживает", Date.valueOf("2016-03-07"));
		System.out.println("Найденное значение: " + ch); // должно быть 1
		ch = chl.getChar("Проживает", Date.valueOf("2016-03-14"));
		System.out.println("Найденное значение: " + ch); // должно быть 5

		title("*** Проверка удаления ***");
		chl.addChar("Комнат", "1", Date.valueOf("2016-01-15"));
		chl.addChar("Комнат", "3", Date.valueOf("2016-01-18"));
		System.out.println("Список ДО удаления:");
		Entity.printCharList(chl.getCharList("Комнат", false));
		chl.removeChar("Комнат", Date.valueOf("2016-01-15"));
		chl.removeChar("Комнат", Date.valueOf("2016-01-19"));
		System.out.println("Список ПОСЛЕ удаления:");
		Entity.printCharList(chl.getCharList("Комнат", false));

		title("*** Проверка сортировки ***");
		System.out.println("Неотсортированный список:");
		Entity.printCharList(chl.getCharList("Проживает", false));
		System.out.println("Отсортированный список:");
		Entity.printCharList(chl.getSortedCharList("Проживает"));
		
//==========================================================================		
		System.out.println("Meter class testing...");

		System.out.println("=== Meter ===");
		Meter meter = new Meter();
		meter.setBadgeNumber("11223344");
		System.out.println("meter: " + meter);
		meter.addChar(MeterChar.METERTYPE, "НЕВА 101", Date.valueOf("2016-01-01"));

		meter.on(Date.valueOf("2016-01-01"));

		meter.addMeterRead(Date.valueOf("2016-04-01"), 1000.5, "Клиент по телефону", 10);
		meter.addMeterRead(Date.valueOf("2016-03-01"), 9223.2, "Личный кабинет", 5);
		meter.addMeterRead(Date.valueOf("2016-02-01"), 1.245, "Показания абонента", 1);

		// Проверка п.8
		Date startDt = Date.valueOf("2016-02-01");
		Date endDt = Date.valueOf("2016-03-01");
		String str = "Список показаний в диапазоне " + startDt + " - " + endDt + ":";
		System.out.println(str);
		for (int index = 0; index < str.length(); index++)
			System.out.print("=");
		System.out.println();

		List<MeterRead> mrl = meter.getMeterReadList(startDt, endDt);
		for (MeterRead mr : mrl) {
			System.out.println(mr);
		}

		meter.printMRs(false);

		Date d = Date.valueOf("2016-04-01");
		MeterRead mr = meter.getMeterReadByDate(d);
		System.out.println("показания на дату " + d + " = " + mr);

		// А теперь пытаемся удалить показания
		meter.removeMeterRead(mr);

		// И показываем, что получилось
		meter.printMRs(false);

		System.out.println("=== Проверка работы MeterRead.equals() ===");
		// Тестирование сравнения двух MeterRead
		MeterRead mr1 = new MeterRead();
		mr1.setMtrDt(Date.valueOf("2016-02-01"));
		mr1.setVal(100);
		mr1.setSource(new Source("Показания абонента", 1));

		MeterRead mr2 = new MeterRead();
		mr2.setMtrDt(Date.valueOf("2016-02-02"));
		mr2.setVal(150);
		mr2.setSource(new Source("Показания абонента", 2));

		System.out.println("mr1 = " + mr1);
		System.out.println("mr2 = " + mr2);

		if (mr1.equals(mr2)) {
			System.out.println("Показания ОДИНАКОВЫЕ");
		} else {
			System.out.println("Показания РАЗНЫЕ");
		}

		System.out.println("=== Проверка добавления показаний ===");
		Meter mt1 = new Meter();
		mt1.on(Date.valueOf("2015-12-31"));
		mt1.addMeterRead(Date.valueOf("2016-01-02"), 1000, "Личный кабинет", 1);
		mt1.addMeterRead(Date.valueOf("2016-01-01"), 2000, "Показания в квитанции", 10);
		mt1.printMRs(false);
		mt1.printMRs(true);

		MeterRead lastMr = mt1.getLastMeterRead();
		System.out.println("Последние показания = " + lastMr);

		// String s = "Account class testing...";
		// System.out.println(s);
		//
		// System.out.println("1.");
		// Account acc = new Account();
		// acc.setName("Иванов Иван Иванович");
		// acc.setId("104599999");
		// System.out.println("id = " + acc.getId().getId() + ", name = " +
		// acc.getName());
		//
		// System.out.println("2.");
		// Account ac3 = new Account("Петров Валерий Васильевич");
		// System.out.println("id = " + ac3.getId().getId() + ", name = " +
		// ac3.getName());
		//
		// System.out.println("3.");
		// Account ac4 = new Account("Петров Валерий Васильевич", "4821
		// 570077");
		// System.out.println("id = " + ac4.getId().getId() +
		// ", name = " + ac4.getName() +
		// ", passport = " + ac4.getPassport());
		//
		// ac4.activate(new Date());
	}
}
