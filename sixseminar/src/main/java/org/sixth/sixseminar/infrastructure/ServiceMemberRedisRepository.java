package org.sixth.sixseminar.infrastructure;

import org.sixth.sixseminar.domain.ServiceMember;
import org.springframework.data.repository.CrudRepository;

public interface ServiceMemberRedisRepository extends CrudRepository<ServiceMember, String> {
}
