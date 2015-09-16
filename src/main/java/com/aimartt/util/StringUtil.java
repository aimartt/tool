/**
 * 
 */
package com.aimartt.util;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类。
 * @author aimartt
 * @date 2013-05-02
 * @category 字符串工具类
 */
public class StringUtil {
	
    // 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
    private static int BEGIN = 45217;
    private static int END = 63486;

    // 按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
    // i, u, v都不做声母, 自定规则跟随前面的字母
    private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
            '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
            '塌', '挖', '昔', '压', '匝', };

    // 二十六个字母区间对应二十七个端点
    // GB2312码汉字区间十进制表示
    private static int[] table = new int[27];

    // 对应首字母区间表
    private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            't', 't', 'w', 'x', 'y', 'z', };

    //初始化
    static {
    	for (int i = 0; i < 26; i++) {
    		// 得到GB2312码的首字母区间端点表，十进制。
    		table[i] = gbValue(chartable[i]);
    	}
    	table[26] = END;// 区间表结尾
    }
    

    /**
	 * 获取指定长度的字符串，中文字符算两个字长度。<br />
	 * 当字符串长度大于指定长度时，在返回结果后加上省略号（...）
	 * @param str
	 * @param length 指定的长度
	 * @return
	 * @category 获取指定长度的字符串，中文字符算两个字长度
	 */
	public static String abbreviate(String str, int length) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		str = str.trim();
		if (getLength(str) <= length) {
			//当字符串长度不大于指定长度时，不进行截取
			return str;
		}
		StringBuilder sb = new StringBuilder();
		char[] cc = str.toCharArray();
		//定义已占长度，与指定长度比较
		int alreadyLen = 0;
		for (int i = 0; i < cc.length; i++) {
			sb.append(cc[i]);
			//中文占两个长度，其他字符占一个
			alreadyLen += (isChinese(cc[i]) ? 2 : 1);
			if (alreadyLen >= length) {
				break;
			}
		}
		//尾部拼上省略号
		sb.append("...");
		return sb.toString();
	}

	/**
     * 获取中文的首字母。
     * @param cnstr
     * @return
     * @category 获取中文的首字母
     */
    public static String cn2Pinyin(String cnstr) {
        String result = "";
        int length = cnstr.length();
        try {
            for (int i = 0; i < length; i++) {
            	result += char2Initial(cnstr.charAt(i));
            }
        } catch (Exception e) {
        	result = "";
        }
        return result;
    }
	
	/**
	 * 获取字符串的长度，中文字符算两个长度。
	 * @param str
	 * @return <tt>str</tt> 为 <tt>null</tt> 或空字符串时返回 0
	 * @category 获取字符串的长度，中文字符算两个长度
	 */
	public static int getLength(String str) {
		if (StringUtils.isEmpty(str)) {
			return 0;
		}
		char[] cc = str.toCharArray();
		int intLen = cc.length;
		for (int i = 0; i < cc.length; i++) {
			if (isChinese(cc[i])) {
				//当字符为中文时，长度加一
				intLen++;
			}
		}
		return intLen;
	}
	
	/**
     * 将字符串中的 HTML 元素替换为对应的编码。
     * @param str
     * @return
     * @category 将字符串中的 HTML 元素替换为对应的编码
     */
    public static String htmlEncode(String str) {
        StringBuffer sb = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            switch(c) {
                case 60: 
                	sb.append("&lt;"); 
                	break;
                case 62: 
                	sb.append("&gt;"); 
                	break;
                case 38: 
                	sb.append("&amp;"); 
                	break;
                case 34: 
                	sb.append("&quot;"); 
                	break;
                case 169: 
                	sb.append("&copy;"); 
                	break;
                case 174: 
                	sb.append("&reg;"); 
                	break;
                case 165: 
                	sb.append("&yen;"); 
                	break;
                case 8364: 
                	sb.append("&euro;"); 
                	break;
                case 8482: 
                	sb.append("&#153;"); 
                	break;
                case 13:
                	if (i < length - 1 && str.charAt(i + 1) == 10) {
                		sb.append("<br />");
                		i++;
                	}
                	break;
                case 32:
                	if (i < length - 1 && str.charAt(i + 1) == ' ') {
                		sb.append("&nbsp;");
                		i++;
                		break;
                    }
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

	/**
	 * 判断字符是否是中文。
	 * @param c
	 * @return <tt>c</tt> 为中文时返回 <tt>true</tt>，否则返回 <tt>false</tt>
	 * @category 判断字符是否是中文
	 */
	public static boolean isChinese(char c) {  
		UnicodeBlock ub = UnicodeBlock.of(c);  
		if (ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 
				|| ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS 
				|| ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A 
				|| ub == UnicodeBlock.GENERAL_PUNCTUATION 
				|| ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION 
				|| ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
			return true;  
		}  
		return false;  
	}  
	
    /**
     * 按 UTF-8 编码进行 URL 编码。
     * <ul>
     * <li>若 <tt>url</tt> 为 <tt>null</tt> 或空字符串，或者编码时发生异常，均返回 <tt>url</tt> 本身。</li>
     * </ul>
     * @param url
     * @return
     * @category 按 UTF8 编码进行 URL 编码
     */
    public static String urlEncode(String url){
    	if (StringUtils.isEmpty(url)) {
        	return url;
        }
    	try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return url;
		}
    }

    /**
     * 输入字符，得到他的声母，英文字母返回对应的大写字母，其他非简体汉字返回 '0'。
     * @param ch
     * @return
     */
    private static char char2Initial(char ch) {
        // 对英文字母的处理：小写字母转换为大写，大写的直接返回
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }
        // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
        // 若不是，则直接返回。
        // 若是，则在码表内的进行判断。
        int gb = gbValue(ch);// 汉字转换首字母
        if (gb < BEGIN || gb > END) {
        	// 在码表区间之外，直接返回
            return ch;
        }
        int i;
        for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
            if (gb >= table[i] && gb < table[i+1]) {
                break;
            }
        }
        if (gb == END) {//补上GB2312区间最右端
            i = 25;
        }
        return initialtable[i]; // 在码表区间中，返回首字母
    }

    /**
     * 将一个汉字（GB2312）转换为十进制表示。
     * @param ch
     * @return
     */
    private static int gbValue(char ch) {
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private StringUtil() {
	}

}