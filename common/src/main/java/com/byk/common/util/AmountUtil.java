package com.byk.common.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Title: 金额 工具类
 * Description:
 * Copyright: Copyright (c)2011
 * Company: pay
 *
 * @author huakui.zhang
 */
public class AmountUtil implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //private static final int SCALE = 2; //小数位数

    /**
     * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
     * <p>
     * 确的浮点数运算，包括加减乘除和四舍五入。
     */

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;

    //这个类不能实例化

    private AmountUtil() {

    }

    /** */
    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();

    }

    /** */
    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }

    /** */
    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */

    public static double mul(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).doubleValue();

    }

    /** */
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {

        return div(v1, v2, DEF_DIV_SCALE);

    }

    /** */
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /** */
    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * if a==b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equal(double a, double b) {
        if ((a - b > -0.001) && (a - b) < 0.001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * if a>＝b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean compare(double a, double b) {
        if (a - b > -0.001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * if a>b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean bigger(double a, double b) {
        if (a - b > 0.001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param int scale 保留的小数位
     * @return double
     * example:
     * 1.7333 处理后： 1.74
     * 1.7699 处理后： 1.77
     * 1.3000 处理后： 1.30
     * @Title: 进一法取值，返回不小于 value(保留scale位小数)的下一个数，将 value 的多余小数部分进一取值。
     * @Description:
     */
    public static double ceiling(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();

    }
}
