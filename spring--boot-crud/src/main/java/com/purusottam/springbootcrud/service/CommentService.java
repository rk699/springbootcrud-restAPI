package com.purusottam.springbootcrud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.purusottam.springbootcrud.model.Comment;
import com.purusottam.springbootcrud.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentrepo;

	 public List<Comment> getAllComment(Integer pageNo, Integer pageSize, String[] id)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(id).descending());
	        System.out.println("paging info "+paging);
	 
	        Page<Comment> pagedResult = commentrepo.findAll(paging);
	        System.out.println("paging result "+pagedResult);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Comment>();
	        }
	    }
}
