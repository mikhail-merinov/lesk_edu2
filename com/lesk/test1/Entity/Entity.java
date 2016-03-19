package com.lesk.test1.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Класс для хранения списка характеристик
public abstract class Entity {
	private List<Char> charList; // Список характеристик

	// Конструктор с параметром Simple_Id
	public Entity() {
		charList = new ArrayList<Char>();
		// this.id = id;
	}

	// Добавить характеристику в список
	// (запретить создание характеристики какого-либо типа на одну дату)
	public void addChar(Char ch) throws CharException {

		// Проверка: есть ли уже характеристика в списке
		int index = charList.indexOf(ch);
		if (index != -1) {
			throw new CharException("Характеристика " + 
		                            ch.toStringWithoutVal() + 
		                            " уже есть в списке");
		}

		// Все проверки прошли, добавляем характеристику в список
		charList.add(ch);
		System.out.println("Характеристика " + ch + " добавлена в список.");
	}

	// Добавить характеристику в список (развернутая версия)
	public void addChar(String charType, String charVal, Date charDate) {
		Char ch = new Char(charType, charVal, charDate);
		try {
			addChar(ch);
		} catch(CharException e) {
			e.printStackTrace();
		}
	}

	// Возвратить значение характеристики указанного типа на указанную дату
	public Char getChar(String charType, Date charDate) {

		// По аналогии периодических реквизитов 1С
		Char charValue = null;
		Date maxDate = Date.valueOf("2000-01-01"); // Минимальная дата

		// Просматриваем весь список ищем характеристику с максимальной датой,
		// среди дат, меньших charDate
		for (Char ch : charList) {
			if (ch.getCharType().equals(charType)) {
				if (ch.getCharDate().compareTo(charDate) <= 0) {
					if (ch.getCharDate().compareTo(maxDate) > 0) {
						maxDate = ch.getCharDate();
						charValue = ch;
					}
				}
			}
		}
		
		return charValue;
	}

	public List<Char> getSortedCharList(String charType) {
		return getCharList(charType, true);
	}

	// Вернуть отсортированный список характеристик указанного типа
	public List<Char> getCharList(String charType, boolean sort) {
		List<Char> sortedList = new ArrayList<Char>();

		if ((charType == null) || charType.isEmpty()) {
			// Если тип характеристики не указан...
			return sortedList; // Возвращаем пустой список
		} else {
			// Фильтруем характеристику указанного типа
			for (Char iter : charList) {
				if (iter.getCharType().equals(charType)) {
					sortedList.add(iter);
				}
			}
		}
		// Сортируем
		if (sort) Collections.sort(sortedList);
		return sortedList;
	}
	
	public List<Char> getCharList() {

		return charList;
	}

	// Для сортировки списка характеристик
//	public class sortDate implements Comparator<Char> {
//		@Override
//		public int compare(Char o1, Char o2) {
//			return o1.getCharDate().compareTo(o2.getCharDate());
//		}
//	}

	// Удалить характеристику из списка
	public void removeChar(String charType, Date charDate) {

		boolean result = charList.remove(new Char(charType, "", charDate));

		System.out.print("Характеристика " + new Char(charType, "", charDate).toStringWithoutVal() + " ");
		if (result) {
			System.out.println("удалена");
		} else {
			System.out.println("НЕ НАЙДЕНА для удаления");
		}
	}

	// Печать списка характеристик
	public static void printCharList(List<Char> list) {
		int No = 1;
		for (Char ch : list) {
			System.out.println(No++ + ": " + ch);
		}
	}
}
