package vc.thinker.core.web.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 用于配合 限制 session 数量做的过滤器
 * @author james
 *
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        
        Object kickoutUrl=session.getAttribute(FormAuthenticationFilter.KICKOUT_URL_SESSION_ATTRIBUTE_KEY);
        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (kickoutUrl != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, (String)kickoutUrl);
            return false;
        }
        return true;
    }
}