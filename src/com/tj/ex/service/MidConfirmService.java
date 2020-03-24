package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.mIdConfirm(mId);
		if(result==mDao.NONEXISTENT) {
			request.setAttribute("idConfirmResult", "�� ��밡����? ID�Դϴ�.");
		}else{
			request.setAttribute("idConfirmResult", "�� �ߺ��� ID");
		}
			

	}

}
