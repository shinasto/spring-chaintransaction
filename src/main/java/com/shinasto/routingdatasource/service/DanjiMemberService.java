package com.shinasto.routingdatasource.service;

import com.shinasto.routingdatasource.data.DanjiDto;
import com.shinasto.routingdatasource.data.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class DanjiMemberService {

  @Autowired
  private MemberService memberService;

  @Autowired
  private DanjiService danjiService;

  public void required_required() {
    this.danjiService.saveDanji(new DanjiDto(null, "name"));
    this.memberService.saveMember(new MemberDto(null, "name", "32"));
  }
}
