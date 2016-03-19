package com.lesk.test1.account;
import java.sql.Date;

import com.lesk.test1.Entity.Entity;

// Класс для работы со СЧЕТАМИ
public class Account extends Entity {
	
	private String name;            // Имя
	private Account_Id id;          // Идентификатор
	private Date startDt;           // Начало действия счета
	private String passport;        // Номер паспорта

// === Setters ================================================================
	public void setPassport(String passport) 	{ this.passport = passport; }
	public void setName(String name)			{ System.out.println("Account.setName(String name)"); this.name = name; }
	public void setStartDt(Date dt) 			{ this.startDt = dt; 		}
	public void setId(String id) 				{ System.out.println("Account.setId(String id)"); this.id = new Account_Id(id); }

//=== Getters =================================================================
	public String getPassport() 	{ return passport;  	}
	public String getName()         { return name;          }
	public Date getStartDt()        { return startDt;       }
	public Account_Id getId()       { return id;            }
        
//=== Misc. ===================================================================
	public void activate(Date dt) {
		System.out.println("Account activated on " + dt);
		this.startDt = dt;
	}
	
	public void activate() {
		Date dt = Date.valueOf("2015-01-01");
		System.out.println("Account activated on " + dt);
		this.startDt = dt;
	}
        
//=== constructors ============================================================
	public Account(String name) {
		System.out.println("Account(name=" + name + ")");
		this.name = name;
		this.id = new Account_Id(); 
	}
        
	public Account() {
		System.out.println("Account()");
		this.id = new Account_Id(); 
	}
        
	public Account(String name, String passport) {
		System.out.println("Account(name" + name + ", passport=" + passport + ")");
		this.name = name;
		this.passport = passport;
		this.id = new Account_Id(passport);
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", id=" + id + ", startDt=" + startDt + ", passport=" + passport + "]";
	}
}
