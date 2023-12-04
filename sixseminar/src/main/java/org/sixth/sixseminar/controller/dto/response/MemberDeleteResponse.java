package org.sixth.sixseminar.controller.dto.response;

public record MemberDeleteResponse(Long memberId) {
	public static MemberDeleteResponse of(Long memberId){
		return new MemberDeleteResponse(memberId);
	}
}
