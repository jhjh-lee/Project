package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.AdminDao;
import com.tj.ex.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.adminLoginCheck(aId, aPw);
		if(result==AdminDao.ADMIN_SUCCESS) { 
			HttpSession session = request.getSession();
			AdminDto admin = aDao.getAdmin(aId);
			session.setAttribute("admin", admin);
			request.setAttribute("adminResult", "?���由?�옄 ?�꾩?���쑝濡� �뱾�뼱�삤��?��?���땲�떎.");
		}else { 
			request.setAttribute("errorMsg", "?���由?�옄 ?�꾩?���쑝濡� 濡쒓?���씤�씠 �떎�뙣�릺��?�뒿�땲�떎.");
		}
	}
}
