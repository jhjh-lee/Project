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

public class QnaReplyWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("qnaFileUp");
		int maxSize = 1024*1024; // 理쒕� �뾽濡쒕뱶 �궗�씠利� : 1M
		MultipartRequest mRequest = null;
		String fFileName = "";
		try {
			// mRequest 媛앹껜 �깮�꽦�븳 �썑 �뙆�씪 �씠由� 諛쏆븘�삤湲�
			
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			//�뙆�씪誘명꽣媛� �떎 諛쏆븘�삤湲� -> DB�뿉 �꽔湲�
			HttpSession session = request.getSession();
			String mId = null;
			String aId = null;
			if(session.getAttribute("member")!=null) {
				mId = ((MemberDto)session.getAttribute("member")).getmId();
			}else if(session.getAttribute("admin")!=null) {
				aId = ((AdminDto)session.getAttribute("admin")).getaId();
			}
			String qTitle = mRequest.getParameter("qTitle");
			String qContent = mRequest.getParameter("qContent");
			String qFilename = mRequest.getParameter("qFilename");
			int qGroup = Integer.parseInt(mRequest.getParameter("qGroup"));
			int qStep = Integer.parseInt(mRequest.getParameter("qStep"));  
			int qIndent = Integer.parseInt(mRequest.getParameter("qIndent"));   
			
			QnADao qDao = QnADao.getInstance();
			int result = qDao.qnaReply(aId, mId, qTitle, qContent, qFilename, qGroup, qStep, qIndent);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("reviewresult", "由щ럭湲��벑濡� �꽦怨�");
			}else {
				request.setAttribute("errorMsg", "由щ럭湲��벑濡� �떎�뙣");
			}
			
		} catch (Exception e) {
			System.out.println("�삁�쇅 硫붿꽭吏� : " +e.getMessage());
		}
		// �뾽濡쒕뱶�맂 �뙆�씪�쓣 �냼�뒪�뤃�뜑濡� 蹂듭궗
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
						}finally {
							try {
								if(os!=null) os.close();
								if(is!=null) is.close();
							} catch (Exception e) {}
						}//try-catch-finally
						
					}//execute(
}
