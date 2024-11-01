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
        // addArticle은 새로운 Article을 데이터베이스에 저장하는 로직을 정의 ->
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status((HttpStatus.CREATED))
                .body(savedArticle);
    }
}
