package com.ni235.common.constant;

public class FundConstant {
//    基金类型：1.股票型。2.混合型。3.债券型。4.指数型。5.QDII-指数。6.混合-FOF
    public enum FundEnum {
        FUND_TYPE_GP(1, "股票型"), FUND_TYPE_HH(2, "混合型"),
        FUND_TYPE_ZQ(3, "债券型"), FUND_TYPE_ZS(4, "指数型"),
        FUND_TYPE_QDII(5, "QDII"), FUND_TYPE_HH_FOF(6, "混合-FOF"),
        FUND_TYPE_GP_FOF(7, "股票-FOF"), FUND_TYPE_QDII_ZS(8, "QDII-指数"),
        FUND_TYPE_DK_ZQ(9, "定开债券"), FUND_TYPE_GP_ZS(10, "股票指数"), FUND_TYPE_CX(11, "其他创新")
        ,FUND_TYPE_LJ(11, "联接基金"), FUND_TYPE_ZQ_ZS(11, "债券指数");
        private int code;
        private String msg;

        FundEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
