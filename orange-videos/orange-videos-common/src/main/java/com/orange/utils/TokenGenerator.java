package com.orange.utils;

import java.util.Calendar;

public class TokenGenerator {
    public String generate(String username) throws Exception {
        Calendar calendar = Calendar.getInstance();
        long timestamp = calendar.getTimeInMillis();
        String tokenMeta = username + timestamp;
        String token = MD5Utils.getMD5Str(tokenMeta);
        return token;
    }
}
