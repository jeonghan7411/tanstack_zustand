package com.example.react_query_back.board.service.impl;

import com.example.react_query_back.board.dao.BoardMapper;
import com.example.react_query_back.board.model.BoardVO;
import com.example.react_query_back.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Override
    public List<BoardVO> getBoardList() throws Exception{
        return boardMapper.getBoardList();
    }

    @Override
    public BoardVO getBoard(int idx) throws Exception {
        return boardMapper.getBoard(idx);
    }

    @Override
    public void createBoard(BoardVO boardVO) {
        boardMapper.createBoard(boardVO);
    }

    @Override
    public void deleteBoard(int idx) {
        boardMapper.deleteBoard(idx);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {
        boardMapper.updateBoard(boardVO);
    }
}
