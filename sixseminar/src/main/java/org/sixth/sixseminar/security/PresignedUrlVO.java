package org.sixth.sixseminar.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresignedUrlVO {
	private String fileName;
	private String url;

	public static PresignedUrlVO of(String fileName, String url){
		return new PresignedUrlVO(fileName, url);
	}
}
