package cn.filter;

import cn.po.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 由于springmvc无法对jsp文件进行拦截所以通过Filter拦截
 */


public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对servletRequest进行转换来获取session
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(1);
        if(user == null){
            request.getRequestDispatcher("/client/login.jsp").forward(servletRequest,servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
