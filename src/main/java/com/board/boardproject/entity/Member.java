package com.board.boardproject.entity;


import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private String nickname;
    private String password;
    private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public boolean isMatchPassword(String rawPassword) {
		return this.password.equals(rawPassword);
	}
}
