package org.sixth.sixseminar.controller;

import java.util.List;

import org.sixth.sixseminar.common.ApiResponse;
import org.sixth.sixseminar.controller.dto.request.member.MemberCreateRequest;
import org.sixth.sixseminar.controller.dto.request.member.MemberProfileUpdateRequest;
import org.sixth.sixseminar.controller.dto.response.MemberDeleteResponse;
import org.sixth.sixseminar.controller.dto.response.MemberResponse;
import org.sixth.sixseminar.exception.Success;
import org.sixth.sixseminar.service.MemberService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/{memberId}")
	public ApiResponse<MemberResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
		return ApiResponse.success(Success.GET_MEMBER_SUCCESS,memberService.getById(memberId)); //->membergetresponse 뱉음.
	}

	@PostMapping
	public ApiResponse<MemberResponse> createMember(@RequestBody MemberCreateRequest request) {
		// URI location = URI.create(memberService.create(request));
		return ApiResponse.success(Success.CREATE_MEMBER_SUCCESS, memberService.create(request));
	}

	// 목록 조회
	@GetMapping
	public ApiResponse<List<MemberResponse>> getMembersProfile() {
		return ApiResponse.success(Success.GET_MEMBERS_SUCCESS, memberService.getMembers());
	}

	// 수정
	@PatchMapping("/{memberId}")
	public ApiResponse updateMemberSoptInfo(@PathVariable Long memberId,
		@RequestBody MemberProfileUpdateRequest request) {
		memberService.updateSOPT(memberId, request);
		return ApiResponse.success(Success.UPDATE_MEMBER_SUCCESS);
	}

	// 삭제
	@DeleteMapping("/{memberId}")
	public ApiResponse<MemberDeleteResponse> deleteMember(@PathVariable Long memberId) {
		return ApiResponse.success(Success.DELETE_MEMBER_SUCCESS, memberService.deleteMember(memberId));
	}
}
