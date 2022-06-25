package com.purusottam.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purusottam.springbootcrud.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
