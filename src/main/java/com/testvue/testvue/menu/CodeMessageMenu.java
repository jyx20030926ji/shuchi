package com.testvue.testvue.menu;


import com.testvue.testvue.iterface.MenuInterface;

public enum CodeMessageMenu implements MenuInterface {

        // 数据操作错误定义
        SUCCESS(2000, "成功!"),
        BODY_NOT_MATCH(4000, "请求的数据格式不符!"),
        SIGNATURE_NOT_MATCH(4001, "请求的数字签名不匹配!"),
        NOT_FOUND(4004, "未找到该资源!"),
        INTERNAL_SERVER_ERROR(5000, "服务器内部错误!"),
        SERVER_BUSY(5003, "服务器正忙，请稍后再试!"),
         SERVER_BUSYS(5003, "服务器正忙，请稍后再试!"),

        ACCOUNT_BUSYS(400,"账号不存在"),

        PASSWORD_BUSYS(400,"密码错误"),

        USER_NOT_LEGALLY(404,"用户身份不合法"),

        USER_ALREADY_EXIST(405,"用户已经存在"),

        USER_PASSWORD_DISARESS(405,"密码不一致，请重新输入"),

        Book_NOT_EXIST(404,"图书信息不存在");



        /**
         * 错误码
         */
        private final Integer codeMessage;

        /**
         * 错误描述
         */
        private final String textMessage;



        CodeMessageMenu(Integer resultCode, String resultMsg) {
            this.codeMessage = resultCode;
            this.textMessage = resultMsg;
        }


    @Override
    public Integer getCodeMessage() {
        return codeMessage;
    }

    @Override
    public String getTextMessage() {
        return textMessage;
    }
}
