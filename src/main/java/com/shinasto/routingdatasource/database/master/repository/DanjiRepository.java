package com.shinasto.routingdatasource.database.master.repository;

import com.shinasto.routingdatasource.database.master.entity.Danji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanjiRepository extends JpaRepository<Danji, Integer> {
}
