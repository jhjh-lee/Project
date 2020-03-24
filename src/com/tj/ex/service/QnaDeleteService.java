package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dao.QnADao;

public class QnaDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QnADao qDao = QnADao.getInstance();
		int result = qDao.qnaDelete(qNo);

	}

}
