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
			request.setAttribute("adminResult", "?„¿ï¿½ç”±?Šì˜„ ?¨ê¾©? ™ï¿½ì‘æ¿¡ï¿½ ï¿½ë±¾ï¿½ë¼±ï¿½ì‚¤ï¿½ë?²ï¿½?’¿ï¿½ë•²ï¿½ë–.");
		}else { 
			request.setAttribute("errorMsg", "?„¿ï¿½ç”±?Šì˜„ ?¨ê¾©? ™ï¿½ì‘æ¿¡ï¿½ æ¿¡ì’“? ‡ï¿½ì”¤ï¿½ì”  ï¿½ë–ï¿½ë™£ï¿½ë¦ºï¿½ë?ï¿½ë’¿ï¿½ë•²ï¿½ë–.");
		}
	}
}
