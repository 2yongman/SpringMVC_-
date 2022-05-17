package sample.spring.yse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//���� ���̾�� �������̽��� Ŭ������ �Բ� ����Ѵ�. �������� ���� Ŭ���� �����ϴ� ���� �����ϰ� �������̽��� ���� �����ϴ� ���� �����ϴ� �����ӿ�ũ�̴�.
@Service
//Impl�� �ٿ��δ� ������ Ŭ�������� �������̽����� �����ϱⰡ ���� �����̴�.
public class BookServiceImpl implements BookService { // implements BookService �������̽� ����� �����Ѵ�.
	@Autowired
	BookDao bookDao;

	@Override // ���� �������̽��� ���ǵ� ���� ������ �Ѵٴ� ��, ���� �������̽��� �޼ҵ� �ñ״��İ� ���� ��� ������ ǥ�����ָ鼭 �ڵ����� �������̽� �ñ��ĸ� ������ִ� ���
	public String create(Map<String, Object> map) {
		int affectRowCount = this.bookDao.insert(map);
		if (affectRowCount == 1) {
			return map.get("book_id").toString();
		}
		return null;
	}
	
	//���񽺴� DAo�� ȣ���� ����� �ٷ� �����ϴ� �ϸ� �Ѵ�.
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
