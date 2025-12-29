package com.example.demo.repository;

import com.example.demo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMemoBetweenOrderByMemoDesc(Long from, Long to);

    Page<Memo> findByMemoBetween(Long from, Long to, Pageable pageable);
}
