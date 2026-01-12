package cl.bice.banca.empresas.servicio.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase usada para encodear en base 64.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public final class Base64Coder {

	// Mapping table from 6-bit nibbles to Base64 characters.
	private static char[] map1 = new char[64];

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Base64Coder.class);

	static {
		int i = 0;
		for (char c = 'A'; c <= 'Z'; c++) {
			map1[i++] = c;
		}
		for (char c = 'a'; c <= 'z'; c++) {
			map1[i++] = c;
		}
		for (char c = '0'; c <= '9'; c++) {
			map1[i++] = c;
		}
		map1[i++] = '+';
		map1[i++] = '/';
	}

	// Mapping table from Base64 characters to 6-bit nibbles.
	private static byte[] map2 = new byte[128];
	static {
		for (int i = 0; i < map2.length; i++) {
			map2[i] = -1;
		}
		for (int i = 0; i < 64; i++) {
			map2[map1[i]] = (byte) i;
		}
	}

	/**
	 * Encodes a string into Base64 format. No blanks or line breaks are inserted.
	 * 
	 * @param s a String to be encoded.
	 * @return A String with the Base64 encoded data.
	 */
	public static String encodeString(final String s) {
		return new String(encode(s.getBytes()));
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param bytes an array containing the data bytes to be encoded.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(final byte[] bytes) {
		return encode(bytes, bytes.length);
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param bytes an array containing the data bytes to be encoded.
	 * @param iLen  number of bytes to process in <code>in</code>.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(final byte[] bytes, final int iLen) {
		int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
		int oLen = ((iLen + 2) / 3) * 4; // output length including padding
		char[] out = new char[oLen];
		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			int i0 = bytes[ip++] & 0xff;
			int i1 = ip < iLen ? bytes[ip++] & 0xff : 0;
			int i2 = ip < iLen ? bytes[ip++] & 0xff : 0;
			int o0 = i0 >>> 2;
			int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
			int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
			int o3 = i2 & 0x3F;
			out[op++] = map1[o0];
			out[op++] = map1[o1];
			out[op] = op < oDataLen ? map1[o2] : '=';
			op++;
			out[op] = op < oDataLen ? map1[o3] : '=';
			op++;
		}
		return out;
	}

	/**
	 * Decodes a string from Base64 format.
	 * 
	 * @param s a Base64 String to be decoded.
	 * @return A String containing the decoded data.
	 * @throws IllegalArgumentException if the input is not valid Base64 encoded
	 *                                  data.
	 */
	public static String decodeString(final String s) {
		byte[] respuesta = decode(s);
		try {
			return new String(respuesta, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error al encoding", e);
			return new String(respuesta);
		}
	}

	/**
	 * Decodes a byte array from Base64 format.
	 * 
	 * @param s a Base64 String to be decoded.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException if the input is not valid Base64 encoded
	 *                                  data.
	 */
	public static byte[] decode(final String s) {
		return decode(s.toCharArray());
	}

	/**
	 * Decodes a byte array from Base64 format. No blanks or line breaks are allowed
	 * within the Base64 encoded data.
	 * 
	 * @param chars a character array containing the Base64 encoded data.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException if the input is not valid Base64 encoded
	 *                                  data.
	 */
	public static byte[] decode(final char[] chars) {
		int iLen = chars.length;
		if (iLen % 4 != 0) {
			throw new IllegalArgumentException("Length of Base64 encoded input string is not" + " a multiple of 4.");
		}
		while (iLen > 0 && chars[iLen - 1] == '=') {
			iLen--;
		}
		int oLen = (iLen * 3) / 4;
		byte[] out = new byte[oLen];
		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			int i0 = chars[ip++];
			int i1 = chars[ip++];
			int i2 = ip < iLen ? chars[ip++] : 'A';
			int i3 = ip < iLen ? chars[ip++] : 'A';
			if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127) {
				throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
			}
			int b0 = map2[i0];
			int b1 = map2[i1];
			int b2 = map2[i2];
			int b3 = map2[i3];
			if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
				throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
			}
			int o0 = (b0 << 2) | (b1 >>> 4);
			int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
			int o2 = ((b2 & 3) << 6) | b3;
			out[op++] = (byte) o0;
			if (op < oLen) {
				out[op++] = (byte) o1;
			}
			if (op < oLen) {
				out[op++] = (byte) o2;
			}
		}
		return out;
	}

	/**
	 * Constructir privado.
	 *
	 */
	private Base64Coder() {

	}

}
