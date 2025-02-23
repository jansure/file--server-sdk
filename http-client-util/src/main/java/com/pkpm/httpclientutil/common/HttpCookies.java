package com.pkpm.httpclientutil.common;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * 封装Cookie
 *
 * @author zhangshuai
 * @version 1.0
 */
public class HttpCookies {

    /**
     * 使用httpcontext，用于设置和携带Cookie
     */
    private HttpClientContext context;

    /**
     * 储存Cookie
     */
    private CookieStore cookieStore;

    private HttpCookies() {
        this.context = new HttpClientContext();
        this.cookieStore = new BasicCookieStore();
        this.context.setCookieStore(cookieStore);
    }

    public static HttpCookies custom() {
        return new HttpCookies();
    }

    public HttpClientContext getContext() {
        return context;
    }

    public HttpCookies setContext(HttpClientContext context) {
        this.context = context;
        return this;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public HttpCookies setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
        return this;
    }


}
