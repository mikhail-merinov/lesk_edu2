package com.lesk.test1.account;

import com.lesk.test1.Id.Simple_Id;

public class Account_Id extends Simple_Id {

	public Account_Id() {
		super();
	}

	public Account_Id(int range) {
		super(range);
	}

	public Account_Id(String id) {
		System.out.println("Account_Id(String id), id = " + id);
		if( id.length() > 5 )
		{
			System.out.println("Id клиента должно быть не длиннее 5 символов");
		}else {
			super.setId(id); // Установить id
		}
	}
        
}
