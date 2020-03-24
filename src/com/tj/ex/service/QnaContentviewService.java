package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.QnADto;

public class QnaContentviewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QnADao qDao = QnADao.getInstance();
		QnADto qDto = qDao.qNaContentView(qNo);
		request.setAttribute("QnaContentview", qDto);

	}
}
