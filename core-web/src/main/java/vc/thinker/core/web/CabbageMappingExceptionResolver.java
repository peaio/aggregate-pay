package vc.thinker.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 处理一下错误消息的打印
 * @author james
 *
 */
public class CabbageMappingExceptionResolver extends SimpleMappingExceptionResolver{

	private static final Logger log=LoggerFactory.getLogger(CabbageMappingExceptionResolver.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {

		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			Integer statusCode = determineStatusCode(request, viewName);
			
			if (statusCode != null) {
				//如果错误为500，打印错误消息
				if(HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode.intValue()){
					log.error("CabbageMappingExceptionResolver print error:",ex);
				}
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			return getModelAndView(viewName, ex, request);
		}
		else {
			return null;
		}
	}
}
