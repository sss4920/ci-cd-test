package org.sixth.sixseminar.service;

import java.util.List;

import org.sixth.sixseminar.controller.dto.request.post.PostCreateRequest;
import org.sixth.sixseminar.controller.dto.request.post.PostUpdateRequest;
import org.sixth.sixseminar.controller.dto.response.PostGetResponse;
import org.sixth.sixseminar.domain.ServiceMember;
import org.sixth.sixseminar.infrastructure.ServiceMemberJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sixth.sixseminar.domain.Category;
import org.sixth.sixseminar.domain.Member;
import org.sixth.sixseminar.domain.Post;
import org.sixth.sixseminar.infrastructure.MemberJpaRepository;
import org.sixth.sixseminar.infrastructure.PostJpaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostJpaRepository postJpaRepository;
	private final ServiceMemberJpaRepository serviceMemberJpaRepository;
	private final CategoryService categoryService;
	// @Transactional
	// public String create(PostCreateRequest request, Long memberId) {
	// 	ServiceMember member = serviceMemberJpaRepository.findByIdOrThrow(memberId);
	// 	Post post = postJpaRepository.save(
	// 		Post.builder()
	// 			.serviceMember(member)
	// 			.title(request.title())
	// 			.content(request.content()).build());
	//
	// 	return post.toString();
	// }


	@Transactional
	public void editContent(Long postId, PostUpdateRequest request) {
		Post post = postJpaRepository.findById(postId)
			.orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
		post.updateContent(request.content());
	}

	@Transactional
	public void deleteById(Long postId) {
		Post post = postJpaRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
		postJpaRepository.delete(post);
	}
	public List<PostGetResponse> getPosts(Long memberId) {
		return postJpaRepository.findAllByServiceMemberId(memberId)
			.stream()
			.map(post -> PostGetResponse.of(post, getCategoryByPost(post)))
			.toList();
	}

	public PostGetResponse getById(Long postId) {
		Post post = postJpaRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
		return PostGetResponse.of(post,getCategoryByPost(post));
	}

	private Category getCategoryByPost(Post post) {
		return categoryService.getByCategoryId(post.getCategoryId());
	}
}
