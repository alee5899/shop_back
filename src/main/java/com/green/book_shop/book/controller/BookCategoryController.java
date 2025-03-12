package com.green.book_shop.book.controller;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class BookCategoryController {
  private final BookService bookService;

  //카테고리 목록 조회 api
  @GetMapping("")
  public List<BookCategoryDTO> getCategoryList(){
    return bookService.selectCategoryList();
  }

//카테고리 등록 api
  //post~/categories
  @PostMapping("")
  public int insertCategory(@RequestBody BookCategoryDTO bookCategoryDTO){
    //등록 성공 -> 1리턴
    //등록 안햇으면 -> 0리턴
    return bookService.insertCategory(bookCategoryDTO.getCateName());
  }

}


