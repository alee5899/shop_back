package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.dto.ImgDTO;
import com.green.book_shop.book.service.BookService;
import com.green.book_shop.util.UploadUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")


public class BookController {
  private final BookService bookService;
  private final UploadUtil uploadUtil;


  //도서 등록 api
  @PostMapping("")
  // required = false 이걸 넣으면 넣을수도 안넣을수도 있다
  public void  regBook(
          BookDTO bookDTO,
          @RequestParam(name = "mainImg",required = true) MultipartFile mainImg,
          @RequestParam(name = "subImg",required = true) MultipartFile subImg
  ){

    //첨부파일(도서 이미지)업로드
    String mainAttachedFileName = uploadUtil.fileUpload(mainImg); // 첨부된 파일명은 fileUpload() 메서드에서 만들어짐
    String subAttachedFileName = uploadUtil.fileUpload(subImg);

    //다음에 들어갈 BOOK_CODE 조회
    int nextBookCode = bookService.getNextBookCode();

    //BOOK 테이블 데이터 INSERT
    bookDTO.setBookCode(nextBookCode);
    bookService.insertBook(bookDTO);

    //bookDTO에 이미지 데이터 저장
    //자바에서 한번엔 받아올수 없어서 하나하나 따로  받아준다
    List<ImgDTO> imgList = new ArrayList<>();
    ImgDTO mainImgDTO = new ImgDTO();
    mainImgDTO.setOriginFileName(mainImg.getOriginalFilename());
    mainImgDTO.setAttachedFileName(mainAttachedFileName);
    mainImgDTO.setIsMain("Y");
    mainImgDTO.setBookCode(nextBookCode);

    ImgDTO subImgDTO = new ImgDTO();
    subImgDTO.setOriginFileName(subImg.getOriginalFilename());
    subImgDTO.setAttachedFileName(subAttachedFileName);
    subImgDTO.setIsMain("N");
    subImgDTO.setBookCode(nextBookCode);

    //내가 실수로 imgDTOList 로 적었는데 imgList 로 적으니 당연히 오류나지
    imgList.add(mainImgDTO);
    imgList.add(subImgDTO);
    bookDTO.setImgList(imgList);


    //BOOK_IMG 테이블에 도서 이미지 정보 INSERT
    bookService.insertImg(bookDTO);// 매개변수로 bookDTO 들어와야한다
  }

}
