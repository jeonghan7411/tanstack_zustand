package com.example.react_query_back.board.dao;

import com.example.react_query_back.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardVO> getBoardList() throws Exception;

    BoardVO getBoard(int idx) throws  Exception;

    void createBoard(BoardVO boardVO);

    void deleteBoard(int idx);

    void updateBoard(BoardVO boardVO);
}
