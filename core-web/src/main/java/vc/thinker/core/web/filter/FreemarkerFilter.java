package vc.thinker.core.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.ViewRendererServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class FreemarkerFilter implements Filter {

    private Locale locale;
    
    private String springServletName;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String localeStr = filterConfig.getInitParameter("locale");
//        String springServletName = filterConfig.getInitParameter("springServletName");
//        if(StringUtils.isBlank(springServletName)){
//        	throw new ServletException("springServletName is null");
//        }
//        this.springServletName=springServletName;
        if(StringUtils.isNotBlank(localeStr)){
            locale = new Locale(localeStr);
        }else {
            locale = Locale.getDefault();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res = (HttpServletResponse)response;
            String name = req.getRequestURI();
            name = name.substring(1, name.lastIndexOf(".ftl"));
            FreeMarkerViewResolver viewResolver = webApplicationContext.getBean(FreeMarkerViewResolver.class);
            FreeMarkerView view = (FreeMarkerView) viewResolver.resolveViewName(name, locale);
            
            if(view == null){
            	throw new RuntimeException("view "+name+"not find");
            }
            //关闭暴露，这里和之前写的属性冲突
            view.setExposeRequestAttributes(false);
            view.setExposeSessionAttributes(false);
            view.setExposeSpringMacroHelpers(false);
            
            @SuppressWarnings("unchecked")
            Map<String, Object> model = (Map<String, Object>) request.getAttribute(ViewRendererServlet.MODEL_ATTRIBUTE);
            //viewResolver.setAllowRequestOverride(true);
            view.render(model, req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }
    
}