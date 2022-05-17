package sample.spring.yse;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // �����Ϳ� �����ϴ� Ŭ�������� ���, �������� �����͸� �����ϴ� Ŭ������� �����ؼ� �ڹ� ������ ����ؼ� �����Ѵ�.
public class BookDao {
	@Autowired // @Autowired ������̼� ���� sqlSessionTemplate ��ü�� ����� �� �ְ� �� -> �� ������ ���� �϶�� ��
	SqlSessionTemplate sqlSessionTemplate; // ���� XML�� �����Ű�� ���ؼ� SqlSessionTemplate ��ü�� ��� ������ ����

	// ����
	public int insert(Map<String, Object> map) {
		// book.insert�� book_SQL.xml���� namespace�� book�̰�, id�� insert���� ���ӽ����̽�+id ��������
		// ������ ã�Ƽ� �����Ѵ�.
		// map�� insert(Map map) �޼ҵ尡 ���޹��� �Ķ�����̹Ƿ� ���޹��� �Ķ���͸� �״�� ���� XML�� �����Ѵ�.
		return this.sqlSessionTemplate.insert("book.insert", map); // -> insert �޼ҵ�� XML ���۰� ��ȯ�� ���� �ٷ� �����Ѵ�.
	}

	// �б�
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map); // selectOne : ������ �Ѱ��� ������ �� ���. null�̸�
																				// TooManyResultsException ���ܸ� ����
	}

	// ����
	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}
	
	// ����
	public int delete(Map<String, Object>map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}
	
	//���
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
		}  
	
}
