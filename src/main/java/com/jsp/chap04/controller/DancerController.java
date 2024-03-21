package com.jsp.chap04.controller;

import com.jsp.chap04.service.DeleteService;
import com.jsp.chap04.service.IDancerService;
import com.jsp.chap04.service.RegistService;
import com.jsp.chap04.service.ListService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 == 컨트롤러
// 컨트롤러는 클라이언트의 요청을 파악하고 모델에게 로직을 위임하며
// 응답할 view를 결정합니다.

// @WebServlet("DancerController가 받는 요청[경로]")
// 1. "chap04/dancer/register" 2. "chap04/dancer/list" 3. "chap04/dancer/delete"
// 상기 요청에 DancerController는 모두 받아야 한다. 그래서 다음과 같이 작성해야 한다. "chap04/dancer/*.do"
// -> 확장자 패턴: .do: 앞이 뭐든간에 .do로 끝나는 요청이라면 이 서블릿(DancerController)이 다 받겠다.
// 확장자 패턴을 사용하면 서블릿 하나에서 요청을 여러 개 받을 수 있다.
@WebServlet("*.do")
public class DancerController extends HttpServlet {

    // 인터페이스 타입의 변수를 선언해서, 상황에 맞게 서비스 객체를 갈아 끼울 겁니다.
    private IDancerService sv;
    private RequestDispatcher rd;

    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        try {
            switch(requestURI) {
                case "/register.do":
                    System.out.println("등록 폼으로 이동시켜 달라는 요청이구나!");
                    // response.sendRedirect("/WEB-INF/chap03/dancer/register.jsp");
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/register.jsp");
                    rd.forward(request, response);
                    break;

                case "/regist.do":
                    System.out.println("댄서 등록 요청!");
                    sv = new RegistService();
                    sv.execute(request, response);

                    // 서비스가 댄서 목록을 request에 담아 놓은 상태입니다.
                    // 디스패치에게 목적지를 알려주면서 request와 response객체를 가지고
                    // 이동하라는 명령을 내립니다.
                    // 실제 페이지가 목적지로 이동되면서, request와 response객체도 함께 전달됩니다.
                    // -> jsp에서 request를 꺼내서 목록을 화면에 뿌려서 응답하면 끝!
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/delete.do":
                    System.out.println("삭제 요청이 들어옴!");
                    sv = new DeleteService();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/list.do":
                    System.out.println("댄서 목록화면 보여줘");
                    sv = new ListService();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
