package org.sixth.sixseminar.controller.dto.response;

import org.sixth.sixseminar.domain.Category;

public record CategoryResponse(
	String content
) {
	public static CategoryResponse of(Category category){return new CategoryResponse(category.getContent());}
}
