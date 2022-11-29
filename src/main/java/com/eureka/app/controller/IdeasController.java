package com.eureka.app.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.app.model.Comments;
import com.eureka.app.model.Idea;
import com.eureka.app.repository.IdeasRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IdeasController {
	@Autowired
	IdeasRepository ideasRepo;
	
	@GetMapping("/ideas")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Idea> getAllIdeas(
			@RequestParam(required = false) String fname,
			@RequestParam(required = false) String ideaTitle
			) {
		
		if (fname != null) return ideasRepo.findByFname(fname);
		
		else if (ideaTitle != null)
			return ideasRepo.findByIdeaTitle(ideaTitle);
		
		else return ideasRepo.findAll();
//		return ideasRepo.findAll();
	}
	
	@GetMapping("/ideas/recent")
	public List<Idea> getRecentIdeas() {
		
		Pageable sortedPage = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
		Iterable<Idea> iterable = ideasRepo.findAll(sortedPage);
		List<Idea> ideas = new ArrayList<>();
		iterable.forEach(ideas::add);
		return ideas;
		
//		return ideasRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
	}

	@GetMapping("/ideas/{id}")
	public Idea getIdeaById(@PathVariable String id) {
		Idea idea = ideasRepo.findById(id).get();
		return idea;
	}

	@PostMapping("/ideas")
	public Idea createIdea(@RequestBody Idea idea) {

		return ideasRepo.save(idea);
	}

//	@PutMapping("ideas/{id}")
//	public ResponseEntity<Idea> updateIdea(@PathVariable String id,
//			@RequestBody Idea idea) {
//		
//		Optional<Idea> myIdea = ideasRepo.findById(id);
//		
//		if (!myIdea.isPresent())
//			new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//		myIdea.get().setId(idea.getId());
//		myIdea.get().setFname(idea.getFname());
//		myIdea.get().setLname(idea.getLname());
//		myIdea.get().setIdeaTitle(idea.getIdeaTitle());
//		myIdea.get().setIdeaDescription(idea.getIdeaDescription());
//		myIdea.get().setLikesCount(idea.getLikesCount());
//		myIdea.get().setCommentsCount(idea.getCommentsCount());
//		myIdea.get().setLikes(idea.getLikes());
//		myIdea.get().setComments(idea.getComments());
//		return new ResponseEntity<>(ideasRepo.save(myIdea.get()), HttpStatus.OK);
//	}

	@DeleteMapping("/ideas")
	public ResponseEntity<HttpStatus> deleteAllIdeas() {
		try {
			ideasRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/ideas/{id}")
	public ResponseEntity<HttpStatus> deleteIdea(@PathVariable String id) {
		try {
			ideasRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
