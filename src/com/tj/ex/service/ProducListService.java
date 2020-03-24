package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.scenario.effect.impl.prism.PrDrawable;
import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProducListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
				pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=9, BLOCKSIZE=3;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		ProductDao pDao = ProductDao.getInstance();
		ArrayList<ProductDto> productList = pDao.productlistFileBoard(startRow, endRow);
		System.out.println();
		request.setAttribute("productList", productList);
		int totCnt = pDao.productAllTotCnt();// ê¸?ê°??ˆ˜
		
		
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//?Ž˜?´ì§?ê°??ˆ˜
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt?Š” ?—†?œ¼ë©? list.size()???š©
		request.setAttribute("pageNum", currentPage);// pageNum ?—†?œ¼ë©? param.pageNum
	}
}
