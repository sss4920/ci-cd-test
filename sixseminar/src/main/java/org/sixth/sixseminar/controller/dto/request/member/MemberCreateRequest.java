package org.sixth.sixseminar.controller.dto.request.member;


import org.sixth.sixseminar.domain.SOPT;

public record MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
}