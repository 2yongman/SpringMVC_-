package sample.spring.yse;

import java.awt.print.Book;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

@Controller // �������� �������� ��û�� �޾Ƶ��̴� ��Ʈ�ѷ���� �����ؼ� �ڹ� ������ ����� �����Ѵ�.
public class BookController {

	@Autowired
	BookService bookservice; // �������̽�

	// ��
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView creat() {
		return new ModelAndView("book/create");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	// http �Ķ���͸� map ������ �ڵ����� ���ε� ���ִ� RequestParameter. HTTP �Ķ���ʹ� ���������� ���� �����͸�
	// ������ �����Ѵ�.
	public ModelAndView createPost(@RequestParam Map<String, Object> map) { // map�� ��� RequestParam ������̼��� �־�� ���ε���
																			// ���������� ��üŸ���̳� ��Į�� Ÿ���� ��� ��� �ȴ�.
		ModelAndView mav = new ModelAndView(); // ModelAndView

		String bookId = this.bookservice.create(map); // ������ create �޼ҵ� ȣ��
		if (bookId == null) {
			mav.setViewName("redirect:/create"); // ������ �Է��� ������ ��� �ٽ� ����ȭ������ �����̷�Ʈ
		} else {
			mav.setViewName("redirect:/detail?bookId=" + bookId); // ModelAndView ��ü�� .setViewName �޼ҵ带 ���� ���� ��θ� �������� ��
																	// �ִ�. ������ �Է� �����ϸ� �� �������� �̵�
		}
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookservice.detail(map); // �����ͺ��̽� ��ȸ ��� detailMap ������ ��´�.

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap); // mav�� ��� ������ �����͸� data��� �̸����� ������ ����� ��Ҵ�.
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId); // å�� PK�� bookId�� mav ��ü�� ��´�.
		mav.setViewName("/book/detail"); //
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookservice.detail(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		return mav;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		boolean isUpdateSuccess = this.bookservice.edit(map);
		if (isUpdateSuccess) {
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		} else {
			mav = this.update(map);
		}
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.bookservice.remove(map); // ������ �����ߴ��� Ȯ���Ѵ�.
		if (isDeleteSuccess) {
			mav.setViewName("redirect:/list"); //������ ���������� �� �������� �����Ƿ� ������� �����̷�Ʈ
		}else {
			String bookId=map.get("bookId").toString();
			mav.setViewName("redirect://detail?bookId=" + bookId); // ������ ���������� �ٽ� ���������� �̵�
		}
		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		
		List<Map<String,Object>> list = this.bookservice.list(map); // å ����� �����ͺ��̽����� �����´�.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list); // �����͸� �信 ������ �� �ְ� mav ��ü�� �ִ´�.
		
		if(map.containsKey("keyword")) {//HTTP �Ķ���Ͱ� ���� ���� ���� ���� �ֱ⿡ �Ķ���Ͱ� �ִ��� �˻�
			mav.addObject("keyword", map.get("keyword")); // �Ķ���Ͱ� �ִٸ� �信 keyword�� ����
		}
		
		mav.setViewName("/book/list"); // �並 �����Ѵ�.
		return mav;
	}

}
