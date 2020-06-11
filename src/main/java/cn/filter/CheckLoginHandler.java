package cn.filter;

import cn.domain.RedisUtil;
import cn.po.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对用户进行检测
 * @author 胡建德
 */
public class CheckLoginHandler implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        if(user == null){
            String userjson =(String) redisUtil.get(name);
            if(userjson != null){
                user = JSONObject.parseObject(userjson,User.class);
                request.getSession().setAttribute("user",user);
            }else{
                response.sendRedirect(request.getContextPath()+"/client/login");
            }
        }
        return true;
    }
}
