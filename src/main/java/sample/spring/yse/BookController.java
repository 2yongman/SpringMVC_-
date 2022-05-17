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

@Controller // 스프링이 브라우저의 요청을 받아들이는 컨트롤러라고 인지해서 자바 빈으로 등록해 관리한다.
public class BookController {

	@Autowired
	BookService bookservice; // 인터페이스

	// 뷰
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView creat() {
		return new ModelAndView("book/create");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	// http 파라미터를 map 변수에 자동으로 바인딩 해주는 RequestParameter. HTTP 파라미터는 브라우저에서 받은 데이터를
	// 서버로 전달한다.
	public ModelAndView createPost(@RequestParam Map<String, Object> map) { // map의 경우 RequestParam 어노테이션이 있어야 바인딩이
																			// 가능하지만 객체타입이나 스칼라 타입일 경우 없어도 된다.
		ModelAndView mav = new ModelAndView(); // ModelAndView

		String bookId = this.bookservice.create(map); // 서비스의 create 메소드 호출
		if (bookId == null) {
			mav.setViewName("redirect:/create"); // 데이터 입력이 실패일 경우 다시 생성화면으로 리다이렉트
		} else {
			mav.setViewName("redirect:/detail?bookId=" + bookId); // ModelAndView 객체는 .setViewName 메소드를 통해 뷰의 경로를 지정해줄 수
																	// 있다. 데이터 입력 성공하면 상세 페이지로 이동
		}
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookservice.detail(map); // 데이터베이스 조회 결과 detailMap 변수에 담는다.

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap); // mav에 뷰로 전달할 데이터를 data라는 이름으로 쿼리의 결과를 담았다.
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId); // 책의 PK인 bookId도 mav 객체에 담는다.
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
		
		boolean isDeleteSuccess = this.bookservice.remove(map); // 삭제가 성공했는지 확인한다.
		if (isDeleteSuccess) {
			mav.setViewName("redirect:/list"); //삭제가 성공했으면 상세 페이지가 없으므로 목록으로 리다이렉트
		}else {
			String bookId=map.get("bookId").toString();
			mav.setViewName("redirect://detail?bookId=" + bookId); // 삭제가 실패했으면 다시 상세페이지로 이동
		}
		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		
		List<Map<String,Object>> list = this.bookservice.list(map); // 책 목록을 데이터베이스에서 가져온다.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list); // 데이터를 뷰에 전달할 수 있게 mav 객체에 넣는다.
		
		if(map.containsKey("keyword")) {//HTTP 파라미터가 있을 수도 없을 수도 있기에 파라미터가 있는지 검사
			mav.addObject("keyword", map.get("keyword")); // 파라미터가 있다면 뷰에 keyword를 전달
		}
		
		mav.setViewName("/book/list"); // 뷰를 리턴한다.
		return mav;
	}

}
