package com.tj.ex.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tj.ex.service.ALoginService;
import com.tj.ex.service.CartDeleteService;
import com.tj.ex.service.CartInsertService;
import com.tj.ex.service.CartListService;
import com.tj.ex.service.CartModifyService;
import com.tj.ex.service.MJoinService;
import com.tj.ex.service.MLoginService;
import com.tj.ex.service.MLogoutService;
import com.tj.ex.service.MemberModifyService;
import com.tj.ex.service.MidConfirmService;
import com.tj.ex.service.NoticeContentService;
import com.tj.ex.service.NoticeDeleteService;
import com.tj.ex.service.NoticeListService;
import com.tj.ex.service.NoticeModifyService;
import com.tj.ex.service.NoticeModifyViewService;
import com.tj.ex.service.NoticeWriteService;
import com.tj.ex.service.OrderContentviewService;
import com.tj.ex.service.OrderListViewService;
import com.tj.ex.service.OrderService;
import com.tj.ex.service.OrderViewService;
import com.tj.ex.service.ProducListService;
import com.tj.ex.service.ProducModifyService;
import com.tj.ex.service.ProducModifyviewService;
import com.tj.ex.service.ProducTypeListService;
import com.tj.ex.service.ProducWriteService;
import com.tj.ex.service.ProductContentViewService;
import com.tj.ex.service.ProductDeleteService;
import com.tj.ex.service.QnaContentviewService;
import com.tj.ex.service.QnaDeleteService;
import com.tj.ex.service.QnaListService;
import com.tj.ex.service.QnaModifyService;
import com.tj.ex.service.QnaModifyviewService;
import com.tj.ex.service.QnaWriteService;
import com.tj.ex.service.QnaWriteviewService;
import com.tj.ex.service.Service;
import com.tj.ex.service.memberAllViewService;

