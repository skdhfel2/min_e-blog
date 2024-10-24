package blog.springbootdeveloper.dto;
// DTO : Data Transfer Object 의 약자로, 계층 간 데이터를 전송하기 위한 객체
import blog.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor // 모든 필드를 매개변수로 가지는 생성자를 자동으로 생성 -> 생성자 코드를 반복적으로 작성하는 수고를 덜어준다.
@Getter // 해당 클래스의 모든 필드에 대해 자동으로 getter 메서드 생성 -> 각 필드값을 외부에서 읽을 수 있게 함으로써 데이터 전송할때 필수!
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){ // "DTO"에서 엔티티 객체를 생성하는 역할
        return Article.builder() // 빌더 패턴을 통해 Article 객체를 생성
                .title(title)
                .content(content)
                .build();
    }
}
