package org.sixth.sixseminar.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ServiceMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickname;
	private String password;

	private String accessToken;
	@OneToMany(mappedBy = "serviceMember", cascade = CascadeType.ALL) //cascade 맥일려면 양방향을 맺어줘야한다.
	private final List<Post> posts = new ArrayList<>();
	@Builder
	public ServiceMember(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	public void updateAccessToken(String accessToken){
		this.accessToken=accessToken;
	}
}
