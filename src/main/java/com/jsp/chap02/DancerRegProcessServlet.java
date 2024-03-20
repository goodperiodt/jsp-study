package com.jsp.chap02;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;


// 해당 경로의 요청이 들어오면,
// DancerRegProcessServlet 서블릿이 작동한다.
@WebServlet("/chap02/dancer/reg-process")
public class DancerRegProcessServlet extends HttpServlet {

    // 사용자의 입력값 가져오기
    // 가져온 입력값을 토대로 Dancer 객체 생성 -> repository의 save 메서드가 대신 Dancer객체를 생성함.
    // 정보를 데이터베이스에 저장 -> repository의 save()가 대신 해줄거임

    //Get방식이든 Post방식이든 둘다 처리하는 service()
    
    //Post방식만을 처리하는 doPost()
    // POST 요청이 전달되면 자동으로 호출되는 메서드
    // POST 요청에만 호출됩니다.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post방식에서 전달된 한글 데이터의 깨짐 방지를 위한 코드
        // post라는 방식의 한계, post는 요청 헤더에 숨겨져서 전달되고,
        // 숨겨져서 전달될 때 사용자가 입력한 한글을 디코딩하기 때문에
        // 값을 받아서 출력하기 전에 다시 디코딩된 한글을 인코딩해야 한다.
        // ** POST전송방식은 한글이 깨지기 때문에 한글 깨짐방지를 위한 코드를 반드시 써 주어야 한다.
        request.setCharacterEncoding("UTF-8");


        // 제공된 form에 작성된 사용자의 입력값을 가져온다.
        // 사용자가 입력한 이름을 가져온다.
        String name = request.getParameter("name");
        String crewName = request.getParameter("crewName");
        String danceLevel = request.getParameter("danceLevel");
        // checkbox같이 여러 값을 전달받는 경우에는 getParameterValues를 사용합니다.
        // -> String  배열로 리턴합니다.
        String[] genresArray = request.getParameterValues("genres");

        /*
        System.out.println("name = " + name);
        System.out.println("crewName = " + crewName);
        System.out.println("danceLevel = " + danceLevel);
        System.out.println("Arrays.toString(genresArray) = " + Arrays.toString(genresArray));
        */

        // 얻어온 입력 값을 save()에게 전달 -> 객체 생성 및 Map에 저장까지 일괄 처리
        DancerRepository.save(name, crewName, danceLevel, genresArray);

        // 화면으로 결과를 응답해주기

        // 결과 출력
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter w = response.getWriter();

        w.write("<!DOCTYPE html>\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("</head>\n");
        w.write("<body>\n");

        w.write("<ul>");

        for (Dancer dancer : DancerRepository.findAll()) {
            w.write(String.format("<li># 이름 : %s, 크루명: %s, 수준: %s, 공연당페이: %d원, 장르: %s</li>\n"
                    , dancer.getName(), dancer.getCrewName(),
                    dancer.getDanceLevel().getLevelDescription(),
                    dancer.getDanceLevel().getPayPerEvent(),
                    dancer.getGenres()));
        }

        w.write("</ul>");

        w.write("<a href=\"/chap02/dancer/register\">새로운 등록하기</a>");

        w.write("</body>\n");
        w.write("</html>");

        w.flush();
        w.close();


    }

    //Get방식의 요청에만 반응하는 doGet()
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    */
    
}
