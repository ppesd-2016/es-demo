package io.codearte.jfairy.producer.person.locale.en;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;

import java.util.List;

import static java.lang.String.valueOf;
import static java.lang.System.arraycopy;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * @author Olga Maciaszek-Sharma
 * @since 15.03.15
 */
public class SocialSecurityCardNumberProvider implements NationalIdentityCardNumberProvider, VATIdentificationNumberProvider {

	private static final int SSN_LENGTH = 11;
	private static final int AREA_NUMBER_LENGTH = 3;
	private static final int GROUP_NUMBER_LENGTH = 2;
	private static final int GROUP_NUMBER_INDEX = 4;
	private static final List<Integer> HYPHEN_INDEXES = Lists.newArrayList(3, 6);
	private static final int SERIAL_NUMBER_LENGTH = 4;
	private static final int SERIAL_NUMBER_INDEX = 7;

	private final BaseProducer baseProducer;

	@Inject
	public SocialSecurityCardNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		char[] ssn = new char[SSN_LENGTH];

		fillHyphens(ssn);

		fillAreaNumber(ssn);

		fillGroupNumber(ssn);

		fillSerialNumber(ssn);

		return valueOf(ssn);
	}

	private void fillHyphens(char[] ssn) {
		for (Integer index : HYPHEN_INDEXES) {
			ssn[index] = '-';
		}
	}

	private void fillAreaNumber(char[] ssn) {
		String number;
		do {
			number = valueOf(baseProducer.randomBetween(1, 899));
		} while (number.equals("666"));
		char[] digits = leftPad(number, AREA_NUMBER_LENGTH, "0").toCharArray();
		arraycopy(digits, 0, ssn, 0, digits.length);
	}

	private void fillGroupNumber(char[] ssn) {
		String number = valueOf(baseProducer.randomBetween(1, 99));
		char[] digits = leftPad(number, GROUP_NUMBER_LENGTH, "0").toCharArray();
		arraycopy(digits, 0, ssn, GROUP_NUMBER_INDEX, digits.length);
	}

	private void fillSerialNumber(char[] ssn) {
		String number = valueOf(baseProducer.randomBetween(1, 9999));
		char[] digits = leftPad(number, SERIAL_NUMBER_LENGTH, "0").toCharArray();
		arraycopy(digits, 0, ssn, SERIAL_NUMBER_INDEX, digits.length);
	}

}

