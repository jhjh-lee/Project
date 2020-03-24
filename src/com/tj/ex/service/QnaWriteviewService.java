package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.QnADto;

public class QnaWriteviewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			int qNo = Integer.parseInt(request.getParameter("qNo"));
			QnADao qnADao = QnADao.getInstance();
			QnADto dto = qnADao.qNaContentView(qNo);
			request.setAttribute("modify_view", dto);
		}
	}
