package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProducTypeListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String pageNum = request.getParameter("pageNum");
		//String requestPageNum = (String)request.getAttribute("pageNum");
		if(pageNum==null) {
			//if(requestPageNum==null)
				pageNum = "1";
			//else
			//	pageNum = requestPageNum;
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=9, BLOCKSIZE=3;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		String pType = request.getParameter("pType");
		String pTypesTr = (String)request.getParameter("pType");
		if(pType==null) {
			if(pTypesTr==null) {
				pType = "all";
			}
			else {
				pType=pTypesTr;
			}
		}
		
		ProductDao pDao = ProductDao.getInstance();
		
		ArrayList<ProductDto> productTypeList = pDao.productTypelistFileBoard(pType, startRow, endRow);
		System.out.println();
		request.setAttribute("productTypeList", productTypeList);
		int totCnt = pDao.productTypeTotCnt(pType);// �?�??��
		
		
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//?��?���?�??��
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt?�� ?��?���? list.size()???��
		request.setAttribute("pageNum", currentPage);// pageNum ?��?���? param.pageNum
	}
}
