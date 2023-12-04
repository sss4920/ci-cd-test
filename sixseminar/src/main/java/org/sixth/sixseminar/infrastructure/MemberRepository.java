package org.sixth.sixseminar.infrastructure;

import org.sixth.sixseminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}