package com.eureka.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.app.model.Idea;
import com.eureka.app.model.User;
import com.eureka.app.payload.request.IdeaRequest;
import com.eureka.app.repository.IdeasRepository;
import com.eureka.app.repository.UserRepository;
import com.eureka.app.security.services.UserDetailsImpl;
import com.eureka.app.service.SequenceGeneratorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IdeasController {
	@Autowired
	IdeasRepository ideasRepo;
	
	@Autowired
	UserRepository usersRepo;
	
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
		
		Pageable sortedPage = PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdDate"));
		Iterable<Idea> iterable = ideasRepo.findAll(sortedPage);
		List<Idea> ideas = new ArrayList<>();
		iterable.forEach(ideas::add);
		return ideas;
	}

	@GetMapping("/ideas/{id}")
	public Idea getIdeaById(@PathVariable String id) {
		Idea idea = ideasRepo.findById(id).get();
		return idea;
	}
	
//	@GetMapping("/ideas/{ideaTitle}")
//	public List<Comments> getCommentsByIdeaTitle(@PathVariable String ideaTitle) {
//		Idea idea = ideasRepo.findByIdeaTitle(ideaTitle).get(0);
//		List<Comments> comments = new ArrayList<>();
//		comments.addAll(idea.getComments());
//		return comments;
//	}

//	@PostMapping("/ideas")
//	public Idea createIdea(@RequestBody Idea idea) {
//
//		return ideasRepo.save(idea);
//	}
	
	@PostMapping("/ideas")
	public ResponseEntity<Idea> createTutorial(@RequestBody IdeaRequest ideaReq) {
    try {
    	int seqNumber = (int) SequenceGeneratorService.generateSequence(Idea.SEQUENCE_NAME);
    	String idString = "ID" + String.format("%05d", seqNumber);
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    	String email = userDetails.getUsername();
    	User user = usersRepo.findByEmail(email);
    	
    	String fname = user.getFname();
        String lname = user.getLname();
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String createdDate = formatter.format(date);
        
        Idea idea = new Idea(fname, lname, ideaReq.getIdeaTitle(), ideaReq.getIdeaDescription(), createdDate);
        idea.setIdeaId(idString);
        ideasRepo.save(idea);
        
        return new ResponseEntity<>(idea, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
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
//		
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