@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;
		if (command.equals("/mainView.do")) { 
			viewPage = "main/main.jsp";
		} else if (command.equals("/loginView.do")) { 
			viewPage = "member/login.jsp";
		} else if (command.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp"; // 
		} else if (command.equals("/joinView.do")) { 
			viewPage = "member/join.jsp";
		} else if (command.equals("/idConfirm.do")) { 
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if (command.equals("/join.do")) { 
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		} else if (command.equals("/logout.do")) { 
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "mainView.do";
		} else if (command.equals("/memberModifyView.do")) { 
			viewPage = "member/memberModify.jsp";
		} else if (command.equals("/memberModify.do")) { 
			service = new MemberModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (command.equals("/memberAllView.do")) { 
			service = new memberAllViewService();
			service.execute(request, response);
			viewPage = "member/memberAllView.jsp"; 
		}else if (command.equals("/adminLogin.do")) { //**************************admin
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";				
		}else if(command.equals("/adminloginView.do")) { 
			viewPage = "admin/adminLogin.jsp";  
		}//**************************admin
		
		
		
		if(command.equals("/producList.do")) { //**************************product
			 service = new ProducListService(); 
			 service.execute(request, response); 
			 viewPage ="product/producList.jsp"; 
		}else if (command.equals("/producTypeList.do")) {
			service = new ProducTypeListService();
			service.execute(request, response);
			viewPage = "product/productTypeList.jsp";
		}else if (command.equals("/productContentView.do")) { 
			service = new ProductContentViewService();
			service.execute(request, response);
			viewPage = "product/productContentview.jsp";
		}else if (command.equals("/producWriteview.do")) { 
			viewPage = "product/producWriteview.jsp";
		}else if (command.equals("/productWrite.do")) { 
			service = new ProducWriteService();
			service.execute(request, response);
			viewPage = "producList.do";
		}else if (command.equals("/producModifyview.do")) { 
			service = new ProducModifyviewService();
			service.execute(request, response);
			viewPage = "product/productModifyview.jsp";
		}else if (command.equals("/productModify.do")) {
			service = new ProducModifyService();
			service.execute(request, response);
			viewPage = "producList.do";
		}else if (command.equals("/productDelete.do")) {
			service = new ProductDeleteService();
			service.execute(request, response);
			viewPage = "producList.do";			 //**************************product
		}
		
		
		
		else if(command.equals("/cartInsert.do")) { 
			 service = new CartInsertService(); 
			 service.execute(request, response); 
			 viewPage ="cartList.do"; 
		}else if (command.equals("/cartList.do")) { 
			service = new CartListService();
			service.execute(request, response);
			viewPage = "cart/cartlist.jsp";
		}else if (command.equals("/cartDelete.do")) { 
			service = new CartDeleteService();
			service.execute(request, response);
			viewPage = "cartList.do";
		}else if (command.equals("/cartModify.do")) { 
			service = new CartModifyService();
			service.execute(request, response);
			viewPage = "cartList.do";
		}
		
		else if(command.equals("/orderView.do")) { 
			service = new OrderViewService();
			service.execute(request, response);
			viewPage = "order/orderView.jsp";
		}else if(command.equals("/order.do")) { 
			service = new OrderService();
			service.execute(request, response);
			viewPage = "mainView.do";
		}else if(command.equals("/orderListView.do")) { 
			service = new OrderListViewService();
			service.execute(request, response);
			viewPage = "order/orderListview.jsp";
		}else if(command.equals("/orderContentview.do")) { 
			service = new OrderContentviewService();
			service.execute(request, response);
			viewPage = "order/orderContentview.jsp";
		}
		
		
		else if(command.equals("/QnaList.do")) {
			service = new QnaListService();
			service.execute(request, response);
			viewPage = "QNA/QnaList.jsp";
		}else if(command.equals("/QnaContentview.do")) { 
			service = new QnaContentviewService();
			service.execute(request, response);
			viewPage = "QNA/QnaContentview.jsp";
		}else if(command.equals("/QnaWriteview.do")) { 
			viewPage = "QNA/QnaWriteview.jsp";
		}else if(command.equals("/QnaWrite.do")) { 
			service = new QnaWriteService();
			service.execute(request, response);
			viewPage = "QnaList.do";
		}else if(command.equals("/QnaModifyview.do")) {
			service = new QnaModifyviewService();
			service.execute(request, response);
			viewPage = "QNA/QnaModifyview.jsp";
		}else if(command.equals("/QnaModify.do")) { 
			service = new QnaModifyService();
			service.execute(request, response);
			viewPage = "QnaList.do";
		}else if(command.equals("/QnaDelete.do")) { 
			service = new QnaDeleteService();
			service.execute(request, response);
			viewPage = "QnaList.do";
		}else if(command.equals("/About.do")) {
			viewPage = "member/About.jsp";
		}else if(command.equals("/noticeList.do")) { // 공지사항 페이지 리스트
			service = new NoticeListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		} else if (command.equals("/noticeContent_view.do")) { // 공지사항 상세보기
			service = new NoticeContentService();
			service.execute(request, response);
			viewPage = "notice/noticeContentview.jsp";
		} else if (command.equals("/noticeWrite_view.do")) { // 공지사항 글 쓰기 페이지
			viewPage = "notice/noticeWriteview.jsp"; // noticewrite.do
		} else if (command.equals("/noticewrite.do")) { // 공지사항 글 쓰기
			service = new NoticeWriteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if (command.equals("/noticeModifyview.do")) { // 공지사항 글 수정 페이지
			service = new NoticeModifyViewService();
			service.execute(request, response);
			viewPage = "notice/noticeModifyview.jsp";
		} else if (command.equals("/noticeModify.do")) { // 공지사항 글 수정
			service = new NoticeModifyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if (command.equals("/noticeDelete.do")) { // 공지사항 글 삭제
			service = new NoticeDeleteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
			// noticeDelete.do
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
