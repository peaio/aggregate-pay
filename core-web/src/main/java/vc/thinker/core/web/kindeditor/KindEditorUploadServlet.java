package vc.thinker.core.web.kindeditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * kindeditor文件上传servlet
 * @author wangdawei
 *
 */
public class KindEditorUploadServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(KindEditorUploadServlet.class);
	
	private static final long serialVersionUID = -3594767205450922354L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		// 最大文件大小
		long maxSize = 1000000;
		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		
		try {
			// weed init
//			String masterAddress = getServletContext().getInitParameter("masterAddress");
//			String masterPort = getServletContext().getInitParameter("masterPort");
//			WeedFSClient fsClient = new WeedFSClient(masterAddress, masterPort);
//			// 针对上传文件进行校验
//			String dirName = request.getParameter("dir");
//			FileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setHeaderEncoding("UTF-8");
//			List<FileItem> items = upload.parseRequest(request);
//			Iterator<FileItem> itr = items.iterator();
//			while (itr.hasNext()) {
//				FileItem item = (FileItem) itr.next();
//				String fileName = item.getName();
//				long fileSize = item.getSize();
//				if (!item.isFormField()) {
//					// 检查文件大小
//					if (fileSize > maxSize) {
//						out.println(getError("上传文件大小超过限制。"));
//						return;
//					}
//					// 检查扩展名
//					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//					if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
//						out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
//						return;
//					}
//					// weed存储
//					JSONObject obj = new JSONObject();
//					try {
//						RequestResult result = fsClient.upload(
//								item.getInputStream(), item.getName(),
//								item.getContentType());
//						obj.put("error", 0);
//						obj.put("url", result.getUrl());
//					} catch (IOException e) {
//						logger.error("上传文件至文件服务器失败:", e);
//						out.println(getError("上传文件至文件服务器失败。"));
//					} finally {
//						out.print(obj.toJSONString());
//					}
//				}
//			}
		} catch (Exception e) {
			logger.error("文件上传过程出现异常:", e);
			out.println(getError("文件上传过程出现异常。"));
		}
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}