package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {

	public static final String MYSQL_URL = "34.105.231.76:3306";
	public static final String DATABASE_NAME = "ims";
	public static final Scanner SCANNER = new Scanner(System.in);

	private Utils() {

	}

	public static String getInput() {
		return SCANNER.nextLine();
	}

}
