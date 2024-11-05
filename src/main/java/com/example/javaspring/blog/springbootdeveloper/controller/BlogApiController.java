package blog.springbootdeveloper.controller;

import blog.springbootdeveloper.domain.Article;
import blog.springbootdeveloper.dto.AddArticleRequest;
import blog.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // RESTful 웹 서비스를 구현할 때 사용, 모든 메서드에 @ResponseBody를 적용하는 것과 같은 효과이므로 메서드의 반환값이 뷰를 거치지 않고 JSON, XML 등 데이터 형태로 바로 HTTP 응답 본문에 매핑된다.
public class BlogApiController {

    private final BlogService blogService;

     @PostMapping("api/articles") // HTTP 메서드가 POST 일 때 전달받은 URL과 동일하면 메서드로 매핑, 웹 애플리케이션 데이터 생성을 위한 요청으로 주로 사용
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        // ResponseEntity<Article> 타입의 데이터를 요청한 클라이언트에 반환 = Article 객체를 생성해 데이터 베이스에 저장 -> 그 객체를 ResponseEntity에 담아 클라이언트에 반환 -> 클라이언트는 서버에서 생성된 데이터를 받음
        // addArticle은 새로운 Article을 데이터베이스에 저장하는 로직을 정의 -> 그 결과를 HTTP(클라이언트와 서버 간에 데이터를 전송하는데 사용되는 프로토콜(규칙)) 응답으로 반환하는 역할을 한다.
        // @RequestBody AddArticleRequest request는 HTTP 요청의 본문에 있는 JSON(JavaScript Object Notation)(데이터 형식을 저장하고 전송하는 데 사용, 텍스트 기반의 형식) 데이터를 addArticleRequest 타입의 객체로 변환해 request 변수에 담는다.
        Article savedArticle = blogService.save(request); // save 메서드를 호출해서 매개변수에 담긴 데이터를 기반으로 새로운 Article 객체를 생성하고 데이터베이스에 저장 -> 그 결과를 savedArticle 변수에 담는다.

        return ResponseEntity.status((HttpStatus.CREATED)) // ResponseEntity를 생성하여 클라이언트에게 savedArticle 객체를 포함한 응답을 반환
                .body(savedArticle);
        // status(HttpStatus.CREATED) : HTTP 상태 코드 201 Created를 설정 -> 요청에 따른 새로운 리소스(Article)생성 성공을 알림.
        // .body(savedArticle) : 생성된 savedArticle 객체를 HTTP 응답 본문에 포함하여 클라이언트에 전달
    }
}
