//package com.freedom.crawler.sys.security;
//
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Objects;
//import java.util.zip.GZIPOutputStream;
//
///**
// * Created by Thor on 2018/1/18.
// */
//@Configuration
//public class WebSecurityConfig extends WebMvcConfigurerAdapter {
//    @Autowired
//    private UserDOMapper userDOMapper;
//
//    private static final String PARAM_TOKEN = "token";
//
//    private static final String LOGIN_URL = "/login/login";
//
//    @Bean
//    public SecurityInterceptor getSecurityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//
//        addInterceptor.excludePathPatterns(LOGIN_URL);//排除登录配置
//
//        /* swagger相关的请求全部不拦截 */
//        addInterceptor.excludePathPatterns("/swagger**");
//        addInterceptor.excludePathPatterns("/v2/**");
//        addInterceptor.excludePathPatterns("/swagger-resources/**");
//        addInterceptor.excludePathPatterns("/webjars/**");
//
//        addInterceptor.excludePathPatterns("/oss/upload/publicFile");//上传
//        addInterceptor.excludePathPatterns("/s3/uploadMsgFile");//上传
//        addInterceptor.excludePathPatterns("/group/groupAll");//上传
//
//        addInterceptor.addPathPatterns("/**");//拦截配置
//    }
//
//    private class SecurityInterceptor extends HandlerInterceptorAdapter {
//        @Override
//        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)
//                throws Exception {
//            String token = httpServletRequest.getParameter(PARAM_TOKEN);
//
//            UserDO userDO = userDOMapper.selectByToken(token);
//
//            if (userDO == null) {
//                response(httpServletResponse, ResultCode.AUTH_FAIL);
//                return false;
//            } else {
//                if (Objects.equals(userDO.getIsDeleted(), IsDelete.NO)) {
//                    return true;
//                } else {
//                    response(httpServletResponse, ResultCode.AUTH_FAIL);
//                    return false;
//                }
//            }
//        }
//    }
//
//    private void response(HttpServletResponse httpServletResponse, ResultCode resultCode) throws IOException {
//        Gson gson = new Gson();
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        GZIPOutputStream gzip = new GZIPOutputStream(byteArrayOutputStream);
//
//        byte[] bytes = gson.toJson(ResultUtil.error(resultCode)).getBytes();
//        gzip.write(bytes);
//        gzip.close();
//
//        bytes = byteArrayOutputStream.toByteArray();
//
//        httpServletResponse.setHeader("Content-Encoding", "gzip");
//        httpServletResponse.setContentType("application/json; charset=utf-8");
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentLength(bytes.length);
//        httpServletResponse.getOutputStream().write(bytes);
//    }
//
//}
//
