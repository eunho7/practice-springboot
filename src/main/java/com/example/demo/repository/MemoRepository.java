package com.example.demo.repository;

import com.example.demo.entity.Memo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMemoBetweenOrderByMemoDesc(Long from, Long to);

    Page<Memo> findByMemoBetween(Long from, Long to, Pageable pageable);

    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
    int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText );

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno}")
    int updateMemoText(@Param("param") Memo memo);

    @Query(value = "select m from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Memo> getListWithQuery(Long mno, Pageable pageable);

    @Query(value = "select m.mno, m.memoText, CURRENT DATE from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

    @Query(value = "select * from where mno > 0", nativeQuery = true)
    List<Object[]> getNativeResult();
}
