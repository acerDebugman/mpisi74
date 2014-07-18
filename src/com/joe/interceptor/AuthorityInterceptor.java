package com.joe.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限验证拦截器
 * @author Tim
 */
public class AuthorityInterceptor extends AbstractInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2301478600235967985L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		return null;
	}
}
