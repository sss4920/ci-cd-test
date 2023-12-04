package org.sixth.sixseminar.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String nickname;
	private int age;

	@Embedded
	@Setter
	private SOPT sopt;

	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) //cascade 맥일려면 양방향을 맺어줘야한다.
	// private final List<Post> posts = new ArrayList<>();
	@Builder
	public Member(String name, String nickname, int age, SOPT sopt) {
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.sopt = sopt;
	}


}
