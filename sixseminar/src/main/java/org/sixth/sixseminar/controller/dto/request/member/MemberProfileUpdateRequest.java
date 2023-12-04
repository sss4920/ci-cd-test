package org.sixth.sixseminar.controller.dto.request.member;

import org.sixth.sixseminar.domain.Part;

public record MemberProfileUpdateRequest(int generation, Part part) {
}
