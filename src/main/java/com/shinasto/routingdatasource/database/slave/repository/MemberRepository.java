package com.shinasto.routingdatasource.database.slave.repository;

import com.shinasto.routingdatasource.database.slave.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
