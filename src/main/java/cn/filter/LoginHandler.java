package cn.filter;

import cn.domain.RedisUtil;
import cn.po.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 胡建德
 */
@Component
public class LoginHandler implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name = request.getParameter("name");
        String userjson = null;
        if(name != "" && name != null){
            System.out.println(name);
            System.out.println(redisUtil);
            userjson = (String)redisUtil.get(name);
        }
        if(userjson != null){
            User user = JSONObject.parseObject(userjson,User.class);
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/client/myAccount");
        }
        return true;
    }
}
