package org.sixth.sixseminar.controller.dto.response;

import org.sixth.sixseminar.domain.Member;
import org.sixth.sixseminar.domain.SOPT;

public record MemberResponse(
	String name,
	String nickname,
	int age,
	SOPT soptInfo
) {
	public static MemberResponse of(Member member) {
		return new MemberResponse(
			member.getName(),
			member.getNickname(),
			member.getAge(),
			member.getSopt()
		);
	}
}