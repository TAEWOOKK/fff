package utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//����¡�� ���õ� ��ư�� ����� ����� �ϴ� Ŭ����
public class PageMaker {
	
	private Criteria cri; 		// ���� ������ ����
	private int totalCount;		// �� �Խñ��� ��
	private int startPage;		// ȭ�鿡 ������ ù��° ������ ��ȣ
	private int endPage; 		// ȭ�鿡 ������ ������ ������ ��ȣ
	private boolean prev;		// <����> ��ư ǥ�� ���� 
	private boolean next;		// <����> ��ư ǥ�� ���� 
	private int cntPageNum = 10; // ȭ�鿡 ���̴� ��������ȣ ��
	private int realEndPage;	// ���� �� ������ ��ȣ
	
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
		
		// ��� ���� �ʱ�ȭ
		fieldInit();
	}
	
	public void fieldInit() {
		/* 1) �������� ��ȣ = (����)(�ø�(���� ������ ��ȣ / (�Ǽ�)ȭ��� ������ ��ư�� ��) * ȭ��� ������ ��ư ��)
		����1) ���� ������ 3������
		   3/10(������ ��ư�� ��) = 0.xxx => �� ���� �ø� => 1
		   1 * 10(������ ��ư�� ��) = 10
		����2)���� ������ 12������
		   13/10 = 1.xxx => �ø� => 2
		   2 * 10 = 20 	*/
		endPage = (int)(Math.ceil(cri.getPageNum() / (double)cntPageNum) * cntPageNum);
		
		// 2) ���� ������ ��ȣ ���
		// ���� ������ = �� ������ ��ȣ - ȭ��� ������ ��ȣ �� + 1
		startPage = endPage - cntPageNum + 1;
		
		// 3) �� ������ ��ȣ ���
		// ���� �� ������ ��ȣ = �� �Խñ��� �� / �������� �Խñ��� ����
		//	  ����) 77 / (double)10 = 7.7 =>(�ø�) 8
		realEndPage = (int)Math.ceil(totalCount / (double)cri.getRowsPerPage());
		
		// 4) ���� �� ������ ��ȣ �� ����
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// 5) ����, ���� ��ư ǥ�� ���� ����
		//���� ������ ��ȣ�� '1'�� ���, '����' ��ư �ʿ�X
		prev = (startPage == 1 ? false : true);
		next = (endPage * cri.getRowsPerPage() < totalCount ? true : false);
	}
	
	// �Է� �Ķ����: Ŭ���� ������ ��ȣ
	// ���: QueryString���� ������ ��ȣ�� ������ �� �׸� ���� ������ִ� �޼ҵ�
	// ��) 3������ Ŭ�� ��, ?PageNum=3&rowsPerPage=10
	public String makeQuery(int page) {
		//UriComponentsBuilder: UriComponents��� �ϴ� ��ü�� ���� �������ֱ� ���� ����
		//queryParam: ������ ������ֱ����� �޼ҵ�
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
