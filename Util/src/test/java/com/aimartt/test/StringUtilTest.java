package com.aimartt.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.aimartt.util.StringUtil;

/**
 * 字符串工具测试类。
 * @author aimartt
 * @date 2014-12-29
 * @category 字符串工具测试类
 */
public class StringUtilTest {
	
	@Test
	public void abbreviate() {
		//abbreviate(String str, int length)
		assertThat(StringUtil.abbreviate("希尔施斯泰因", 6), is("希尔施..."));
		assertThat(StringUtil.abbreviate("梅斯塔利亚ab", 11), is("梅斯塔利亚a..."));
		
		//abbreviate(String str, int offset, int maxWidth)
		assertThat(StringUtils.abbreviate("abcdefghijklmno", -1, 10), is("abcdefg..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 0, 10), is("abcdefg..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 1, 10), is("abcdefg..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 4, 10), is("abcdefg..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 5, 10), is("...fghi..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 6, 10), is("...ghij..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 7, 10), is("...hijk..."));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 8, 10), is("...ijklmno"));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 10, 10), is("...ijklmno"));
		assertThat(StringUtils.abbreviate("abcdefghijklmno", 12, 10), is("...ijklmno"));
	}
	
	@Test
	public void cn2Pinyin() {
		assertThat(StringUtil.cn2Pinyin("希尔施斯泰因"), is("xessty"));
		assertThat(StringUtil.cn2Pinyin("伯纳德·帕克"), is("bnd·pk"));
		assertThat(StringUtil.cn2Pinyin("梅斯塔利亚ab"), is("mstlyAB"));
	}
	
	@Test
	public void getSpecifyLengthStr() {
		String value = StringUtil.abbreviate("2009年初，他正式到欧洲著名球会贝尔格莱德红星效力。", 20);
		assertThat(value, is("2009年初，他正式到欧..."));
		assertThat(StringUtil.abbreviate("南非职业足球运动员", 20), is("南非职业足球运动员"));
	}
	
	@Test
	public void htmlEncode() {
		String value = StringUtil.htmlEncode("<div id=\"mw-page-base\" class=\"noprint\"></div>");
		assertThat(value, is("&lt;div id=&quot;mw-page-base&quot; class=&quot;noprint&quot;&gt;&lt;/div&gt;"));
	}
	
	@Test
	public void isChinese() {
		assertTrue(StringUtil.isChinese('澳'));
		assertFalse(StringUtil.isChinese('C'));
	}
	
	@Test
	public void urlEncode() {
		String value = StringUtil.urlEncode("http://www.qizhi.com/index.html");
		assertThat(value, is("http%3A%2F%2Fwww.qizhi.com%2Findex.html"));
	}
	
	//------------ Test case for org.apache.commons.lang3.StringUtilss ------------
	@Test
	public void isEmpty() {
		//true
		assertTrue(StringUtils.isEmpty(null));	
		assertTrue(StringUtils.isEmpty(""));	
		//false
		assertFalse(StringUtils.isEmpty(" "));
		assertFalse(StringUtils.isEmpty("bob"));
		assertFalse(StringUtils.isEmpty("  bob  "));
	}
	
	@Test
	public void isNotEmpty() {
		//false
		assertFalse(StringUtils.isNotEmpty(null));
		assertFalse(StringUtils.isNotEmpty(""));
		//true
		assertTrue(StringUtils.isNotEmpty(" "));
		assertTrue(StringUtils.isNotEmpty("bob"));
		assertTrue(StringUtils.isNotEmpty("  bob  "));
	}
	
	@Test
	public void isAnyEmpty() {
		//true
		assertTrue(StringUtils.isAnyEmpty((CharSequence[]) null));
		assertTrue(StringUtils.isAnyEmpty(null, "foo"));
		assertTrue(StringUtils.isAnyEmpty("", "bar"));
		assertTrue(StringUtils.isAnyEmpty("bob", ""));
		assertTrue(StringUtils.isAnyEmpty("  bob  ", null));
		//false
		assertFalse(StringUtils.isAnyEmpty(" ", "bar"));
		assertFalse(StringUtils.isAnyEmpty("for", "bar"));
	}
	
	@Test
	public void isNoneEmpty() {
		//false
		assertFalse(StringUtils.isNoneEmpty((CharSequence[]) null));
		assertFalse(StringUtils.isNoneEmpty(null, "foo"));
		assertFalse(StringUtils.isNoneEmpty("", "bar"));
		assertFalse(StringUtils.isNoneEmpty("bob", ""));
		assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
		//true
		assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
		assertTrue(StringUtils.isNoneEmpty("for", "bar"));
	}
	
	@Test
	public void isBlank() {
		//true
		assertTrue(StringUtils.isBlank(null));
		assertTrue(StringUtils.isBlank(""));
		assertTrue(StringUtils.isBlank(" "));
		//false
		assertFalse(StringUtils.isBlank("bob"));
		assertFalse(StringUtils.isBlank("  bob  "));
	}
	
	@Test
	public void isNotBlank() {
		//false
		assertFalse(StringUtils.isNotBlank(null));
		assertFalse(StringUtils.isNotBlank(""));
		assertFalse(StringUtils.isNotBlank(" "));
		//true
		assertTrue(StringUtils.isNotBlank("bob"));
		assertTrue(StringUtils.isNotBlank("  bob  "));
	}
	
	@Test
	public void isAnyBlank() {
		//true
		assertTrue(StringUtils.isAnyBlank((CharSequence[]) null));
		assertTrue(StringUtils.isAnyBlank(null, "foo"));
		assertTrue(StringUtils.isAnyBlank(null, null));
		assertTrue(StringUtils.isAnyBlank("", "bar"));
		assertTrue(StringUtils.isAnyBlank(" ", "bar"));
		assertTrue(StringUtils.isAnyBlank("bob", ""));
		assertTrue(StringUtils.isAnyBlank("  bob  ", null));
		//false
		assertFalse(StringUtils.isAnyBlank("for", "bar"));
	}
	
	@Test
	public void isNoneBlank() {
		//false
		assertFalse(StringUtils.isNoneBlank((CharSequence[]) null));
		assertFalse(StringUtils.isNoneBlank(null, "foo"));
		assertFalse(StringUtils.isNoneBlank(null, null));
		assertFalse(StringUtils.isNoneBlank("", "bar"));
		assertFalse(StringUtils.isNoneBlank(" ", "bar"));
		assertFalse(StringUtils.isNoneBlank("bob", ""));
		assertFalse(StringUtils.isNoneBlank("  bob  ", null));
		//true
		assertTrue(StringUtils.isNoneBlank("for", "bar"));
	}
	
	@Test
	public void trim() {
		assertNull(StringUtils.trim(null));	
		assertThat(StringUtils.trim(""), is(""));	
		assertThat(StringUtils.trim("    "), is(""));	
		assertThat(StringUtils.trim("abc"), is("abc"));	
		assertThat(StringUtils.trim("  abc  "), is("abc"));	
	}
	
	@Test
	public void trimToNull() {
		assertNull(StringUtils.trimToNull(null));	
		assertNull(StringUtils.trimToNull(""));	
		assertNull(StringUtils.trimToNull("    "));	
		assertThat(StringUtils.trimToNull("abc"), is("abc"));	
		assertThat(StringUtils.trimToNull("  abc  "), is("abc"));	
	}
	
	@Test
	public void trimToEmpty() {
		assertThat(StringUtils.trimToEmpty(null), is(""));	
		assertThat(StringUtils.trimToEmpty(""), is(""));	
		assertThat(StringUtils.trimToEmpty("    "), is(""));	
		assertThat(StringUtils.trimToEmpty("abc"), is("abc"));	
		assertThat(StringUtils.trimToEmpty("  abc  "), is("abc"));	
	}
	
	@Test
	public void strip() {
		//strip(String str)
		assertNull(StringUtils.strip(null));
		assertThat(StringUtils.strip(""), is(""));
		assertThat(StringUtils.strip("  "), is(""));
		assertThat(StringUtils.strip("abc"), is("abc"));
		assertThat(StringUtils.strip("  abc"), is("abc"));
		assertThat(StringUtils.strip("abc  "), is("abc"));
		assertThat(StringUtils.strip("  abc  "), is("abc"));
		assertThat(StringUtils.strip("  ab c  "), is("ab c"));
		
		//strip(String str, String stripChars)
		assertNull(StringUtils.strip(null, "anything"));
		assertThat(StringUtils.strip("", "anything"), is(""));
		assertThat(StringUtils.strip("abc", null), is("abc"));
		assertThat(StringUtils.strip("  abc", null), is("abc"));
		assertThat(StringUtils.strip("abc  ", null), is("abc"));
		assertThat(StringUtils.strip("  abc  ", null), is("abc"));
		assertThat(StringUtils.strip("  abcyx", "xyz"), is("  abc"));
	}
	
	@Test
	public void stripToNull() {
		assertNull(StringUtils.stripToNull(null));
		assertNull(StringUtils.stripToNull(""));
		assertNull(StringUtils.stripToNull("  "));
		assertThat(StringUtils.stripToNull("abc"), is("abc"));
		assertThat(StringUtils.stripToNull("  abc"), is("abc"));
		assertThat(StringUtils.stripToNull("abc  "), is("abc"));
		assertThat(StringUtils.stripToNull("  abc  "), is("abc"));
		assertThat(StringUtils.stripToNull("  ab c  "), is("ab c"));
	}
	
	@Test
	public void stripToEmpty() {
		assertThat(StringUtils.stripToEmpty(null), is(""));
		assertThat(StringUtils.stripToEmpty(""), is(""));
		assertThat(StringUtils.stripToEmpty("  "), is(""));
		assertThat(StringUtils.stripToEmpty("abc"), is("abc"));
		assertThat(StringUtils.stripToEmpty("  abc"), is("abc"));
		assertThat(StringUtils.stripToEmpty("abc  "), is("abc"));
		assertThat(StringUtils.stripToEmpty("  abc  "), is("abc"));
		assertThat(StringUtils.stripToEmpty("  ab c  "), is("ab c"));
	}
	
	@Test
	public void stripStart() {
		assertNull(StringUtils.stripStart(null, "anything"));
		assertThat(StringUtils.stripStart("", "anything"), is(""));
		assertThat(StringUtils.stripStart("abc", ""), is("abc"));
		assertThat(StringUtils.stripStart("abc", null), is("abc"));
		assertThat(StringUtils.stripStart("  abc", null), is("abc"));
		assertThat(StringUtils.stripStart("abc  ", null), is("abc  "));
		assertThat(StringUtils.stripStart("  abc  ", null), is("abc  "));
		assertThat(StringUtils.stripStart("yxabc  ", "xyz"), is("abc  "));
	}
	
	@Test
	public void stripEnd() {
		assertNull(StringUtils.stripEnd(null, "anything"));
		assertThat(StringUtils.stripEnd("", "anything"), is(""));
		assertThat(StringUtils.stripEnd("abc", ""), is("abc"));
		assertThat(StringUtils.stripEnd("abc", null), is("abc"));
		assertThat(StringUtils.stripEnd("  abc", null), is("  abc"));
		assertThat(StringUtils.stripEnd("abc  ", null), is("abc"));
		assertThat(StringUtils.stripEnd("  abc  ", null), is("  abc"));
		assertThat(StringUtils.stripEnd("  abcyx", "xyz"), is("  abc"));
		assertThat(StringUtils.stripEnd("120.00", ".0"), is("12"));
	}
	
	@Test
	public void stripAll() {
		//stripAll(String... strs)
		assertNull(StringUtils.stripAll((String[]) null));
		assertThat(StringUtils.stripAll(new String[]{}), equalTo(new String[]{}));
		assertThat(StringUtils.stripAll("abc", "  abc"), equalTo(new String[]{"abc", "abc"}));
		assertThat(StringUtils.stripAll("abc  ", null), equalTo(new String[]{"abc", null}));
		
		//stripAll(String[] strs, String stripChars)
		assertNull(StringUtils.stripAll((String[]) null, "anything"));
		assertThat(StringUtils.stripAll(new String[]{}, "anything"), equalTo(new String[]{}));
		assertThat(StringUtils.stripAll(new String[]{"abc", "  abc"}, null), equalTo(new String[]{"abc", "abc"}));
		assertThat(StringUtils.stripAll(new String[]{"abc  ", null}, null), equalTo(new String[]{"abc", null}));
		assertThat(StringUtils.stripAll(new String[]{"abc  ", null}, "yz"), equalTo(new String[]{"abc  ", null}));
		assertThat(StringUtils.stripAll(new String[]{"yabcz", null}, "yz"), equalTo(new String[]{"abc", null}));
	}
	
	@Test
	public void stripAccents() {
		assertNull(StringUtils.stripAccents(null));
		assertThat(StringUtils.stripAccents(""), is(""));
		assertThat(StringUtils.stripAccents("control"), is("control"));
		assertThat(StringUtils.stripAccents("éclair"), is("eclair"));
	}
	
	@Test
	public void equals() {
		//true
		assertTrue(StringUtils.equals(null, null));
		assertTrue(StringUtils.equals("abc", "abc"));
		//false
		assertFalse(StringUtils.equals(null, "abc"));
		assertFalse(StringUtils.equals("abc", null));
		assertFalse(StringUtils.equals("abc", "ABC"));
	}
	
	@Test
	public void equalsIgnoreCase() {
		//true
		assertTrue(StringUtils.equalsIgnoreCase(null, null));
		assertTrue(StringUtils.equalsIgnoreCase("abc", "abc"));
		assertTrue(StringUtils.equalsIgnoreCase("abc", "ABC"));
		//false
		assertFalse(StringUtils.equalsIgnoreCase(null, "abc"));
		assertFalse(StringUtils.equalsIgnoreCase("abc", null));
	}
	
	@Test
	public void indexOf() {
		//indexOf(CharSequence seq, int searchChar)
		//A null or empty ("") CharSequence will return INDEX_NOT_FOUND (-1).
		assertThat(StringUtils.indexOf(null, 'x'), is(-1));
		assertThat(StringUtils.indexOf("", 'c'), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", 'a'), is(0));
		assertThat(StringUtils.indexOf("aabaabaa", 'b'), is(2));
		
		//indexOf(CharSequence seq, int searchChar, int startPos)
		//A null or empty ("") CharSequence will return (INDEX_NOT_FOUND) -1. 
		//A negative start position is treated as zero. A start position greater than the string length returns -1.
		assertThat(StringUtils.indexOf(null, 0, 0), is(-1));
		assertThat(StringUtils.indexOf("", 0, 0), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", 'b', 0), is(2));
		assertThat(StringUtils.indexOf("aabaabaa", 'b', 3), is(5));
		assertThat(StringUtils.indexOf("aabaabaa", 'b', 9), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", 'b', -1), is(2));
		
		//indexOf(CharSequence seq, CharSequence searchSeq)
		assertThat(StringUtils.indexOf(null, "anything"), is(-1));
		assertThat(StringUtils.indexOf("anything", null), is(-1));
		assertThat(StringUtils.indexOf("", ""), is(0));
		assertThat(StringUtils.indexOf("aabaabaa", "a"), is(0));
		assertThat(StringUtils.indexOf("aabaabaa", "b"), is(2));
		assertThat(StringUtils.indexOf("aabaabaa", "ab"), is(1));
		assertThat(StringUtils.indexOf("aabaabaa", ""), is(0));
		
		//indexOf(CharSequence seq, CharSequence searchSeq, int startPos)
		//A null CharSequence will return -1. 
		//A negative start position is treated as zero. 
		//An empty ("") search CharSequence always matches. 
		//A start position greater than the string length only matches an empty search CharSequence.
		assertThat(StringUtils.indexOf(null, "abc", 0), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", null, 0), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", "a", 0), is(0));
		assertThat(StringUtils.indexOf("aabaabaa", "b", 0), is(2));
		assertThat(StringUtils.indexOf("aabaabaa", "ab", 0), is(1));
		assertThat(StringUtils.indexOf("aabaabaa", "b", 3), is(5));
		assertThat(StringUtils.indexOf("aabaabaa", "b", 9), is(-1));
		assertThat(StringUtils.indexOf("aabaabaa", "b", -1), is(2));
		assertThat(StringUtils.indexOf("aabaabaa", "", 2), is(2));
		assertThat(StringUtils.indexOf("abc", "", 9), is(3));
	}
	
	@Test
	public void ordinalIndexOf() {
		//A null CharSequence will return -1.
		assertThat(StringUtils.ordinalIndexOf(null, "sdk", 1), is(-1));
		assertThat(StringUtils.ordinalIndexOf("", "", 0), is(-1));
		assertThat(StringUtils.ordinalIndexOf("", "", 1), is(0));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", null, 0), is(-1));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "a", 1), is(0));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "a", 2), is(1));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "b", 1), is(2));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "b", 2), is(5));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "ab", 1), is(1));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "ab", 2), is(4));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "", 1), is(0));
		assertThat(StringUtils.ordinalIndexOf("aabaabaa", "", 2), is(0));
	}
	
	@Test
	public void indexOfIgnoreCase() {
		//indexOfIgnoreCase(CharSequence str, CharSequence searchStr)
		assertThat(StringUtils.indexOfIgnoreCase(null, "anything"), is(-1));
		assertThat(StringUtils.indexOfIgnoreCase("anything", null), is(-1));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "a"), is(0));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "b"), is(2));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "ab"), is(1));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "Ab"), is(1));
		
		//indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos)
		//A null CharSequence will return -1. 
		//A negative start position is treated as zero. 
		//An empty ("") search CharSequence always matches. 
		//A start position greater than the string length only matches an empty search CharSequence.
		assertThat(StringUtils.indexOfIgnoreCase(null, "anything", 0), is(-1));
		assertThat(StringUtils.indexOfIgnoreCase("", "", 0), is(0));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", null, 0), is(-1));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0), is(0));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0), is(2));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0), is(1));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3), is(5));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9), is(-1));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1), is(2));
		assertThat(StringUtils.indexOfIgnoreCase("aabaabaa", "", 2), is(2));
		assertThat(StringUtils.indexOfIgnoreCase("abc", "", 2), is(2));
		assertThat(StringUtils.indexOfIgnoreCase("abc", "", 3), is(3));
		assertThat(StringUtils.indexOfIgnoreCase("abc", "", 4), is(4));
		assertThat(StringUtils.indexOfIgnoreCase("abc", "", 5), is(-1));
	}
	
	@Test
	public void lastIndexOf() {
		//lastIndexOf(CharSequence seq, int searchChar)
		//A null or empty ("") CharSequence will return -1.
		assertThat(StringUtils.lastIndexOf(null, 'c'), is(-1));
		assertThat(StringUtils.lastIndexOf("", 'b'), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'a'), is(7));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b'), is(5));
		
		//lastIndexOf(CharSequence seq, int searchChar, int startPos)
		//A null or empty ("") CharSequence will return -1. 
		//A negative start position returns -1. 
		//A start position greater than the string length searches the whole string. 
		//The search starts at the startPos and works backwards; matches starting after the start position are ignored. 
		assertThat(StringUtils.lastIndexOf(null, 'a', 0), is(-1));
		assertThat(StringUtils.lastIndexOf("", 'b', 0), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b', 8), is(5));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b', 4), is(2));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b', 0), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b', 9), is(5));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'b', -1), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", 'a', 0), is(0));
		
		//lastIndexOf(CharSequence seq, CharSequence searchSeq)
		//A null CharSequence will return -1.
		assertThat(StringUtils.lastIndexOf(null, "sfs"), is(-1));
		assertThat(StringUtils.lastIndexOf("", "bas"), is(-1));
		assertThat(StringUtils.lastIndexOf("", ""), is(0));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "a"), is(7));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b"), is(5));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "ab"), is(4));
		assertThat(StringUtils.lastIndexOf("aabaabaa", ""), is(8));
		
		//lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos)
		//A null CharSequence will return -1. 
		//A negative start position returns -1. 
		//An empty ("") search CharSequence always matches unless the start position is negative. 
		//A start position greater than the string length searches the whole string. 
		//The search starts at the startPos and works backwards; matches starting after the start position are ignored. 
		assertThat(StringUtils.lastIndexOf(null, "sdf", 7), is(-1));
		assertThat(StringUtils.lastIndexOf("", null, 3), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "a", 8), is(7));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", 8), is(5));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "ab", 8), is(4));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", 9), is(5));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", -1), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "a", 0), is(0));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", 0), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", 1), is(-1));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "b", 2), is(2));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "ba", 2), is(2));
		assertThat(StringUtils.lastIndexOf("aabaabaa", "", 6), is(6));
	}
	
	@Test
	public void lastOrdinalIndexOf() {
		//A null String will return -1.
		assertThat(StringUtils.lastOrdinalIndexOf(null, "asfd", 1), is(-1));
		assertThat(StringUtils.lastOrdinalIndexOf("", "dsgs", 1), is(-1));
		assertThat(StringUtils.lastOrdinalIndexOf("", "", 1), is(0));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1), is(7));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2), is(6));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1), is(5));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2), is(2));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1), is(4));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2), is(1));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1), is(8));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2), is(8));
		assertThat(StringUtils.lastOrdinalIndexOf("aabaabaa", "", 5), is(8));
	}
	
	@Test
	public void lastIndexOfIgnoreCase() {
		//lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr)
		//A null CharSequence will return -1. 
		//A negative start position returns -1. 
		//An empty ("") search CharSequence always matches unless the start position is negative. 
		//A start position greater than the string length searches the whole string.
		assertThat(StringUtils.lastIndexOfIgnoreCase(null, "sfljasl"), is(-1));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", null), is(-1));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A"), is(7));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B"), is(5));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB"), is(4));
		
		//lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos)
		//A null CharSequence will return -1. 
		//A negative start position returns -1. 
		//An empty ("") search CharSequence always matches unless the start position is negative. 
		//A start position greater than the string length searches the whole string. 
		//The search starts at the startPos and works backwards; matches starting after the start position are ignored. 
		assertThat(StringUtils.lastIndexOfIgnoreCase(null, "fdh", 2), is(-1));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", null, 6), is(-1));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8), is(7));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8), is(5));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8), is(4));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9), is(5));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1), is(-1));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0), is(0));
		assertThat(StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0), is(-1));
	}
	
	@Test
	public void contains() {
		//contains(CharSequence seq, int searchChar)
		//A null or empty ("") CharSequence will return false.
		//false
		assertFalse(StringUtils.contains(null, 's'));
		assertFalse(StringUtils.contains("", 'd'));
		assertFalse(StringUtils.contains("abc", 'z'));
		//true
		assertTrue(StringUtils.contains("abc", 'a'));
		
		//contains(CharSequence seq, CharSequence searchSeq)
		//A null CharSequence will return false.
		//false
		assertFalse(StringUtils.contains(null, "anything"));
		assertFalse(StringUtils.contains(null, null));
		assertFalse(StringUtils.contains("", null));
		assertFalse(StringUtils.contains("abc", "z"));
		//true
		assertTrue(StringUtils.contains("", ""));
		assertTrue(StringUtils.contains("abc", ""));
		assertTrue(StringUtils.contains("abc", "b"));
	}
	
	@Test
	public void containsIgnoreCase() {
		//false
		assertFalse(StringUtils.containsIgnoreCase(null, "anything"));
		assertFalse(StringUtils.containsIgnoreCase("", null));
		assertFalse(StringUtils.containsIgnoreCase("abc", "z"));
		assertFalse(StringUtils.containsIgnoreCase("abc", "Z"));
		//true
		assertTrue(StringUtils.containsIgnoreCase("", ""));
		assertTrue(StringUtils.containsIgnoreCase("abc", ""));
		assertTrue(StringUtils.containsIgnoreCase("abc", "a"));
		assertTrue(StringUtils.containsIgnoreCase("abc", "A"));
	}
	
	@Test
	public void containsWhitespace() {
		//false
		assertFalse(StringUtils.containsWhitespace(""));
		assertFalse(StringUtils.containsWhitespace("利克镇足球俱乐部"));
		//true
		assertTrue(StringUtils.containsWhitespace("abc "));
		assertTrue(StringUtils.containsWhitespace("ab c"));
		assertTrue(StringUtils.containsWhitespace("	"));
	}
	
	@Test
	public void indexOfAny() {
		//indexOfAny(CharSequence cs, char... searchChars)
		//A null String will return -1. 
		//A null or zero length search array will return -1.
		assertThat(StringUtils.indexOfAny(null, 'a', 'b'), is(-1));
		assertThat(StringUtils.indexOfAny("", 'k', 'u', '8'), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", (char[]) null), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", new char[]{}), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", 'k', 'd'), is(2));
		assertThat(StringUtils.indexOfAny("saklfdj", 'r', 'd'), is(5));
		assertThat(StringUtils.indexOfAny("saklfdj", 'p'), is(-1));
		
		//indexOfAny(CharSequence cs, String searchChars)
		//A null String will return -1. 
		//A null search string will return -1.
		assertThat(StringUtils.indexOfAny(null, "ab"), is(-1));
		assertThat(StringUtils.indexOfAny("", "ku8"), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", (String) null), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", ""), is(-1));
		assertThat(StringUtils.indexOfAny("saklfdj", "kd"), is(2));
		assertThat(StringUtils.indexOfAny("saklfdj", "rd"), is(5));
		assertThat(StringUtils.indexOfAny("saklfdj", "p"), is(-1));
		
		//indexOfAny(CharSequence str, CharSequence... searchStrs)
		//A null CharSequence will return -1. 
		//A null or zero length search array will return -1. 
		//A null search array entry will be ignored, but a search array containing "" will return 0 if str is not null.
		assertThat(StringUtils.indexOfAny(null, "salfd", "mkdsa"), is(-1));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", (String[]) null), is(-1));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", new String[]{}), is(-1));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", "ab", "cd"), is(2));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", "cd", "ab"), is(2));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", "mn", "op"), is(-1));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", "zab", "aby"), is(1));
		assertThat(StringUtils.indexOfAny("zzabyycdxx", new String[]{""}), is(0));
		assertThat(StringUtils.indexOfAny("", new String[]{""}), is(0));
		assertThat(StringUtils.indexOfAny("", "a"), is(-1));
	}
	
	@Test
	public void containsAny() {
		//containsAny(CharSequence cs, char... searchChars)
		//A null CharSequence will return false. 
		//A null or zero length search array will return false.
		//false
		assertFalse(StringUtils.containsAny(null, 'i'));
		assertFalse(StringUtils.containsAny("", 'i'));
		assertFalse(StringUtils.containsAny("zzabyycdxx", (char[]) null));
		assertFalse(StringUtils.containsAny("zzabyycdxx", new char[]{}));
		assertFalse(StringUtils.containsAny("aba", 'z'));
		//true
		assertTrue(StringUtils.containsAny("zzabyycdxx", 'u', 'a'));
		assertTrue(StringUtils.containsAny("zzabyycdxx", 'b', 'y'));
		
		//containsAny(CharSequence cs, CharSequence searchChars)
		//A null CharSequence will return false. 
		//A null search CharSequence will return false.
		//false
		assertFalse(StringUtils.containsAny(null, "abce"));
		assertFalse(StringUtils.containsAny("", "abce"));
		assertFalse(StringUtils.containsAny("zzabyycdxx", (String) null));
		assertFalse(StringUtils.containsAny("aba", "z"));
		//true
		assertTrue(StringUtils.containsAny("zzabyycdxx", "ua"));
		assertTrue(StringUtils.containsAny("zzabyycdxx", "by"));
	}
	
	@Test
	public void indexOfAnyBut() {
		//indexOfAnyBut(CharSequence cs, char... searchChars)
		//A null CharSequence will return -1. 
		//A null or zero length search array will return -1.
		assertThat(StringUtils.indexOfAnyBut(null, 'a'), is(-1));
		assertThat(StringUtils.indexOfAnyBut("", 'a'), is(-1));
		assertThat(StringUtils.indexOfAnyBut("anything", new char[]{}), is(-1));
		assertThat(StringUtils.indexOfAnyBut("zzabyycdxx", new char[]{'z', 'a'}), is(3));
		assertThat(StringUtils.indexOfAnyBut("aba", new char[]{'z'}), is(0));
		assertThat(StringUtils.indexOfAnyBut("aba", new char[]{'a', 'b'}), is(-1));
		
		//indexOfAnyBut(CharSequence seq, CharSequence searchChars)
		//A null CharSequence will return -1. 
		//A null or empty search string will return -1.
		assertThat(StringUtils.indexOfAnyBut(null, "anything"), is(-1));
		assertThat(StringUtils.indexOfAnyBut("", "anything"), is(-1));
		assertThat(StringUtils.indexOfAnyBut("anything", ""), is(-1));
		assertThat(StringUtils.indexOfAnyBut("zzabyycdxx", "za"), is(3));
		assertThat(StringUtils.indexOfAnyBut("zzabyycdxx", ""), is(-1));
		assertThat(StringUtils.indexOfAnyBut("aba", "ab"), is(-1));
	}
	
	@Test
	public void containsOnly() {
		//containsOnly(CharSequence cs, char... valid)
		//A null CharSequence will return false. 
		//A null valid character array will return false. 
		//An empty CharSequence (length()=0) always returns true.
		//false
		assertFalse(StringUtils.containsOnly(null, 'a'));
		assertFalse(StringUtils.containsOnly("ab", 'a'));
		//true
		assertTrue(StringUtils.containsOnly("", 'a'));
		assertTrue(StringUtils.containsOnly("abab", 'a', 'b', 's'));
		
		//containsOnly(CharSequence cs, String validChars)
		//A null CharSequence will return false. 
		//A null valid character String will return false. 
		//An empty String (length()=0) always returns true.
		//false
		assertFalse(StringUtils.containsOnly(null, "anything"));
		assertFalse(StringUtils.containsOnly("ab", ""));
		assertFalse(StringUtils.containsOnly("abc", "assbs"));
		assertFalse(StringUtils.containsOnly("abz", "assbs"));
		//true
		assertTrue(StringUtils.containsOnly("", "anything"));
		assertTrue(StringUtils.containsOnly("abab", "assbs"));
	}
	
	@Test
	public void containsNone() {
		//containsNone(CharSequence cs, char... searchChars)
		//A null CharSequence will return true. 
		//A null invalid character array will return true. 
		//An empty CharSequence (length()=0) always returns true.
		//true
		assertTrue(StringUtils.containsNone(null, 'a'));
		assertTrue(StringUtils.containsNone("", 'a'));
		assertTrue(StringUtils.containsNone("anything", new char[]{}));
		assertTrue(StringUtils.containsNone("abab", 'x', 'y', 'z'));
		assertTrue(StringUtils.containsNone("ab1", 'x', 'y', 'z'));
		//false
		assertFalse(StringUtils.containsNone("abz", 'x', 'y', 'z'));
		
		//containsNone(CharSequence cs, String invalidChars)
		//A null CharSequence will return true. 
		//A null invalid character array will return true. 
		//An empty String ("") always returns true.
		//true
		assertTrue(StringUtils.containsNone(null, "anything"));
		assertTrue(StringUtils.containsNone("", "anything"));
		assertTrue(StringUtils.containsNone("", ""));
		assertTrue(StringUtils.containsNone("ab", ""));
		assertTrue(StringUtils.containsNone("abab", "xyz"));
		assertTrue(StringUtils.containsNone("ab1", "xyz"));
		//false
		assertFalse(StringUtils.containsNone("abz", "xyz"));
	}
	
	@Test
	public void substring() {
		//substring(String str, int start)
		//A negative start position can be used to start n characters from the end of the String.
		//A null String will return null. An empty ("") String will return "".
		assertNull(StringUtils.substring(null, 0));
		assertThat(StringUtils.substring("", 0), is(""));
		assertThat(StringUtils.substring("abc", 0), is("abc"));
		assertThat(StringUtils.substring("abc", 2), is("c"));
		assertThat(StringUtils.substring("abc", 4), is(""));
		assertThat(StringUtils.substring("abc", -2), is("bc"));
		assertThat(StringUtils.substring("abc", -4), is("abc"));
		
		//substring(String str, int start, int end)
		//A negative start position can be used to start/end n characters from the end of the String.
		assertNull(StringUtils.substring(null, 0, 1));
		assertThat(StringUtils.substring("", 0, 1), is(""));
		assertThat(StringUtils.substring("abc", 0, 2), is("ab"));
		assertThat(StringUtils.substring("abc", 2, 0), is(""));
		assertThat(StringUtils.substring("abc", 2, 4), is("c"));
		assertThat(StringUtils.substring("abc", 4, 6), is(""));
		assertThat(StringUtils.substring("abc", 2, 2), is(""));
		assertThat(StringUtils.substring("abc", -2, -1), is("b"));
		assertThat(StringUtils.substring("abc", -4, 2), is("ab"));
	}
	
	@Test
	public void left() {
		//If len characters are not available, or the String is null, the String will be returned without an exception. 
		//An empty String is returned if len is negative.
		assertNull(StringUtils.left(null, 1));
		assertThat(StringUtils.left("anything", -5), is(""));
		assertThat(StringUtils.left("", 5), is(""));
		assertThat(StringUtils.left("abc", 0), is(""));
		assertThat(StringUtils.left("abc", 2), is("ab"));
		assertThat(StringUtils.left("abc", 4), is("abc"));
	}
	
	@Test
	public void right() {
		//If len characters are not available, or the String is null, the String will be returned without an an exception. 
		//An empty String is returned if len is negative.
		assertNull(StringUtils.right(null, 1));
		assertThat(StringUtils.right("anything", -5), is(""));
		assertThat(StringUtils.right("", 5), is(""));
		assertThat(StringUtils.right("abc", 0), is(""));
		assertThat(StringUtils.right("abc", 2), is("bc"));
		assertThat(StringUtils.right("abc", 4), is("abc"));
	}
	
	@Test
	public void mid() {
		//If the String is null, null will be returned. 
		//An empty String is returned if len is negative or exceeds the length of str.
		assertNull(StringUtils.mid(null, 2, 2));
		assertThat(StringUtils.mid("anything", 0, -4), is(""));
		assertThat(StringUtils.mid("", 0, 2), is(""));
		assertThat(StringUtils.mid("abc", 0, 2), is("ab"));
		assertThat(StringUtils.mid("abc", 0, 4), is("abc"));
		assertThat(StringUtils.mid("abc", 2, 4), is("c"));
		assertThat(StringUtils.mid("abc", 4, 2), is(""));
		assertThat(StringUtils.mid("abc", -2, 2), is("ab"));
	}
	
	@Test
	public void substringBefore() {
		//A null string input will return null. 
		//An empty ("") string input will return the empty string. 
		//A null separator will return the input string.
		//If nothing is found, the string input is returned.
		assertNull(StringUtils.substringBefore(null, "anything"));
		assertThat(StringUtils.substringBefore("", "anything"), is(""));
		assertThat(StringUtils.substringBefore("abc", "a"), is(""));
		assertThat(StringUtils.substringBefore("abcba", "b"), is("a"));
		assertThat(StringUtils.substringBefore("abc", "c"), is("ab"));
		assertThat(StringUtils.substringBefore("abc", "d"), is("abc"));
		assertThat(StringUtils.substringBefore("abc", ""), is(""));
		assertThat(StringUtils.substringBefore("abc", null), is("abc"));
	}
	
	@Test
	public void substringAfter() {
		//A null string input will return null. 
		//An empty ("") string input will return the empty string. 
		//A null separator will return the empty string if the input string is not null.
		//If nothing is found, the empty string is returned.
		assertNull(StringUtils.substringAfter(null, "anything"));
		assertThat(StringUtils.substringAfter("", "anything"), is(""));
		assertThat(StringUtils.substringAfter("anything", null), is(""));
		assertThat(StringUtils.substringAfter("abc", "a"), is("bc"));
		assertThat(StringUtils.substringAfter("abcba", "b"), is("cba"));
		assertThat(StringUtils.substringAfter("abc", "c"), is(""));
		assertThat(StringUtils.substringAfter("abc", "d"), is(""));
		assertThat(StringUtils.substringAfter("abc", ""), is("abc"));
	}
	
	@Test
	public void substringBeforeLast() {
		//A null string input will return null. 
		//An empty ("") string input will return the empty string. 
		//An empty or null separator will return the input string.
		//If nothing is found, the string input is returned.
		assertNull(StringUtils.substringBeforeLast(null, "anything"));
		assertThat(StringUtils.substringBeforeLast("", "anything"), is(""));
		assertThat(StringUtils.substringBeforeLast("abcba", "b"), is("abc"));
		assertThat(StringUtils.substringBeforeLast("abc", "c"), is("ab"));
		assertThat(StringUtils.substringBeforeLast("a", "a"), is(""));
		assertThat(StringUtils.substringBeforeLast("a", "z"), is("a"));
		assertThat(StringUtils.substringBeforeLast("a", null), is("a"));
		assertThat(StringUtils.substringBeforeLast("a", ""), is("a"));
	}
	
	@Test
	public void substringAfterLast() {
		//A null string input will return null. 
		//An empty ("") string input will return the empty string. 
		//An empty or null separator will return the empty string if the input string is not null.
		//If nothing is found, the empty string is returned.
		assertNull(StringUtils.substringAfterLast(null, "anything"));
		assertThat(StringUtils.substringAfterLast("", "anything"), is(""));
		assertThat(StringUtils.substringAfterLast("Kangana Ranaut", ""), is(""));
		assertThat(StringUtils.substringAfterLast("Expedition", null), is(""));
		assertThat(StringUtils.substringAfterLast("Mogobane", "village"), is(""));
		assertThat(StringUtils.substringAfterLast(" south-East", "south-"), is("East"));
		assertThat(StringUtils.substringAfterLast(" south-East", "s"), is("t"));
	}
	
	@Test
	public void substringBetween() {
		//substringBetween(String str, String tag)
		//A null input String returns null. 
		//A null tag returns null.
		assertNull(StringUtils.substringBetween("", "anything"));
		assertNull(StringUtils.substringBetween("anything", null));
		assertNull(StringUtils.substringBetween("tagabctag", "abc"));
		assertThat(StringUtils.substringBetween("", ""), is(""));
		assertThat(StringUtils.substringBetween("tagabctag", ""), is(""));
		assertThat(StringUtils.substringBetween("tagabctag", "tag"), is("abc"));
		
		//substringBetween(String str, String open, String close)
		//A null input String returns null. 
		//A null open/close returns null (no match). 
		//An empty ("") open and close returns an empty string.
		assertNull(StringUtils.substringBetween(null, "anything", "anything"));
		assertNull(StringUtils.substringBetween("anything", null, "anything"));
		assertNull(StringUtils.substringBetween("anything", "anything", null));
		assertNull(StringUtils.substringBetween("", "[", ""));
		assertNull(StringUtils.substringBetween("", "[", "]"));
		assertThat(StringUtils.substringBetween("wx[b]yz", "", ""), is(""));
		assertThat(StringUtils.substringBetween("wx[b]yz", "[", "]"), is("b"));
		assertThat(StringUtils.substringBetween("yabcz", "y", "z"), is("abc"));
		assertThat(StringUtils.substringBetween("yabczyabdz", "y", "z"), is("abc"));
	}
	
	@Test
	public void substringsBetween() {
		//A null input String returns null. 
		//A null open/close returns null (no match). 
		//An empty ("") open/close returns null (no match).
		assertNull(StringUtils.substringsBetween(null, "anything", "anything"));
		assertNull(StringUtils.substringsBetween("anything", null, "anything"));
		assertNull(StringUtils.substringsBetween("anything", "anything", null));
		assertThat(StringUtils.substringsBetween("[a][b][c]", "[", "]"), equalTo(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.substringsBetween("", "[", "]"), equalTo(new String[]{}));
	}
	
	@Test
	public void split() {
		//split(String str)
		//A null input String returns null.
		assertNull(StringUtils.split(null));
		assertThat(StringUtils.split(""), equalTo(new String[]{}));
		assertThat(StringUtils.split("abc def"), equalTo(new String[]{"abc", "def"}));
		assertThat(StringUtils.split("abc  def"), equalTo(new String[]{"abc", "def"}));
		assertThat(StringUtils.split(" abc  "), equalTo(new String[]{"abc"}));
		
		//split(String str, char separatorChar)
		//A null input String returns null. 
		//A null separatorChars splits on whitespace.
		assertNull(StringUtils.split(null, 'a'));
		assertThat(StringUtils.split("", 'a'), equalTo(new String[]{}));
		assertThat(StringUtils.split("a.b.c", '.'), equalTo(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.split("a..b.c", '.'), equalTo(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.split("a:b:c", '.'), equalTo(new String[]{"a:b:c"}));
		assertThat(StringUtils.split("a b c", ' '), equalTo(new String[]{"a", "b", "c"}));
		
		//split(String str, String separatorChars)
		//A null input String returns null. 
		//A null separatorChars splits on whitespace.
		assertNull(StringUtils.split(null, "anything"));
		assertThat(StringUtils.split("", "anything"), equalTo(new String[]{}));
		assertThat(StringUtils.split("abc def", null), equalTo(new String[]{"abc", "def"}));
		assertThat(StringUtils.split("abc def", " "), equalTo(new String[]{"abc", "def"}));
		assertThat(StringUtils.split("abc  def", " "), equalTo(new String[]{"abc", "def"}));
		assertThat(StringUtils.split("ab:cd:ef", ":"), equalTo(new String[]{"ab", "cd",  "ef"}));
		
		//split(String str, String separatorChars, int max)
		//A null input String returns null. 
		//A null separatorChars splits on whitespace.
		assertNull(StringUtils.split(null, "anything", 8));
		assertThat(StringUtils.split("", "anything", 6), equalTo(new String[]{}));
		assertThat(StringUtils.split("ab cd ef", null, 0), equalTo(new String[]{"ab", "cd", "ef"}));
		assertThat(StringUtils.split("ab          cd ef", null, 0), equalTo(new String[]{"ab", "cd", "ef"}));
		assertThat(StringUtils.split("ab:cd:ef", ":", 0), equalTo(new String[]{"ab", "cd", "ef"}));
		assertThat(StringUtils.split("ab:cd:ef", ":", 2), equalTo(new String[]{"ab", "cd:ef"}));
	}
	
	@Test
	public void splitByWholeSeparator() {
		//splitByWholeSeparator(String str, String separator)
		//A null input String returns null. 
		//A null separator splits on whitespace.
		assertNull(StringUtils.splitByWholeSeparator(null, "anything"));
		assertThat(StringUtils.splitByWholeSeparator("California State Route 57", null), is(new String[]{"California", "State", "Route", "57"}));
		assertThat(StringUtils.splitByWholeSeparator("ab:cd:ef", ":"), is(new String[]{"ab", "cd", "ef"}));
		assertThat(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-"), is(new String[]{"ab", "cd", "ef"}));
		
		//splitByWholeSeparator(String str, String separator, int max)
		//A null input String returns null. 
		//A null separator splits on whitespace.
		assertNull(StringUtils.splitByWholeSeparator(null, "anything", 0));
		assertThat(StringUtils.splitByWholeSeparator("California State Route 57", null, 0), is(new String[]{"California", "State", "Route", "57"}));
		assertThat(StringUtils.splitByWholeSeparator("California State Route 57", null, 2), is(new String[]{"California", "State Route 57"}));
		assertThat(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5), is(new String[]{"ab", "cd", "ef"}));
		assertThat(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2), is(new String[]{"ab", "cd-!-ef"}));
	}
	
	@Test
	public void splitByWholeSeparatorPreserveAllTokens() {
		//StringUtilss.splitByWholeSeparatorPreserveAllTokens(String str, String separator)
		//A null input String returns null. 
		//A null separator splits on whitespace.
		assertNull(StringUtils.splitByWholeSeparatorPreserveAllTokens(null, "anything"));
		assertThat(StringUtils.splitByWholeSeparatorPreserveAllTokens("California State Route 57", null), is(new String[]{"California", "State", "Route", "57"}));
		assertThat(StringUtils.splitByWholeSeparatorPreserveAllTokens("California   State Route 57", null), is(new String[]{"California", "", "", "State", "Route", "57"}));

		//splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max)
		//A null input String returns null. 
		//A null separator splits on whitespace.
		assertNull(StringUtils.splitByWholeSeparatorPreserveAllTokens(null, "anything", 0));
		assertThat(StringUtils.splitByWholeSeparatorPreserveAllTokens("California State Route 57", null, 0), is(new String[]{"California", "State", "Route", "57"}));
		assertThat(StringUtils.splitByWholeSeparatorPreserveAllTokens("California   State Route 57", null, 3), is(new String[]{"California", "", " State Route 57"}));
	}
	
	@Test
	public void splitPreserveAllTokens() {
		//splitPreserveAllTokens(String str)
		//A null input String returns null.
		assertNull(StringUtils.splitPreserveAllTokens(null));
		assertThat(StringUtils.splitPreserveAllTokens(""), is(new String[]{}));
		assertThat(StringUtils.splitPreserveAllTokens("abc def"), is(new String[]{"abc", "def"}));
		assertThat(StringUtils.splitPreserveAllTokens("abc  def"), is(new String[]{"abc", "","def"}));
		assertThat(StringUtils.splitPreserveAllTokens(" abc  "), is(new String[]{"", "abc", "",""}));
		
		//splitPreserveAllTokens(String str, char separatorChar)
		//A null input String returns null.
		assertNull(StringUtils.splitPreserveAllTokens(null, 'x'));
		assertThat(StringUtils.splitPreserveAllTokens("", 'x'), is(new String[]{}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c", '.'), is(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a..b.c", '.'), is(new String[]{"a", "", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a:b:c", '.'), is(new String[]{"a:b:c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a b c", ' '), is(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a b c ", ' '), is(new String[]{"a", "b", "c", ""}));
		assertThat(StringUtils.splitPreserveAllTokens("a b c  ", ' '), is(new String[]{"a", "b", "c", "", ""}));
		assertThat(StringUtils.splitPreserveAllTokens(" a b c", ' '), is(new String[]{"", "a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("  a b c", ' '), is(new String[]{"", "", "a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens(" a b c ", ' '), is(new String[]{"", "a", "b", "c", ""}));
		
		//splitPreserveAllTokens(String str, String separatorChars)
		//A null input String returns null. 
		//A null separatorChars splits on whitespace.
		assertNull(StringUtils.splitPreserveAllTokens(null, "anything"));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c", "."), is(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", "."), is(new String[]{"a", "b", "c", ""}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", null), is(new String[]{"a.b.c."}));
		assertThat(StringUtils.splitPreserveAllTokens(".a.b.c.", "."), is(new String[]{"", "a", "b", "c", ""}));
		
		//splitPreserveAllTokens(String str, String separatorChars, int max)
		//A null input String returns null. 
		//A null separatorChars splits on whitespace.
		assertNull(StringUtils.splitPreserveAllTokens(null, "anything", 2));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c", ".", 0), is(new String[]{"a", "b", "c"}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", ".", 0), is(new String[]{"a", "b", "c", ""}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", ".", 2), is(new String[]{"a", "b.c."}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", ".", 3), is(new String[]{"a", "b", "c."}));
		assertThat(StringUtils.splitPreserveAllTokens("a.b.c.", null, 0), is(new String[]{"a.b.c."}));
		assertThat(StringUtils.splitPreserveAllTokens(".a.b.c.", ".", 4), is(new String[]{"", "a", "b", "c."}));
	}
	
	@Test
	public void splitByCharacterType() {
		assertNull(StringUtils.splitByCharacterType(null));
		assertThat(StringUtils.splitByCharacterType(""), is(new String[]{}));
		assertThat(StringUtils.splitByCharacterType("ab de fg"), is(new String[]{"ab", " ", "de", " ", "fg"}));
		assertThat(StringUtils.splitByCharacterType("ab   de fg"), is(new String[]{"ab", "   ", "de", " ", "fg"}));
		assertThat(StringUtils.splitByCharacterType("ab:cd:ef"), is(new String[]{"ab", ":", "cd", ":", "ef"}));
		assertThat(StringUtils.splitByCharacterType("number5"), is(new String[]{"number", "5"}));
		assertThat(StringUtils.splitByCharacterType("fooBar"), is(new String[]{"foo", "B", "ar"}));
		assertThat(StringUtils.splitByCharacterType("foo200Bar"), is(new String[]{"foo", "200", "B", "ar"}));
		assertThat(StringUtils.splitByCharacterType("ASFRules"), is(new String[]{"ASFR", "ules"}));
	}
	
	@Test
	public void join() {
		//Null objects or empty strings within the array are represented by empty strings.
		assertThat(StringUtils.join("a", "b", "c"), is("abc"));
		assertThat(StringUtils.join(null, "", "c"), is("c"));
		assertThat(StringUtils.join(new byte[]{1, 2, 3}, ';'), is("1;2;3"));
		assertThat(StringUtils.join(new byte[]{1, 2, 3}, ' '), is("1 2 3"));
		assertThat(StringUtils.join(new String[]{"a", "b", "c"}, "~~", 0, 3), is("a~~b~~c"));
		assertThat(StringUtils.join(new String[]{"a", "b", "c"}, "~~", 1, 3), is("b~~c"));
		assertThat(StringUtils.join(new String[]{"a", "b", "c"}, "~~", 2, 3), is("c"));
		assertThat(StringUtils.join(new String[]{"a", "b", "c"}, "~~", 2, 2), is(""));
		assertThat(StringUtils.join(new String[]{null, "", "c"}, "~~", 0, 3), is("~~~~c"));
	}
	
	@Test
	public void deleteWhitespace() {
		assertNull(StringUtils.deleteWhitespace(null));
		assertThat(StringUtils.deleteWhitespace(""), is(""));
		assertThat(StringUtils.deleteWhitespace("abc"), is("abc"));
		assertThat(StringUtils.deleteWhitespace("ab c"), is("abc"));
		assertThat(StringUtils.deleteWhitespace("  a  bc "), is("abc"));
	}
	
	@Test
	public void removeStart() {
		//A null source string will return null. 
		//An empty ("") source string will return the empty string. 
		//A null search string will return the source string.
		assertNull(StringUtils.removeStart(null, "anything"));
		assertThat(StringUtils.removeStart("", "anything"), is(""));
		assertThat(StringUtils.removeStart("abc", null), is("abc"));
		assertThat(StringUtils.removeStart("www.domain.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeStart("domain.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeStart("www.domain.com", "com"), is("www.domain.com"));
	}
	
	@Test
	public void removeStartIgnoreCase() {
		//A null source string will return null. 
		//An empty ("") source string will return the empty string. 
		//A null search string will return the source string.
		assertNull(StringUtils.removeStartIgnoreCase(null, "anything"));
		assertThat(StringUtils.removeStartIgnoreCase("", "anything"), is(""));
		assertThat(StringUtils.removeStartIgnoreCase("abc", null), is("abc"));
		assertThat(StringUtils.removeStartIgnoreCase("www.domain.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeStartIgnoreCase("www.domain.com", "wWw."), is("domain.com"));
		assertThat(StringUtils.removeStartIgnoreCase("domain.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeStartIgnoreCase("www.domain.com", "com"), is("www.domain.com"));
	}
	
	@Test
	public void removeEnd() {
		//A null source string will return null. 
		//An empty ("") source string will return the empty string. 
		//A null search string will return the source string.
		assertNull(StringUtils.removeEnd(null, "anything"));
		assertThat(StringUtils.removeEnd("", "anything"), is(""));
		assertThat(StringUtils.removeEnd("abc", null), is("abc"));
		assertThat(StringUtils.removeEnd("abc", ""), is("abc"));
		assertThat(StringUtils.removeEnd("The Tetrarch", "Tetrarch"), is("The "));
		assertThat(StringUtils.removeEnd("The Tetrarch", "Tetrareh"), is("The Tetrarch"));
	}
	
	@Test
	public void removeEndIgnoreCase() {
		//A null source string will return null. 
		//An empty ("") source string will return the empty string. 
		//A null search string will return the source string.
		assertNull(StringUtils.removeEndIgnoreCase(null, "anything"));
		assertThat(StringUtils.removeEndIgnoreCase("", "anything"), is(""));
		assertThat(StringUtils.removeEndIgnoreCase("abc", null), is("abc"));
		assertThat(StringUtils.removeEndIgnoreCase("abc", ""), is("abc"));
		assertThat(StringUtils.removeEndIgnoreCase("The Tetrarch", "Tetrarch"), is("The "));
		assertThat(StringUtils.removeEndIgnoreCase("The Tetrarch", "tetrarch"), is("The "));
		assertThat(StringUtils.removeEndIgnoreCase("The Tetrarch", "Tetrareh"), is("The Tetrarch"));
	}
	
	@Test
	public void remove() {
		//remove(String str, String remove)
		//A null source string will return null. 
		//An empty ("") source string will return the empty string. 
		//A null remove string will return the source string. 
		//An empty ("") remove string will return the source string.
		assertNull(StringUtils.remove(null, "anything"));
		assertThat(StringUtils.remove("", "anything"), is(""));
		assertThat(StringUtils.remove("Amaryllis", ""), is("Amaryllis"));
		assertThat(StringUtils.remove("Amaryllis", null), is("Amaryllis"));
		assertThat(StringUtils.remove("queued", "ue"), is("qd"));
		assertThat(StringUtils.remove("queued", "qd"), is("queued"));
		
		//remove(String str, char remove)
		//A null source string will return null. 
		//An empty ("") source string will return the empty string.
		assertNull(StringUtils.remove(null, 'q'));
		assertThat(StringUtils.remove("", 'q'), is(""));
		assertThat(StringUtils.remove("Amaryllis", 'l'), is("Amaryis"));
		assertThat(StringUtils.remove("Amaryllis", 'A'), is("maryllis"));
	}
	
	@Test
	public void chomp() {
		assertNull(StringUtils.chomp(null));
		assertThat(StringUtils.chomp(""), is(""));
		assertThat(StringUtils.chomp("abc \r"), is("abc "));
		assertThat(StringUtils.chomp("abc\n"), is("abc"));
		assertThat(StringUtils.chomp("abc\r\n"), is("abc"));
		assertThat(StringUtils.chomp("abc\r\n\r\n"), is("abc\r\n"));
		assertThat(StringUtils.chomp("abc\n\r"), is("abc\n"));
		assertThat(StringUtils.chomp("abc\n\rabc"), is("abc\n\rabc"));
		assertThat(StringUtils.chomp("\r"), is(""));
		assertThat(StringUtils.chomp("\n"), is(""));
		assertThat(StringUtils.chomp("\r\n"), is(""));
	}	
}