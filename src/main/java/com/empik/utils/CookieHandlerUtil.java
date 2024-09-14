package com.empik.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.util.List;

@Log4j2
public class CookieHandlerUtil {

    private static final Logger log = LogManager.getLogger(CookieHandlerUtil.class);

    public static String getCookieUsingCookieHandler(String customUrl) {
        String cookies = "";
        try {
            // Instantiate CookieManager;
            // make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            java.net.CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL(customUrl);
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> interimCookies = cookieJar.getCookies();

            for (HttpCookie cookie: interimCookies) {
                cookies = cookies.concat(cookie + "; ");
            }
        } catch(Exception e) {
            log.info("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
        return cookies;
    }
}
