package org.sixth.sixseminar.domain;

import static jakarta.persistence.EnumType.*;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SOPT {
	private int generation;

	@Enumerated(value = STRING)
	private Part part;
}
