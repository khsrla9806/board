package com.board.boardproject.repository;

import com.board.boardproject.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member findById(Long id);
}
