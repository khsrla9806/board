package com.board.boardproject.common.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagination<T> {
	// 페이지에 들어가는 데이터 목록
	private List<T> data;
	
	// 조회하는 리소스의 총 개수
	private int totalCount;
	
	// 총 페이지 개수 (1페이지부터 시작했을 때의 개수)
	private int totalPages;
	
	// 첫 번째 페이지인지 여부
	private boolean isFirstPage;
	
	// 마지막 페이지인지 여부
	private boolean isLastPage;
	
	// Spring에서 제공하는 Pageable 객체
	private Pageable pageable;
	
	public static <T> Pagination<T> of(List<T> data, Pageable pageable, int totalCount) {
		Pagination<T> pagination = new Pagination<>();
		pagination.setData(data);
		pagination.setPageable(pageable);
		pagination.setTotalCount(totalCount);
		
		if (pageable.getPageSize() * pageable.getPageNumber() + data.size() >= totalCount) {
			pagination.setLastPage(true);
		}
		
		if (pageable.getPageNumber() == 0) {
			pagination.setFirstPage(true);
		}
		
		int pageSize = pageable.getPageSize();
		pagination.setTotalPages(totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1);
		
		
		return pagination;
	}
}
