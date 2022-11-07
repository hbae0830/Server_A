package com.umc.boardcrud.service;

import com.umc.boardcrud.domain.Board;
import com.umc.boardcrud.domain.EditBoard;
import com.umc.boardcrud.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    @Transactional
    public List<Board> getBoard(){
        return boardRepository.findAll();
    }

    @Transactional
    public Board postBoard(Board board){
        return boardRepository.save(board.toEntity());
    }

    @Transactional
    public List<Board> searchBoard(String word) {
        return boardRepository.findByTitleContaining(word);
    }

    @Transactional
    public Board modifyBoard(Integer id, EditBoard editboard) {
        Board board = boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. "+id));
        board.setTitle(editboard.getTitle());
        board.setContent(editboard.getContent());
        return boardRepository.save(board.toEntity());
    }

    @Transactional
    public void deleteBoard(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. "+id));
        boardRepository.delete(board);
    }
}
