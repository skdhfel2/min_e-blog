package blog.springbootdeveloper.repository;

import blog.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
// BlogRepository 인터페이스가 JpaRepository 인터페이스를 확장
// JpaRepository<Article, Long>은 JPA를 사용해 데이터베이스와 상호작용할 수 있는 여러 CRUD 메서드를 자동으로 제공
// Article은 데이터베이스 테이블에 해당하는 엔티티 클래스이며, Long은 이 테이블의 기본 키 타입을 지정
