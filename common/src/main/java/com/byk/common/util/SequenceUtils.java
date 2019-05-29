package com.byk.common.util;


/**
 * Title: 序列工具
 * Description:
 * Copyright: Copyright (c)2011
 * Company: pay
 */
public class SequenceUtils {

    public static String createSequence(long sourceInt, int[] offset,
                                        int[]... displacementPair) {
        return displacement(incremental(sourceInt, offset), displacementPair);
    }

    public static String incremental(long sourceInt, int[] offset) {
        String sourceStr = String.valueOf(sourceInt);
        if ((sourceStr == null) || (offset == null)) {
            return sourceStr;
        }
        int sourceStrLength = sourceStr.length();
        int offsetLength = offset.length;

        if (sourceStrLength > offsetLength) {
            return sourceStr;
        }
        StringBuffer source = new StringBuffer();
        for (int i = 0; i < offsetLength - sourceStrLength; i++) {
            source.append("0");
        }
        source.append(sourceStr);

        StringBuffer result = new StringBuffer();
        char[] sequence = source.toString().toCharArray();
        for (int i = 0; i < sequence.length; i++) {
            long position = Integer.parseInt(String.valueOf(sequence[i]));
            position = (position + offset[i]) % 10;
            result.append(position);
        }

        return result.toString();
    }

    public static String displacement(String sourceStr,
                                      int[]... displacementPair) {
        if (displacementPair == null) {
            return sourceStr;
        }
        char[] source = sourceStr.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < displacementPair.length; i++) {
            if ((displacementPair[i].length != 2)
                    || (source.length < displacementPair[i][0])
                    || (source.length < displacementPair[i][1])) {
                return sourceStr;
            }
            char tmp = source[displacementPair[i][0] - 1];
            source[displacementPair[i][0] - 1] = source[displacementPair[i][1] - 1];
            source[displacementPair[i][1] - 1] = tmp;
        }
        result.append(source);

        return result.toString();
    }
}