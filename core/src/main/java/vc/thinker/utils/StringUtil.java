package vc.thinker.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

public class StringUtil {

	/**
	 * 判断�?��字符串Str是否为空 return true if it is supplied with an empty, zero
	 * length, or whitespace-only string. documented
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	/**
	 * 判断�?��字符串Str是否为null return true if it is supplied with an null, or
	 * whitespace-only string. documented
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullString(String str) {
		return (str == null);
	}

	/**
	 * 将String数组转换成Integer数组
	 * 
	 * @param s
	 * @return
	 */
	public static Integer[] convertToIntegerArray(String[] s) {
		Integer[] num = new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			num[i] = new Integer(s[i]);
		}
		return num;
	}

	/**
	 * 将字符串数组转换成字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String arrayToString(String[] str) {
		if (str == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sb.append(str[i]);
			sb.append(", ");
		}
		return sb.toString();
	}

	// 判断字符串是否存在于指定的字符串数组�?
	public static boolean isExist(String str, String[] array) {
		boolean result = false;
		if (array == null)
			return result;

		for (int i = 0; i < array.length; i++) {
			if (str.equals(array[i]))
				result = true;
		}
		return result;
	}

	/**
	 * 右对齐填充字�?
	 * 
	 * @param data
	 * @param length
	 * @param fill
	 * @return
	 */
	public static String rightAlign(String data, int length, String fill) {
		for (int i = data.length(); i < length; i++) {
			data = fill + data;
		}
		return data;
	}

	/**
	 * 左对齐填充字�?
	 * 
	 * @param data
	 * @param length
	 * @param fill
	 * @return
	 */
	public static String leftAlign(String data, int length, String fill) {
		for (int i = data.length(); i < length; i++) {
			data = data + fill;
		}
		return data;
	}

	public static String getBASE64(String s) {
		if (s == null) {
			return null;
		}
		try {
			return (new sun.misc.BASE64Encoder()).encode(s.getBytes("UTF-8"));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 解码方法, 把一个BASE64编码的字符串解码为编码前的字符串
	 * 
	 * @param in
	 *            �?��解码的BASE64编码的字符串
	 * @return 解码后的字节数组, 若解码失�?该字符串不是BASE64编码)则返回null
	 */
	public static String decode(String in) {
		String s = null;
		try {
			byte[] arrB = new BASE64Decoder().decodeBuffer(in);
			s = new String(arrB, "UTF-8");
		} catch (Exception ex) {
			s = null;
		}
		return s;
	}

	/**
	 * MD5转换
	 * 
	 * @param plainText
	 * 
	 * @return MD5字符�?
	 */
	public static String toMD5(String plainText)
			throws NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainText.getBytes());
		byte by[] = messageDigest.digest();

		StringBuffer buf = new StringBuffer();
		int val;
		for (int i = 0; i < by.length; i++) {
			val = by[i];
			if (val < 0) {
				val += 256;
			} else if (val < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(val));
		}
		return buf.toString();
	}

	public static String substituteParams(String msgtext, Object params[]) {
		if (params == null || msgtext == null) {
			return msgtext;
		}
		MessageFormat mf = new MessageFormat(msgtext);
		return mf.format(params);
	}

	/**
	 * �?��身份证是 否合�?15位或18�?
	 * 
	 * @param value
	 * @return
	 */
	public static boolean checkIDCard(String value) {
		/*
		 * Pattern regex = Pattern.compile(check); Matcher matcher =
		 * regex.matcher(value); boolean isMatched = matcher.matches(); return
		 * isMatched;
		 */
		return value.matches("\\d{15}|\\d{18}");
	}

	/**
	 * �?��手机输入 是否正确
	 * 
	 * @param value
	 * @return
	 */
	public static boolean checkMobile(String value) {
		if ("".equals(value)) {
			return true;
		} else {
			return value.matches("^[1][3,5,8]+\\d{9}");
		}
	}

	/**
	 * 是否是数�?
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		str = StringUtil.nullStringToEmptyString(str);
		String regex = "\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean validate = m.matches();
		return validate;
	}

	/**
	 * �?null"字符串或者null值转换成""
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStringToEmptyString(String str) {
		if (str == null) {
			str = "";
		}
		if (str.equals("null")) {
			str = "";
		}
		return str;
	}

	/**
	 * �?��整数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整�?"-0":非正整数 "-":负整�?"":整数
	 * @return
	 */
	public static boolean checkNumber(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+$";// 非负整数
		else if (type.equals("+"))
			eL = "^\\d*[1-9]\\d*$";// 正整�?
		else if (type.equals("-0"))
			eL = "^((-\\d+)|(0+))$";// 非正整数
		else if (type.equals("-"))
			eL = "^-\\d*[1-9]\\d*$";// 负整�?
		else
			eL = "^-?\\d+$";// 整数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	/**
	 * @param account
	 * @return �?��是否是注册的账号 () var reg =
	 *         /^[a-zA-Z][a-zA-Z0-9_]*$/;用户名字母开�?允许字母数字下划�?
	 */
	public static boolean checkAccout(String account) {
		String check = "^[a-zA-Z][a-zA-Z0-9_]{5,15}$";
		Pattern p = Pattern.compile(check);
		Matcher m = p.matcher(account);
		boolean b = m.matches();
		return b;
	}

	public static void main(String[] args) {
		System.out.println(checkAccout("jjfhff"));
	}
}
