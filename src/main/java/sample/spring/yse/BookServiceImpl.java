package sample.spring.yse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//서비스 레이어는 인터페이스와 클래스를 함께 사용한다. 스프링은 직접 클래스 생성하는 것을 지양하고 인터페이스를 통해 접근하는 것을 권장하는 프레임워크이다.
@Service
//Impl을 붙여두는 이유는 클래스인지 인터페이스인지 구별하기가 쉽기 때문이다.
public class BookServiceImpl implements BookService { // implements BookService 인터페이스 사용을 선언한다.
	@Autowired
	BookDao bookDao;

	@Override // 상위 인터페이스에 정의된 것을 재정의 한다는 뜻, 상위 인터페이스에 메소드 시그니쳐가 없을 경우 오류를 표시해주면서 자동으로 인터페이스 시그쳐를 만들어주는 기능
	public String create(Map<String, Object> map) {
		int affectRowCount = this.bookDao.insert(map);
		if (affectRowCount == 1) {
			return map.get("book_id").toString();
		}
		return null;
	}
	
	//서비스는 DAo를 호출한 결과를 바로 리턴하는 일만 한다.
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
		return this.bookDao.selectDetail(map);
	}
	
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);
		return affectRowCount==1;
	}
	
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);
		return affectRowCount==1;
	}
	
	@Override  
	public List<Map<String, Object>> list(Map<String, Object> map){  
	return this.bookDao.selectList(map);  
	}  
	
}
