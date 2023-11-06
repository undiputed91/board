package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.service.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Transactional
    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }
    @Transactional
    @GetMapping
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    @Transactional
    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public BoardResponseDto deleteBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return getBoard(id);
    }
}

