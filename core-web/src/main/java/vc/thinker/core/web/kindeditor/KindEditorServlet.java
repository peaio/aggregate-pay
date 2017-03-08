package vc.thinker.core.web.kindeditor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KindEditorServlet extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3594767205450922354L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.sendRedirect("/index.jsp");
	}
	
	
	
	
}