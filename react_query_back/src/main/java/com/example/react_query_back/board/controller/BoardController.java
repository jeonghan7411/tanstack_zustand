package com.example.react_query_back.board.controller;

import com.example.react_query_back.board.model.BoardVO;
import com.example.react_query_back.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/boardList",method = RequestMethod.GET)
    public ResponseEntity<Object> boardList() {
        try {
            List<BoardVO> list = boardService.getBoardList();
            LOGGER.info("[ boardList ]:  SUCCESS");
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            LOGGER.error("[ boardList ]: ERROR {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
    public ResponseEntity<Object> boardDetail(@RequestParam("idx") int idx){
        try{
            BoardVO board = boardService.getBoard(idx);
            LOGGER.info(" [ boardDetail ] : SUCCESS ");
            return ResponseEntity.ok().body(board);
        }catch (Exception e){
            LOGGER.error(" [ boardDetail ] : ERROR : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<Object> createBoard(@RequestBody BoardVO boardVO){
        try{
            boardService.createBoard(boardVO);
            LOGGER.info(" [ createBoard ] : SUCCESS ");
//            return ResponseEntity.status(HttpStatus.CREATED).body("CREATE SUCCESS");
            return ResponseEntity.ok().body("CREATE SUCCESS");
        }catch(Exception e){
            LOGGER.error(" [ createBoard ] : ERROR : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBoard(@RequestParam("idx") int idx){
        try{
            boardService.deleteBoard(idx);
            LOGGER.info(" [ deleteBoard ] : SUCCESS ");
            return ResponseEntity.ok().body("DELETE SUCCESS");
        }catch(Exception e){
            LOGGER.error(" [ deleteBoard ] : ERROR : {} ",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBoard(@RequestBody BoardVO boardVO){
        try{
            boardService.updateBoard(boardVO);
            LOGGER.info(" [ updateBoard ] : SUCCESS");
            return ResponseEntity.ok().body("UPDATE SUCCESS");
        }catch(Exception e){
            LOGGER.error(" [ updateBoard ] : ERROR : {}",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
