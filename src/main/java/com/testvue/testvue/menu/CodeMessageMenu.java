package com.testvue.testvue.menu;


import com.testvue.testvue.enity.po.OrderDetail;
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

        Book_NOT_EXIST(404,"图书信息不存在"),

        CART_NOT_EXIST(404,"购物车不存在"),

        BOOK_NOT_EXIST_OF_CART(404,"该图书不存在于购物车"),

        ORDER_STATUS_NO_CAN_DELETE(404,"该订单状态错误，不能被取消或删除"),

       ORDER_NOT_EXIST(404,"订单不存在"),

       ORDER_ALREADY_PAYED(404,"订单已经支付过了"),

       USER_EMAIL_EXIST(401,"用户邮箱已存在"),

        CODE_NOT_EXIST(401,"验证码不存在"),

        CODE_CHECK_ERROR(401,"验证码输入错误,请重新输入"),

        VX_ACCOUNT_NOTEXIST(401,"未绑定微信账号,请先绑定微信账号"),

        PAY_PASSWORD_ERROR(401,"支付密码错误,请重试"),

        PAY_PASSWORD_NOTEXIST(401,"支付密码不存在,请先去设置支付密码"),

        USER_BLANCE_NOT(401,"用户余额不足"),

        DEFAULT_ADDRESS_NOT_EXIST(401,"默认地址不存在"),

        ORDER_DETAIL_NOT_EXIST(401,"订单详情不存在");



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
