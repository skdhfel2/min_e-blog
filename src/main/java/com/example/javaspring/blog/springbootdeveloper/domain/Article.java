package blog.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 필드 == 변수
@Entity // 엔티티로 지정 : 데이터베이스의 레코드 = 테이블의 한 행(row), CRUD(생성,읽기,업데이트,삭제) 작업의 기본 단위로 활용
@Getter // 게터 메서드를 정의 하지 않기 위해서 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 정의하지 않아도 됨, JPA 엔티티에서 기본 생성자를 외부에서 직접 호출할 수 없게 보호하는 용도
public class Article {

    @Id // id 필드가 엔티티의 기본 키임을 나타냄, 기본키 : 테이블에서 각 레코드를 고유하게 식별
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // 기본 키 값을 일일히 설정할 필요없이 데이터베이스에서 기본 키 값을 자동으로 생성하게 만드는 역할
    // @GeneratedValue: 기본 키값을 자동으로 생성
    // strategy 속성은 기본 키 생성 전략(새로운 기본 키 값을 어떻게 만들지 정하는 규칙)을 정의
    // GenerationType.IDENTITY: IDENTITY 전략은 데이터베이스가 자동으로 고유한 값을 생성하고 관리하게 만들고 새로운 데이터가 추가될 때마다 기본 키 값이 자동으로 1씩 증가한다.
    // Article 엔티티에 id가 기본 키로 설정된 상태이므로 데이터베이스가 각 Article 레코드에 대해 고유한 id 값을 자동으로 할당한다.
    @Column(name = "id" , updatable = false) // @Column(name = "id") : "id"라는 변수가 데이터베이스의 id 칼럼과 연결, updatable = false : 한번 설정된 이후에는 수정 불가
    private Long id; // 외부에서 접근할수 없는 long 타입 id 필드(=변수) 정의

    @Column(name = "title", nullable = false) // nullable = false : 해당 필드("title"필드)가 "null"값을 가질수 없다, 데이터베이스에 이 필드를 저장할때 반드시 값이 존재해야한다.
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
    @Builder // 빌더 패턴(객체 생성과정을 단순화하고 여러 매개변수 사용)으로 객체 생성, 생성자가 이닌 메서드를 통해 객체 생성 가능
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
    public Long getId(){ // id 필드값을 가져와 반환하는 getter 메서드 -> Article 객체의 "id"값을 외부에서도 읽을수 있게됨 -> 이러한 캡슐화 지원으로 필드에 대한 직접 접근을 제한
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
}
