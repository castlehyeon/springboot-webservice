package com.castlehyeon.springboot;

import com.castlehyeon.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행,
                            // 여기서는 SpringRunner라는 실행자를 사용. 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class)//여러 스프링 어노테이션 중, Web(Spring MVC)d에 집중할 수 있는 어노테이션
                                                //선언할 경우 @Controller, @ControllerAdvice사용
                                                //@Service, @Component, @Repository 사용X
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean)을 주입
    private MockMvc mvc; //웹API를 테스트할 때 사용, 스프링 MVC테스트의 시작점
                        //이 클래스를 통해 HTTP GET,POST에 대한 API 테스트
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다.
                .andExpect(status().isOk())// mvc.perform의 결과검증, HTTP Header의 Status 검증
                                        //여기선 OK 즉, 200이 아닌지를 검증.
                .andExpect(content().string(hello));//mvc.perform 결과검증, 응답 본문의 내용을 검증
                                        //Controller에서  "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }
}
