package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository =  boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);
        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoard() {

        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);
        if(board.getPassword().equals(requestDto.getPassword())){
        board.update(requestDto);
        return new BoardResponseDto(board);}
        else {return new BoardResponseDto("비밀번호 불일치!");}
    }

    public BoardResponseDto deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);
        if(board.getPassword().equals(requestDto.getPassword())){boardRepository.delete(board);}
        else{return new BoardResponseDto("비밀번호 불일치!");}
        return new BoardResponseDto("게시글 삭제 완료");
    }
    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 게시글이 존재하지 않습니다.")
        );}
}
