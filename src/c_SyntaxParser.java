package com.yiyaowen.javar;

import com.yiyaowen.javar.c_SyntaxParseInfo;

public class c_SyntaxParser
{
    /**
     * Parse the file and fill the information
     *
     * @param info (The information to be filled)
     * @param file (The file to be parsed)
     * @param keywords (Keywords that should be highlighed)
     * @param kwTotalCount (Size of keywords)
     * @param splitSymbols (Chars that used as split symbols)
     * @param ssTotalCount (Size of splitSymbols)
     * @return
     */
    public static native void fillSyntaxParseInfo(c_SyntaxParseInfo info, String file, String keywords[], int kwTotalCount, byte splitSymbols[], int ssTotalCount);

    static
    {
        System.loadLibrary("parser");
    }
}
