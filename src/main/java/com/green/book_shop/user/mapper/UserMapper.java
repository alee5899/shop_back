package com.green.book_shop.user.mapper;

import com.green.book_shop.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

//회원가입
public interface UserMapper {
  public int insertUser(UserDTO userDTO);

  //id 중복확인
  public String checkUserId(String userID);

  //로그인
  public UserDTO login(UserDTO userDTO);
}
