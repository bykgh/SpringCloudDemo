package com.byk.common.common;

public class AccountCommon {

    /**
     * 资源可用
     */
    public static class AvailableType{

        /**
         * 可用
         */
        public static final String AVAILABLE = "1";

        /**
         * 不可用
         */
        public static final String UNAVAILABLE = "2";
    }


    /**
     * 资源类型
     */
    public static class ResourceType{

        /**
         * 菜单
         */
        public static final String MENU = "MENU";

        /**
         * 按钮
         */
        public static final String BUTTON = "BUTTON";
    }

    /**
     * 是否展示
     */
    public static class ShowType{

        /**
         * 显示
         */
        public static final String SHOW = "1";

        /**
         * 隐藏
         */
        public static final String HIDE = "0";
    }
}
