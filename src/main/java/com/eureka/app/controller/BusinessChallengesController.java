package com.eureka.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.app.model.BusinessChallenges;
import com.eureka.app.repository.BusinessChallengesRepository;

@RestController
public class BusinessChallengesController {
	@Autowired
	BusinessChallengesRepository businessChallengeRepo;

	@GetMapping("/businessChallenges")
	public List<BusinessChallenges> getAllBusinessChallenges(
			@RequestParam(required = false) String fname,
			@RequestParam(required = false) String challengeTitle
			) {
		
		if (fname != null) return businessChallengeRepo.findByFname(fname);
		
		else if (challengeTitle != null)
			return businessChallengeRepo.findByChallengeTitle(challengeTitle);
		
		else return businessChallengeRepo.findAll();
	}

	@GetMapping("/businessChallenges/{id}")
	public Optional<BusinessChallenges> getBusinessChallengeById(@PathVariable String id) {
		return businessChallengeRepo.findById(id);
	}

	@PostMapping("/businessChallenges")
	public BusinessChallenges createBusinessChallenge(@RequestBody BusinessChallenges businessChallenge) {

		return businessChallengeRepo.save(businessChallenge);
	}

//	@PutMapping("businessChallenges/{id}")
//	public ResponseEntity<BusinessChallenges> updateBusinessChallenge(@PathVariable String id,
//			@RequestBody BusinessChallenges businessChallenge) {
//		
//		Optional<BusinessChallenges> myBusinessChallenge = businessChallengeRepo.findById(id);
//		
//		if (!myBusinessChallenge.isPresent())
//			new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//		myBusinessChallenge.get().setId(businessChallenge.getFname());
//		myBusinessChallenge.get().setId(businessChallenge.getLname());
//		myBusinessChallenge.get().setId(businessChallenge.getChallengeTitle());
//		myBusinessChallenge.get().setChallengeDescription(businessChallenge.getChallengeDescription());
//		myBusinessChallenge.get().setId(businessChallenge.getExpiryDate());
//		return new ResponseEntity<>(businessChallengeRepo.save(myBusinessChallenge.get()), HttpStatus.OK);
//	}

	@DeleteMapping("/businessChallenges")
	public ResponseEntity<HttpStatus> deleteAllBusinessChallenges() {
		try {
			businessChallengeRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/businessChallenges/{id}")
	public ResponseEntity<HttpStatus> deleteBusinessChallenge(@PathVariable String id) {
		try {
			businessChallengeRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
