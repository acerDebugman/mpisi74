package com.joe.common;

public class CustomerContextHolder {
	private static final ThreadLocal<String> customer = new ThreadLocal<String>();// 线程本地环境
	
	// 设置数据源类型
	public static void setCustomerType(String customerType){  
	    customer.set(customerType);  
	}
	// 获取数据源类型
	public static String getCustomerType(){  
	    return customer.get();  
	}
	// 清除数据源类型
	public static void remove(){  
	    customer.remove();  
	}
}
