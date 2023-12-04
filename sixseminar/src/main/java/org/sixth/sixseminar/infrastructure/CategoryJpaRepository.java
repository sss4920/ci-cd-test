package org.sixth.sixseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sixth.sixseminar.domain.Category;

public interface CategoryJpaRepository extends JpaRepository<Category, Short> {
}
