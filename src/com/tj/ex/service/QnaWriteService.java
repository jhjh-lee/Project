package com.tj.ex.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.AdminDto;
import com.tj.ex.dto.MemberDto;
public class QnaWriteService implements Service {
	@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) {
			// ?��?��첨�? 로직 + ?��?��미터?�� 받아 DB?�� join
			String path = request.getRealPath("QNAFileboardUp");
			int maxSize = 1024*1024*10; // 최�??��로드 ?��?��즈는 10M
			MultipartRequest mRequest = null;
			String fFileName = "";
			try {
				mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
				Enumeration<String> params = mRequest.getFileNames(); 
				String param = params.nextElement();
				fFileName = mRequest.getFilesystemName(param);
				// mId, fTitle, fContent,  fileName, fIp
				HttpSession httpSession = request.getSession();
				String mId = null;
				String aId = null;
				if(httpSession.getAttribute("member")!=null) {
					mId = ((MemberDto)httpSession.getAttribute("member")).getmId();
				}else if(httpSession.getAttribute("admin")!=null) {
					aId = ((AdminDto)httpSession.getAttribute("admin")).getaId();
				}
				String qTitle = mRequest.getParameter("qTitle");
				String qContent = mRequest.getParameter("qContent");
				//String qFilename = mRequest.getParameter("qFilename");
				QnADao qnADao = QnADao.getInstance();
				int result = qnADao.qNaWrite(aId, mId, qTitle, qContent, fFileName);
				if(result == QnADao.SUCCESS) { 
					request.setAttribute("resultMsg", "�??���? ?���?");
				}else {
					request.setAttribute("resultMsg", "�??���? ?��?��");
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				request.setAttribute("resultMsg", "�??���? ?��?��");
			}
			// ?��버에 ?��?���? fileboardUp ?��?��?�� ?��?��?��?��?�� filecopy
			File serverFile = new File(path+"/"+fFileName);
			if(serverFile.exists()) {
				InputStream  is = null;
				OutputStream os = null;
				try {
					
					is = new FileInputStream(serverFile);
					os = new FileOutputStream("D:\\mega_IT\\source\\7_JQuery\\shoping1\\WebContent\\QNAFileboardUp\\"+fFileName);
					byte[] bs = new byte[(int)serverFile.length()];
					while(true) {
						int nByteCnt = is.read(bs);
						if(nByteCnt==-1) break;
						os.write(bs, 0, nByteCnt);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if(os!=null) os.close();
						if(is!=null) is.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

	}

}
