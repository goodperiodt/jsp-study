package com.jsp.chap04.service;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegistService implements IDancerService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
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

        // 가져온 입력값을 토대로 댄서 객체 생성 -> DancerRepository 객체를 참조해 save() 메서드 호출
        DancerRepository.save(name, crewName, danceLevel, genresArray);

        // 등록이 완료되었으므로 지금까지 등록된 댄서의 목록을 화면에 보여주고 싶다.
        // MVC구조에서 화면응답은 View가 전담하기 때문에 서비스는 repository로부터 목록을 받아와서 View단으로 데이터를 넘기겠다. (requuest 담아서 전달하자)

        // 댄서 목록 받아오기
        List<Dancer> dancerList = DancerRepository.findAll();

        // request에 담기
        // setAtrribute("이름", 저장할 값) -> 이름을 통해 값을 얻을 수 있습니다.
        request.setAttribute("dl", dancerList);

        // 서비스의 역할을 여기까지 입니다.
        // 화면단 결정은 Controller의 몫입니다.



    }
}
