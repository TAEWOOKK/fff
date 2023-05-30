package utils;

/* 현재 페이지와 관련 정보 저장
	- 현재 페이지 번호
	- 페이지당 게시글 개수 */
public class Criteria {
	private int pageNum;
	private int rowsPerPage;
	
	//기본 생성자
	public Criteria() {
		//아래 생성자에서 생성한 현재페이지 번호와 페이지당 게시글 개수를 갖고 와서
		//초기화 하고 싶을 경우 this() 사용
		this(1, 10);
	}
	
	//생성자 생성
	public Criteria(int pageNum, int rowsPerPage) {
		super();
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if(pageNum <= 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage <= 0 || rowsPerPage >= 20) {
			this.rowsPerPage = 20;
		} else {
			this.rowsPerPage = rowsPerPage;
		}
	}
	
	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}

}
