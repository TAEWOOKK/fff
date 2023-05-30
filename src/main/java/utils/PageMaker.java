package utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//페이징에 관련된 버튼을 만드는 기능을 하는 클래스
public class PageMaker {
	
	private Criteria cri; 		// 현재 페이지 정보
	private int totalCount;		// 총 게시글의 수
	private int startPage;		// 화면에 보여질 첫번째 페이지 번호
	private int endPage; 		// 화면에 보여질 마지막 페이지 번호
	private boolean prev;		// <이전> 버튼 표시 여부 
	private boolean next;		// <다음> 버튼 표시 여부 
	private int cntPageNum = 10; // 화면에 보이는 페이지번호 수
	private int realEndPage;	// 실제 끝 페이지 번호
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		// 멤버 변수 초기화
		fieldInit();
	}
	
	public void fieldInit() {
		/* 1) 끝페이지 번호 = (정수)(올림(현재 페이지 번호 / (실수)화면당 페이지 버튼의 수) * 화면당 페이지 버튼 수)
		예시1) 현재 페이지 3페이지
		   3/10(페이지 버튼의 수) = 0.xxx => 이 값을 올림 => 1
		   1 * 10(페이지 버튼의 수) = 10
		예시2)현재 페이지 12페이지
		   13/10 = 1.xxx => 올림 => 2
		   2 * 10 = 20 	*/
		endPage = (int)(Math.ceil(cri.getPageNum() / (double)cntPageNum) * cntPageNum);
		
		// 2) 시작 페이지 번호 계산
		// 시작 페이지 = 끝 페이지 번호 - 화면당 페이지 번호 수 + 1
		startPage = endPage - cntPageNum + 1;
		
		// 3) 끝 페이지 번호 계산
		// 실제 끝 페이지 번호 = 총 게시글의 수 / 페이지당 게시글의 개수
		//	  예시) 77 / (double)10 = 7.7 =>(올림) 8
		realEndPage = (int)Math.ceil(totalCount / (double)cri.getRowsPerPage());
		
		// 4) 실제 끝 페이지 번호 값 수정
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// 5) 이전, 다음 버튼 표시 여부 결정
		//시작 페이지 번호가 '1'일 경우, '이전' 버튼 필요X
		prev = (startPage == 1 ? false : true);
		next = (endPage * cri.getRowsPerPage() < totalCount ? true : false);
	}
	
	// 입력 파라미터: 클릭한 페이지 번호
	// 출력: QueryString으로 페이지 번호와 페이지 당 항목 수를 만들어주는 메소드
	// 예) 3페이지 클릭 시, ?PageNum=3&rowsPerPage=10
	public String makeQuery(int page) {
		//UriComponentsBuilder: UriComponents라고 하는 객체의 값을 생성해주기 위한 도구
		//queryParam: 쿼리를 만들어주기위한 메소드
		UriComponents uri = UriComponentsBuilder.newInstance() 
				.queryParam("pageNum", page)
				.queryParam("rowsPerPage", cri.getRowsPerPage())
				.build();
		
		return uri.toString();
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCntPageNum() {
		return cntPageNum;
	}
	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}
	public int getRealEndPage() {
		return realEndPage;
	}
	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" 
				+ next + ", cntPageNum=" + cntPageNum + ", realEndPage=" + realEndPage + "]";
	}

}
