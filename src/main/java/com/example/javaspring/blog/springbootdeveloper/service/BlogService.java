package blog.springbootdeveloper.service;

import blog.springbootdeveloper.domain.Article;
import blog.springbootdeveloper.dto.AddArticleRequest;
import blog.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 빈을 생성자로 생성하는 롬복에서 지원하는 에너테이션, "final"로 선언된 모든 필드를 매개변수로 받아 해당 필드값을 초기화한다.
@Service // 해당 클래스를 서비스 계층의 빈으로 등록, 비즈니스 로직을 처리, 서블릿 컨테이너(웹 애플리케이션에서 서블릿(서버 프로그램)을 실행하고 관리하는 환경을 제공)에 등록
public class BlogService {
    private final BlogRepository blogRepository; // private -> 해당 클래스에서만 접근 가능, final -> "blogRepository"는 생성시 한번만 값이 설정되고 이후에는 다른 객체로 대체할 수 없다,
    // blogRepository -> BlogRepository 타입의 객체를 참조하는 필드 but @RequiredArgsConstructor 으로 인해서 매개변수로 사용된다.

    public Article save(AddArticleRequest request){ // save 메서드에서의 AddArticleRequest 객체인 "request"를 매개변수로 받아서 이 객체에 포함된 데이터(title, content)를 바탕으로 새로운 Article 객체를 생성하고 데이터베이스에 저장
        /* Article -> 데이터베이스에서 저장할 데이터를 표현하는 클래스, 객체지향 프로그래밍에서 객체를 생성할 수 있는 템플릿 역할,
         save 메서드에서 이 클래스의 객체를 생성하여 데이터 베이스에 저장하는 역할(AddArticleRequest 객체에서 받은 데이터를 바탕으로 Article 객체를 생성하고 저장하는 역할, "Article"는 데이터베이스에서 다루는 주체 */

        return blogRepository.save(request.toEntity()); // toEntity() 메서드를 통해 AddArticleRequest 객체의 데이터가 Article 객체로 변환 -> 변환된 Article 객체를 "blogRepository"에 저장하고 저장된 Article 객체를 반환!
    }
}
