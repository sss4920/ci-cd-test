package org.sixth.sixseminar.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.sixth.sixseminar.controller.dto.request.post.PostCreateRequest;
import org.sixth.sixseminar.controller.dto.response.PostGetResponse;
import org.sixth.sixseminar.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
	private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
	private final PostService postService;

	@GetMapping("{postId}")
	public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
		return ResponseEntity.ok(postService.getById(postId));
	}

	@GetMapping
	public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long serviceMemberId) {
		return ResponseEntity.ok(postService.getPosts(serviceMemberId));
	}


	// @PostMapping
	// public ResponseEntity<Void> createPost(
	// 	@RequestBody PostCreateRequest request,
	// 	Principal principal) {
	//
	// 	Long memberId = Long.valueOf(principal.getName());
	// 	URI location = URI.create("/api/posts/" + postService.create(request, memberId));
	//
	// 	return ResponseEntity.created(location).build();
	// }
}
