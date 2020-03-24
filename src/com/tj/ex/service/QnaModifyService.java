package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;
import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.AdminDto;
import com.tj.ex.dto.MemberDto;
import com.tj.ex.service.Service;

public class QnaModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("QNAFileboardUp");
		int maxSize = 1024*1024; // �ִ� ���ε� ������ : 1M
		MultipartRequest mRequest = null;
		String fFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames(); 
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			//�Ķ���Ͱ� �� �޾ƿ��� -> DB�� �ֱ�
			int qNo = Integer.parseInt(mRequest.getParameter("qNo"));
			System.out.println("qNo"+qNo);
			String qTitle = mRequest.getParameter("qTitle");
			String qContent = mRequest.getParameter("qContent");
			//String qFilename = mRequest.getParameter("qFilename");
			QnADao qDao = QnADao.getInstance();
			
			int result = qDao.qnaModify(qTitle, qContent, fFileName , qNo);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("reviewresult", "����۵�� ����");
			}else {
				request.setAttribute("errorMsg", "����۵�� ����");
			}
			
		} catch (Exception e) {
			System.out.println("�������� �������� " +e.getMessage());
			System.out.println("���� ĳġ���巯��");
		}
		// ���ε�� ������ �ҽ������� ����
					File serverFile = new File(path+"/"+fFileName);
						InputStream is = null;
						OutputStream os = null;
						try {
							is = new FileInputStream(serverFile);
							os = new FileOutputStream("D:\\mega_IT\\source\\7_JQuery\\shoping1\\WebContent\\QNAFileboardUp\\"+fFileName);
							byte[] bs = new byte[(int)serverFile.length()];  
							while(true) {
								int readbyteCnt = is.read(bs);
								if(readbyteCnt == -1) break;
								os.write(bs, 0, readbyteCnt);
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							System.out.println("����������?2");
						}finally {
							try {
								if(os!=null) os.close();
								if(is!=null) is.close();
							} catch (Exception e) {}
						}//try-catch-finally
						
					}//execute()
}
