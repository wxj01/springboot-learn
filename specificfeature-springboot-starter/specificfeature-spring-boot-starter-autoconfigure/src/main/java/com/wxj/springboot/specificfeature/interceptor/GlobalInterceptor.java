package com.wxj.springboot.specificfeature.interceptor;

import com.wxj.springboot.specificfeature.FeatureContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class GlobalInterceptor implements HandlerInterceptor {

    //http://dev-finance.sany.com.cn/client/muti1#/index

    public static final String SPLIT_CHARACTER_01 = "\\?";
    public static final String SPLIT_CHARACTER_02 = "&";
    public static final String URI_ERROR = "error";

    private String branchName;
    private String branchRule;

    public GlobalInterceptor(String branchName, String branchRule) {
        this.branchName = branchName;
        this.branchRule = branchRule;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("referer");
        // 从uri 获取featureName 参数
        String[] split = referer.split("/");
        if (split != null && split.length > 0) {
            String branchName = split[split.length - 1];
            System.out.println(branchName);
            if (!URI_ERROR.equals(branchName) && branchName.contains(SPLIT_CHARACTER_01)) {
                branchName = branchName.split(SPLIT_CHARACTER_01)[0];
            }

            if (!URI_ERROR.equals(branchName) && branchName.contains(SPLIT_CHARACTER_02)) {
                branchName = branchName.split(SPLIT_CHARACTER_02)[0];
            }

            if (!URI_ERROR.equals(branchName) && StringUtils.isNotEmpty(branchName)) {
                FeatureContext.set(branchName);
//                FeatureContext.set("consul-provider-payment-8008");
            }

        }

        return true;

    }
}