package org.sixth.sixseminar.infrastructure;

import java.util.Optional;

import org.sixth.sixseminar.domain.Member;
import org.sixth.sixseminar.domain.ServiceMember;
import org.sixth.sixseminar.exception.Error;
import org.sixth.sixseminar.exception.model.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMemberJpaRepository extends JpaRepository<ServiceMember, Long> {
	Optional<ServiceMember> findByNickname(String nickname);

	default ServiceMember findByIdOrThrow(Long id) {
		return findById(id).orElseThrow(
			() -> new NotFoundException(Error.USER_NOT_FOUND, Error.USER_NOT_FOUND.getMessage()));
	}
}
