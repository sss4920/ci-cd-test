package org.sixth.sixseminar.service;

import java.io.IOException;

import org.sixth.sixseminar.domain.Member;
import org.sixth.sixseminar.domain.Post;
import org.sixth.sixseminar.controller.dto.request.post.PostCreateRequest;
import org.sixth.sixseminar.domain.ServiceMember;
import org.sixth.sixseminar.exception.Error;
import org.sixth.sixseminar.exception.model.CustomException;
import org.sixth.sixseminar.external.S3Service;
import org.sixth.sixseminar.infrastructure.MemberJpaRepository;
import org.sixth.sixseminar.infrastructure.PostJpaRepository;
import org.sixth.sixseminar.infrastructure.ServiceMemberJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

	private static final String POST_IMAGE_FOLDER_NAME = "posts/";

	private final ServiceMemberJpaRepository serviceMemberJpaRepository;
	private final PostJpaRepository postJpaRepository;
	private final S3Service s3Service;

	@Transactional
	public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
		try {
			final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
			System.out.println(imageUrl);
			ServiceMember member = serviceMemberJpaRepository.findByIdOrThrow(memberId);
			Post post = postJpaRepository.save(
				Post.builderWithImageUrl()
					.title(request.title())
					.content(request.content())
					.imageUrl(imageUrl)
					.serviceMember(member)
					.build());
			return post.getId().toString();
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public void deleteByIdV2(Long postId) {
		try {
			Post post = postJpaRepository.findById(postId)
				.orElseThrow(() -> new CustomException(Error.POST_NOT_FOUND, Error.POST_NOT_FOUND.getMessage()));
			s3Service.deleteImage(post.getImageUrl());
			postJpaRepository.deleteById(postId);
		} catch (IOException | RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
