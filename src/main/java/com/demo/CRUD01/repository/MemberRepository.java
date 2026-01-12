package com.demo.CRUD01.repository;

import com.demo.CRUD01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByIsDeletedFalse();

    Optional<Member> findByIdAndIsDeletedFalse(Long id);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

}
