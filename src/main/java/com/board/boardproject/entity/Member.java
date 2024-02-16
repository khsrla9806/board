package com.board.boardproject.entity;


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
}
