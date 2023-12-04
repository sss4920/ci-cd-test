package org.sixth.sixseminar.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.sixth.sixseminar.controller.dto.request.post.PostCreateRequest;
import org.sixth.sixseminar.domain.Post;
import org.sixth.sixseminar.domain.ServiceMember;
import org.sixth.sixseminar.infrastructure.PostJpaRepository;
import org.sixth.sixseminar.infrastructure.ServiceMemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class PostServiceV2Test {


	@Autowired
	private ServiceMemberJpaRepository serviceMemberJpaRepository;

	@Autowired
	private PostJpaRepository postJpaRepository;


	@Transactional
	@Test
	public void createV2(PostCreateRequest request, MultipartFile image, Long memberId) {

		final String imageUrl = "posts/84e7d57b-798b-4501-ab56-42fc36d42e28.jpg";
		System.out.println(imageUrl);
		ServiceMember member = serviceMemberJpaRepository.findByIdOrThrow(memberId);
		Post post = postJpaRepository.save(
			Post.builderWithImageUrl()
				.title(request.title())
				.content(request.content())
				.imageUrl(imageUrl)
				.serviceMember(member)
				.build());
		assertEquals(imageUrl, "posts/84e7d57b-798b-4501-ab56-42fc36d42e28.jpg");
	}

}