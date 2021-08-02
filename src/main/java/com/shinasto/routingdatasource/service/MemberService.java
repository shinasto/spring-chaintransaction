package com.shinasto.routingdatasource.service;

import com.shinasto.routingdatasource.data.MemberDto;
import com.shinasto.routingdatasource.data.UserDto;

public interface MemberService {
   void saveMember(MemberDto memberDto);

   MemberDto getMemberById(Integer id);

}
