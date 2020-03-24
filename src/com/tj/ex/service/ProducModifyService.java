package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;

public class ProducModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("productFileUp");
		int maxSize = 1024*1024; // 理쒕�? �뾽濡쒕�? �궗�씠利� : 1M
		String pFilename = "";
		MultipartRequest mRequest = null;
		try {
			// mRequest 媛앹�? �깮�꽦�븳 �썑 �뙆�씪 �씠?���? 諛쏆븘�?��湲�
			
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames(); 
				String param = params.nextElement(); // mPhoto
			pFilename = mRequest.getFilesystemName(param);		
			String pCode = mRequest.getParameter("pCode");
			String pName = mRequest.getParameter("pName");
			String pType = "BEER";//mRequest.getParameter("pType");
			int pPrice = Integer.parseInt(mRequest.getParameter("pPrice"));
			int pStock = Integer.parseInt(mRequest.getParameter("pStock"));
			ProductDao pDao = ProductDao.getInstance();
			int result = pDao.productUpdate(pName, pType, pFilename,pPrice, pStock, pCode);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("productInsert", "�긽��?��?���젙 �꽦?��");
			}else {
				request.setAttribute("erroMsg", "�긽��?��?���젙 �떎�뙣");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// �뾽濡쒕뱶��? �뙆�씪�쓣 �냼�뒪�뤃�뜑濡� 蹂듭�?
				File serverFile = new File(path+"/"+pFilename);
				if(serverFile.exists()) {
					InputStream is = null;
					OutputStream os = null;
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("D:\\mega_IT\\source\\7_JQuery\\shoping1\\WebContent\\QNAFileboardUp\\"+pFilename);
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
	
				}//execute()

	}

}
