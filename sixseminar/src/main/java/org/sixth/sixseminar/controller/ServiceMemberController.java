package org.sixth.sixseminar.controller;

import java.net.URI;

import org.sixth.sixseminar.controller.dto.request.servicemember.ServiceMemberRequest;
import org.sixth.sixseminar.service.ServiceMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
@Slf4j
public class ServiceMemberController {

	private final ServiceMemberService serviceMemberService;

	@PostMapping("sign-up")
	public ResponseEntity<Void> signUp(@RequestBody ServiceMemberRequest request) {
		URI location = URI.create(serviceMemberService.create(request));
		return ResponseEntity.created(location).build();
	}

	@PostMapping("sign-in")
	public ResponseEntity<Void> signIn(@RequestBody ServiceMemberRequest request) {
		serviceMemberService.signIn(request);
		return ResponseEntity.noContent().build();
	}
}
