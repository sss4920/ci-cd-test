package org.sixth.sixseminar.exception.model;

import org.sixth.sixseminar.exception.Error;

public class NotFoundException extends CustomException {
	public NotFoundException(Error error, String message) {
		super(error, message);
	}
}
