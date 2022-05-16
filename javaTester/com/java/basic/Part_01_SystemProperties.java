package com.java.basic;

//class test de thu cac ham
public class Part_01_SystemProperties {
	public static void main(String[] args) {
		String a = "$1,400.00";
		int t = Integer.parseInt(a.trim().replace("$", "").replace(".00", "").replace(",", ""));
		System.out.println(t);
	}
	

}
