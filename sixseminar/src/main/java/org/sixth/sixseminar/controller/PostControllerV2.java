package org.sixth.sixseminar.controller;

import java.net.URI;

import org.sixth.sixseminar.controller.dto.request.post.PostCreateRequest;
import org.sixth.sixseminar.service.PostServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/posts")
@RequiredArgsConstructor
public class PostControllerV2 {

	private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
	private final PostServiceV2 postService;

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Void> createPostV2(@RequestHeader(CUSTOM_AUTH_ID) Long memberId, @RequestPart MultipartFile image, PostCreateRequest request) {
		URI location = URI.create("/api/posts/v2" + postService.createV2(request, image, memberId));
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
		postService.deleteByIdV2(postId);
		return ResponseEntity.noContent().build();
	}
}
