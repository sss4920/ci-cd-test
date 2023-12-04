package org.sixth.sixseminar.controller.dto.response;

import org.sixth.sixseminar.domain.Category;
import org.sixth.sixseminar.domain.Post;

public record PostGetResponse(
	String title,
	String content,
	String category
) {
	public static PostGetResponse of(Post post, Category category) {
		return new PostGetResponse(
			post.getTitle(),
			post.getContent(),
			category.getContent()
		);
	}
}
