package com.lwg.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lwg.domain.User;

public class LoginInte implements HandlerInterceptor{

	/**
	 * 不拦截的后缀
	 */
	private List<String> excludeMappingUrl;//不拦截的后缀通过Spring-mvc.xml配置文件中配置



	public List<String> getExcludeMappingUrl() {
		return excludeMappingUrl;
	}

	public void setExcludeMappingUrl(List<String> excludeMappingUrl) {
		this.excludeMappingUrl = excludeMappingUrl;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub


	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		String url = req.getRequestURI();//获取请求的URL
		String postFix = null;       //前缀
		// 查看是否是静态文件，如果是静态文件，放行
		if(url!=null&&url!=""){
			int index = url.lastIndexOf(".");//获取.操作最后出现的位置的下标
			if(index > -1){//说明索引的位置可以以“.”开头
				postFix = url.substring(index);//拿到该URL的后缀
				if(url!=null&&url!=""&&excludeMappingUrl.contains(postFix)){
					//静态文件，默认处理
					return true;
				}
			}else {
				String newurl=url.substring(9);
				if(!excludeMappingUrl.contains(newurl)){
					//判断session是否存在
					HttpSession session = req.getSession();
					if(session.getAttribute("user")==null){
						System.out.println("Interceptor：跳转到login页面！");
						req.getRequestDispatcher("/WEB-INF/admin/AdminLogin.jsp").forward(req, arg1);
						return false;
					}
					return true;//包含以上两个URL是开始就进入的页面操作
				}else {
					return true;
				}
			}
		}
		return true;

	}

}
