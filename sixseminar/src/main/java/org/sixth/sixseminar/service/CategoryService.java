package org.sixth.sixseminar.service;

import org.sixth.sixseminar.domain.CategoryId;
import org.sixth.sixseminar.controller.dto.response.CategoryResponse;
import org.sixth.sixseminar.infrastructure.CategoryJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sixth.sixseminar.domain.Category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryJpaRepository categoryJpaRepository;

	public Category getByCategoryId(CategoryId categoryId) {
		return categoryJpaRepository.findById(Short.valueOf(categoryId.getCategoryId())).orElseThrow(
			() -> new EntityNotFoundException("해당하는 카테고리가 없습니다."));
	}

	public CategoryResponse getById(Short id) {
		return CategoryResponse.of(categoryJpaRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("해당하는 카테고리가 없습니다.")));
	}
}
