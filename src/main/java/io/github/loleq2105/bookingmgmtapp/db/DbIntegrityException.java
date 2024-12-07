package io.github.loleq2105.bookingmgmtapp.db;

public class DbIntegrityException extends RuntimeException {
	public DbIntegrityException(String msg) {
		 super(msg);
	}
}
