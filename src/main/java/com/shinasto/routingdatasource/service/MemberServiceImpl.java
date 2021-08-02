package com.shinasto.routingdatasource.service;

import com.shinasto.routingdatasource.data.MemberDto;
import com.shinasto.routingdatasource.database.slave.entity.Member;
import com.shinasto.routingdatasource.database.slave.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

  @Autowired MemberRepository memberRepository;

  @Transactional
  public void saveMember(MemberDto memberDto) {
    log.info("TX Name={}", TransactionSynchronizationManager.getCurrentTransactionName());

    Member member = new Member();
    member.setName(memberDto.getName());
    member.setAge(memberDto.getAge());
    memberRepository.save(member);

    throw new IllegalStateException("exception");
  }

  @Transactional(readOnly = true)
  public MemberDto getMemberById(final Integer id) {

    log.info("TX Name={}", TransactionSynchronizationManager.getCurrentTransactionName());

    Optional<Member> optMember = this.memberRepository.findById(id);
    if(optMember.isPresent()) {
      Member member = optMember.get();

      log.info("Member Id={}, Name={}", member.getId(), member.getName());
      return new MemberDto(member.getId(), member.getName(), member.getAge());
    }

    return null;
  }

}
