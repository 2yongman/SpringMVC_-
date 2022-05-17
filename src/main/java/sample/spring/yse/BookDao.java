package sample.spring.yse;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 데이터에 접근하는 클래스임을 명시, 스프링이 데이터를 관리하는 클래스라고 인지해서 자바 빈으로 등록해서 관리한다.
public class BookDao {
	@Autowired // @Autowired 어노테이션 덕에 sqlSessionTemplate 객체를 사용할 수 있게 됨 -> 곧 의존성 주입 하라는 뜻
	SqlSessionTemplate sqlSessionTemplate; // 매퍼 XML을 실행시키기 위해서 SqlSessionTemplate 객체를 멤버 변수로 선언

	// 생성
	public int insert(Map<String, Object> map) {
		// book.insert는 book_SQL.xml에서 namespace가 book이고, id가 insert였음 네임스페이스+id 조합으로
		// 쿼리를 찾아서 실행한다.
		// map은 insert(Map map) 메소드가 전달받은 파라미터이므로 전달받은 파라미터를 그대로 매퍼 XML에 전달한다.
		return this.sqlSessionTemplate.insert("book.insert", map); // -> insert 메소드는 XML 매퍼가 반환한 값을 바로 리턴한다.
	}

	// 읽기
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map); // selectOne : 데이터 한개만 가져올 때 사용. null이면
																				// TooManyResultsException 예외를 던짐
	}

	// 수정
	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}
	
	// 삭제
	public int delete(Map<String, Object>map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}
	
	//목록
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
		}  
	
}
