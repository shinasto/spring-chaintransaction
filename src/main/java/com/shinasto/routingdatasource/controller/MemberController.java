package com.shinasto.routingdatasource.controller;

import com.shinasto.routingdatasource.common.data.ResponseData;
import com.shinasto.routingdatasource.data.MemberDto;
import com.shinasto.routingdatasource.service.DanjiMemberService;
import com.shinasto.routingdatasource.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

  @Autowired MemberService memberService;

  @Autowired DanjiMemberService danjiMemberService;

  @GetMapping("/{id}")
  public Object getMemberById(@PathVariable(name = "id") final Integer id) {
    MemberDto member = this.memberService.getMemberById(id);
    return ResponseData.of(member);
  }

  @GetMapping("/test")
  public Object getMemberById() {
     this.danjiMemberService.required_required();
    return ResponseData.of(null);
  }
}
