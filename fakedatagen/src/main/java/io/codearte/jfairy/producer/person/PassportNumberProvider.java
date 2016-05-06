package io.codearte.jfairy.producer.person;

import com.google.inject.Provider;

/**
 * @author Olga Maciaszek-Sharma
 * @since 21.02.15
 */
public interface PassportNumberProvider extends Provider<String> {

	@Override
	String get();

}
