package com.jsp.chap01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

// 역할: http 요청 응답 처리에서 필요한 공통적인 부분을 쉽게 해결해 주는 자바 API를 이용한 클래스
@WebServlet("/hello") // 우리 웹 서버에 /hello라는 URL로 요청이 오면 이 서블릿을 실행 시켜라

public class HelloServlet extends HttpServlet { // HttpServlet을 상속 받기

    // 기본생성자
    public HelloServlet() {
        // 요청이 들어오면 서블릿 객체가 서버 내에서 자동 생성된다.
        // 약속된 경로(/hello)로 요청이 들어오면 생성자가 호출되고,
        // 객체가 생성된 후에 콘솔창에 하기 명령문이 출력된다.
        System.out.println("\n\n\n  Hello 서블릿 작동 시작! \n\n\n");
    }

    // 요청 정보 얻어오기

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청이 들어오면 객체 생성이 되고, 서비스 메서드가 자동으로 실행시키는 메서드다.
        // 매개 값으로 요청에 대한 정보가 담긴 HttpServletRequest와
        // 응답에 대한 정보 및 기능이 담긴 HttpServletResponse를 전달 받습니다.

        // 클라이언트 요청 방식을 얻을 수 있다.
        String method = request.getMethod();

        // 사용자가 요청한 URL 얻기
        String requestURI = request.getRequestURI();

        // 요청 헤더 읽기
        String header = request.getHeader("Cache-Control");
        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);
        System.out.println("header = "+ header);


        // 요청과 함께 넘어온 데이터 (쿼리 파라미터) 읽기
        // index.jsp 화면에서 이름과 나이를 입력하고, 제출을 누르면
        // <form action="http://localhost:8181/hello"> action에 지정한 경로로 해당 이름과 나이가 전달된다.
        // 요청에 대한 모든 값을 갖고 있는 HttpServletRequest 객체를 참조하는
        // 참조변수 request를 이용해 파라미터(input의 속성 name)를 얻고,
        // 그 값을 변수 nick에 담고 출력한다.
        String nick = request.getParameter("nick"); // 얻고 싶은
        String age = request.getParameter("age");

        System.out.println("nick = " + nick);
        System.out.println("age = " + age);

        // 응답 메세지에 HTML 문서 생성해서 응답하기
        // nick 님은 xxxx년생 입니다.

        // 출생 년도를 구하는 비즈니스 로직
        int year = LocalDateTime.now().getYear();
        int birthYear = year - Integer.parseInt(age);

        // 서블릿 클래스에서 HTML 파일을 생성하는 방법
        response.setContentType("text/html"); // 응답객체에게 클라이언트에게 응답할건데 해당 문서의 타입으로 텍스트로 이루어진 html이야 라고 말해주기
        response.setCharacterEncoding("UTF-8"); // text/html 문서의 문자 형식?은 UTF-8로 셋팅해줘

        // HTML 문서를 작성할 writer 객체를 생성
        PrintWriter w = response.getWriter();

        w.write("<!DOCTYPE html>\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("</head>\n");

        w.write("<body>\n");
        w.write("<h1>\n");
        w.write(String.format("%s님은 %d년생입니다.\n", nick, birthYear));
        w.write("</h1>\n");
        w.write("</body>\n");

        w.write("</html>\n");

        w.flush(); // 브라우저로 지금까지 작성한 내용들을 내보낸 후 메모리 비우기.
        w.close();
    }
}
