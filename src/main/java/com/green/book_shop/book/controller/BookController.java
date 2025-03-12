package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")


public class BookController {
  private final BookService bookService;


  //도서 등록 api
  @PostMapping("")
  public void  regBook(@RequestBody BookDTO bookDTO){
    bookService.insertBook(bookDTO);
  }

}
