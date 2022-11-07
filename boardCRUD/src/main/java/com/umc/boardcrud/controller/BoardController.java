package com.umc.boardcrud.controller;

import com.umc.boardcrud.domain.Board;
import com.umc.boardcrud.domain.EditBoard;
import com.umc.boardcrud.service.BoardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시글 전체 조회
    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardService.getBoard();
    }

    //게시글 검색
    @GetMapping("/boards/search")
    public List<Board> searchBoards(@RequestParam(name = "word") String word) {
        return boardService.searchBoard(word);
    }

    // 게시글 작성
    @PostMapping("/boards")
    public Board postBoard(@RequestBody Board board){
        return boardService.postBoard(board);
    }

    //게시글 수정
    @PutMapping("/boards/{id}")
    public Board modifyBoard(@PathVariable("id") Integer id, @RequestBody EditBoard editBoard) {

        return boardService.modifyBoard(id,editBoard);
    }

    //게시글 삭제
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable("id") Integer id) {
        boardService.deleteBoard(id);
    }

}
