package com.green.book_shop.book.controller;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class BookCategoryController {
  private final BookService bookService;

  //카테고리 목록 조회 api
  @GetMapping("")
  public ResponseEntity<?> getCategoryList(){
    try{
      //조회가 안되면 List는 null아님!!, list.size() == 0
      List<BookCategoryDTO> list = bookService.selectCategoryList();

      return ResponseEntity
              .status(HttpStatus.OK)
              .body(list);
    } catch (Exception e) {
      e.printStackTrace();//오류가 발생한 위치및 이유를 알려줌
//      return  ResponseEntity
//              .status(HttpStatus.INTERNAL_SERVER_ERROR)
//              .body("카테고리 목록 조회 중 서버 오류 발생");

      //전달할 데이터가 없으면.build() 메서드 호출로 마무리
      return  ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

//카테고리 등록 api
  //post~/categories
  @PostMapping("")
  public ResponseEntity<?> insertCategory(@RequestBody BookCategoryDTO bookCategoryDTO){
    //등록 성공 -> 1리턴
    //등록 안햇으면 -> 0리턴
    int result =  bookService.insertCategory(bookCategoryDTO.getCateName());

    return ResponseEntity
            .status(result == 1 ? HttpStatus.CREATED :  HttpStatus.INTERNAL_SERVER_ERROR )//CREATED : 등록 성공
            .body(result == 1 ? result : "알수없는 이유로 등록이 되지 않았습니다"  );
  }

}


