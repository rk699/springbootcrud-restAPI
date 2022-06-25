package com.purusottam.springbootcrud.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purusottam.springbootcrud.exception.ResourceNotFoundException;
import com.purusottam.springbootcrud.model.Comment;
import com.purusottam.springbootcrud.repository.CommentRepository;
import com.purusottam.springbootcrud.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommentService commentservice;

	@GetMapping("/comments")
	public ResponseEntity <?> getAllComments(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String[] id) 
	{
     List<Comment> comm=commentservice.getAllComment( pageNo,  pageSize,  id);
     HttpHeaders headers=new HttpHeaders();
     headers.add("Responded", "CommentController");
	return ResponseEntity.ok().headers(headers).body(comm);
	}

	@PostMapping("/comments")
	public ResponseEntity <?> createComment(@RequestBody Comment comment) {
		 commentRepository.save(comment);
		HttpHeaders headers=new HttpHeaders();
	     headers.add("Responded", "CommentController");
		return ResponseEntity.ok().headers(headers).build();
	}

	@GetMapping("/comments/{id}")
	public ResponseEntity <?> getCommentById(@PathVariable(value = "id") Long commentId) {
		Comment comm=commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		      HttpHeaders headers=new HttpHeaders();
		     headers.add("Responded", "CommentController-single");
			return ResponseEntity.ok().headers(headers).body(comm);
		
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<?> updateComment(@PathVariable(value = "id") Long commentId, @RequestBody Comment commentDetails) {

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		comment.setPostName(commentDetails.getPostName());
		comment.setComment(commentDetails.getComment());

		 commentRepository.save(comment);
		HttpHeaders headers=new HttpHeaders();
	     headers.add("Responded", "CommentController");
		return ResponseEntity.ok().headers(headers).build();
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		commentRepository.delete(comment);
		HttpHeaders headers=new HttpHeaders();
	     headers.add("Responded", "CommentController");
		return ResponseEntity.ok().headers(headers).build();

		
	}
}
