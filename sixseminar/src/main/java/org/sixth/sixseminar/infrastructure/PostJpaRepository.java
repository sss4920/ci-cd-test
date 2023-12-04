package org.sixth.sixseminar.infrastructure;

import java.util.List;

import org.sixth.sixseminar.domain.ServiceMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.sixth.sixseminar.domain.Member;
import org.sixth.sixseminar.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByServiceMemberId(Long serviceMemberId);
	List<Post> findAllByServiceMember(ServiceMember serviceMember);
}
