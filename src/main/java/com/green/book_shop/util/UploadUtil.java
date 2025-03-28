package com.green.book_shop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//첨부파일 기능 모음 클래스
@Component
public class UploadUtil {
  //application.properties 파일에 정의한
  //file.upload.dir 값을 가져와서 uploadPath 변수에 저장
  @Value("${file.upload.dir}")
  private String uploadPath;

  //단일 파일 업로드
  public String fileUpload(MultipartFile file){
    //파일을 첨부했을 때만 첨부기능 실행
    if (file != null){
      //첨부기능 실행
      //화면에서 선택한 원본 파일명
      String originalFileName = file.getOriginalFilename();

      //첨부할 파일이름을 생성한다
      String attachFileName = getAttachedFileName(originalFileName);

      //업로드경로 파일명을 연결
      File f = new File(uploadPath + attachFileName);

      //파일 첨부하는 실제 코드
      // 오류 났을때 어떻게 처리하세요
      try {
        //실제로 파일을 첨부하는 기능
        //내가 받아온 첨부파일(file).실제 업로드 될 경로(f)로 옮긴다
        file.transferTo(f);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return attachFileName;
    }
      return null;
  }

  public void multiFileUpload(MultipartFile[] files){
    for (MultipartFile eachFile : files){
      fileUpload(eachFile);
    }
  }

  // 원본파일명에서 첨부할 파일명을 생하는 메서드
  public String getAttachedFileName(String originalFilename){
    //첨부할 파일명(랜덤한 문자열 생성)
    String uuid = UUID.randomUUID().toString();

    //화면에서 선택한 파일의 확장자 추출
    String[] result  = originalFilename.split("\\."); //aa,bb,cc
    String extension =  result [result.length-1];
    //완성된 첨부할 파일명
    String attachFileName =  uuid + "." + extension;
    return attachFileName;
  }
}
