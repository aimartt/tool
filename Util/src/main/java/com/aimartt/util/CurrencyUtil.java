package com.aimartt.util;

import java.math.BigDecimal;

/**
 * 货币工具类。
 * @author aimartt
 * @date 2013-04-07
 * @category 货币工具类
 */
public class CurrencyUtil {

	private static final String HAN_DIGI_STR[] = {
		"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"
	};     
	
	private static final String HAN_DIVI_STR[] = {
		"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", 
		"佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", 
		"万", "拾", "佰", "仟"
	};

	/**
	 * 获取以￥开头的大写金额字符串。
	 * @param val
	 * @return
	 * @category 获取以￥开头的大写金额字符串
	 */
	public static String numToRMBStr(BigDecimal val) {
		return numToRMBStr(val.doubleValue());
	}

	/**
	 * 获取以￥开头的大写金额字符串。
	 * @param val
	 * @return
	 * @category 获取以￥开头的大写金额字符串
	 */
	public static String numToRMBStr(double val) {
		String signStr = "";
		String tailStr = "";
		if (val < 0.0D) {
			val = -val;
			signStr = "负";
		}
		if (val > 100000000000000D || val < -100000000000000D) {
			return "数值位数过大!";
		}
		long temp = Math.round(val * 100D);
		long integer = temp / 100L;
		long fraction = temp % 100L;
		int jiao = (int) fraction / 10;
		int fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			tailStr = "整";
		} else {
			tailStr = HAN_DIGI_STR[jiao];
			if (jiao != 0) {
				tailStr = tailStr + "角";
			}
			if (integer == 0L && jiao == 0) {
				tailStr = "";
			}
			if (fen != 0) {
				tailStr = tailStr + HAN_DIGI_STR[fen] + "分";
			}
		}
		return "￥" + signStr + numToAmountInWords(String.valueOf(integer)) + "元" + tailStr;
	}
	
	/**
	 * 数字转换成大写汉字。
	 * @param numStr 整形字符串
	 * @return
	 * @category 数字转换成大写汉字
	 */
	public static String numToAmountInWords(String numStr) {
		String rmbStr = "";
		boolean lastzero = false;
		boolean hasvalue = false;
		int len = numStr.length();
		if (len > 15) {
			return "数值过大!";
		}
		for (int i = len - 1; i >= 0; i--) {
			if (numStr.charAt(len - i - 1) != ' ') {
				int n = numStr.charAt(len - i - 1) - 48;
				if (n < 0 || n > 9) {
					return "输入含非数字字符!";
				}
				if (n != 0) {
					if (lastzero) {
						rmbStr = rmbStr + HAN_DIGI_STR[0];
					}
					if (n != 1 || i % 4 != 1 || i != len - 1) {
						rmbStr = rmbStr + HAN_DIGI_STR[n];
					}
					rmbStr = rmbStr + HAN_DIVI_STR[i];
					hasvalue = true;
				} else {
					if (i % 8 == 0 || i % 8 == 4 && hasvalue) {
						rmbStr = rmbStr + HAN_DIVI_STR[i];
					}
				}
				if (i % 8 == 0) {
					hasvalue = false;
				}
				lastzero = n == 0 && i % 4 != 0;
			}
		}	
		if (rmbStr.length() == 0) {
			return HAN_DIGI_STR[0];
		} else {
			return rmbStr;
		}
	}
	
	private CurrencyUtil() {
	}
	
	public static void main(String args[]) {
		System.out.println(-100000000000005D + numToRMBStr(-100000000000005D));
		System.out.println(100000000000000D + numToRMBStr(100000000000000D));
		System.out.println(100054565800.06D + numToRMBStr(100054565800.06D));
		System.out.println(10008767080L + numToAmountInWords("10008767080"));
		System.out.println(0.029999999999999999D + numToRMBStr(0.029999999999999999D));
	}

}
