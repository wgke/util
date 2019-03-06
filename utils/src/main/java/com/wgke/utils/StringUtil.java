package com.wgke.utils;

import android.text.TextUtils;

public class StringUtil {
    private static final String TAG = "StringUtil";
    private static final String REGULAR_PHONENUMBER = "^(?:1)\\d{10}$";
    private static final String REGULAR_AUTHCODE = "^\\d{4}$";
    private static final String REGULAR_BANKCARDNUMBER = "^\\d{13,19}$";
    private static final String REGULAR_PASS = "^[0-9A-Za-z]{6,22}$";


    /**
     * 是否是手机号吗
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && phoneNumber.matches(REGULAR_PHONENUMBER);
    }


    /**
     * 是否是验证码
     *
     * @param authCode
     * @return
     */
    public static boolean isAuthCode(String authCode) {
        return !TextUtils.isEmpty(authCode) && authCode.matches(REGULAR_AUTHCODE);
    }

    public static boolean isPass(String passStr) {
        return !TextUtils.isEmpty(passStr) && passStr.matches(REGULAR_PASS);
    }

    /**
     * 是否是身份证号码
     *
     * @return
     */
    public static boolean isIdNumber(String id) {
        if (null == id || id.isEmpty() || 18 != id.length()) {
            return false;
        }

        int[] factor = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] tail = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        int zeroAscii = (int) '0';
        char[] idChars = id.toCharArray();
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += factor[i] * ((int) idChars[i] - zeroAscii);
        }

        return (idChars[17] == tail[sum % 11]);
    }


    /**
     * 是否是银行卡号
     *
     * @param bankCardNumber
     * @return
     */
    public static boolean isBankCardNumber(String bankCardNumber) {
        return !TextUtils.isEmpty(bankCardNumber) && bankCardNumber.matches(REGULAR_BANKCARDNUMBER);
    }


    public static String hideSubstring(String string, int start, int end) {
        if (!TextUtils.isEmpty(string)) {
            int length = end - start;
            if (length > 0) {
                CharSequence cs = "";
                for (int i = 0; i < length; i++) {
                    cs = cs + "*";
                }
                StringBuilder builder = new StringBuilder(string);
                StringBuilder replace = builder.replace(start, end, cs.toString());
                return replace.toString();

            } else {
                return string;
            }
        } else {
            return string;
        }

    }
}
