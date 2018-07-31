package com.vakhnenko.departments.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.vakhnenko.departments.entity.Variables.*;
import static com.vakhnenko.departments.utils.Strings.timeDateStering;

public class Https {

    private Https() {
    }

    public static Map<String, String> getSessionInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> resultMap = new HashMap();

        HttpSession session = request.getSession();
        resultMap.put("session_id", session.getId());
        resultMap.put("session_is_new", session.isNew() ? "Yes" : "No");

        resultMap.put("session_creation_date", new Date(session.getCreationTime()).toString());
        resultMap.put("session_last_access_date", new Date(session.getLastAccessedTime()).toString());

        AtomicInteger counter = (AtomicInteger) session.getAttribute("counter_session");
        if (counter == null) {
            counter = new AtomicInteger(0);
        }

        int count = counter.incrementAndGet();
        resultMap.put("counter_session", "" + count);
        session.setAttribute("counter_session", counter);

        return resultMap;
    }

    private static void ifCookiesIdsEmptyFillIt() {
        if (cookiesUsername.isEmpty()) {
            cookiesUsername = UUID.randomUUID().toString() + timeDateStering();
        }
        if (cookiesParrword.isEmpty()) {
            cookiesParrword = UUID.randomUUID().toString() + timeDateStering();
        }
        if (cookiesCounter.isEmpty()) {
            cookiesCounter = UUID.randomUUID().toString() + timeDateStering();
        }
    }

    public static Map<String, String> getCookiesInfo(HttpServletRequest request, HttpServletResponse response) {
        int finded = 0;
        boolean findedUsername = false;
        boolean findedPassword = false;
        boolean findedCounter = false;
        Map<String, String> resultMap = new HashMap();

        ifCookiesIdsEmptyFillIt();

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookiesUsername.equals(cookie.getName())) {
                finded++;
                findedUsername = true;
                resultMap.put(cookiesUsername, cookie.getValue().toString());
            }
            if (cookiesParrword.equals(cookie.getName())) {
                finded++;
                findedPassword = true;
                resultMap.put(cookiesParrword, cookie.getValue().toString());
            }
            if (cookiesCounter.equals(cookie.getName())) {
                finded++;
                findedCounter = true;
                int count;
                try {
                    count = Integer.parseInt(cookie.getValue().toString()) + 1;
                } catch (Exception e) {
                    count = 1;
                }
                cookie.setValue("" + count);
                response.addCookie(cookie);
                resultMap.put(cookiesCounter, "" + count);
            }

            if (finded == 3) {
                return resultMap;
            }
        }

        if (!findedUsername) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            response.addCookie(new Cookie(cookiesUsername, username));
            resultMap.put(cookiesUsername, username);
        }
        if (!findedPassword) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String password = BCrypt.hashpw(username, BCrypt.gensalt(12));
            response.addCookie(new Cookie(cookiesParrword, password));
            resultMap.put(cookiesParrword, password);
        }
        if (!findedCounter) {
            response.addCookie(new Cookie(cookiesCounter, "1"));
            resultMap.put(cookiesCounter, "1");
        }

        return resultMap;
    }
}
