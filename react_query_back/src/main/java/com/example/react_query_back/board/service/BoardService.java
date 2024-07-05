package com.example.react_query_back.board.service;

import com.example.react_query_back.board.model.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> getBoardList() throws Exception;

    BoardVO getBoard(int idx) throws  Exception;

    void createBoard(BoardVO boardVO);

    void deleteBoard(int idx);

    void updateBoard(BoardVO boardVO);
}
