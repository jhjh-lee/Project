package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.CartDao;
import com.tj.ex.dao.ProductDao;

public class CartModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cNo = Integer.parseInt( request.getParameter("num"));
		int cNt = Integer.parseInt( request.getParameter("cNt"));
		System.err.println(cNt);
		System.err.println(cNo);
		CartDao cDao = CartDao.getInstance();
		int result = cDao.cartModify(cNt, cNo);
		if(result == cDao.SUCCESS) {
			request.setAttribute("cartUpdate", "μΉ΄νΈ ?? ?±κ³? ");
		}else {
			request.setAttribute("erroMsg", "μΉ΄νΈ ??  ?€?¨");
		}
	}

}
