package vc.thinker.core.web.view;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * FreeMarker 表态处理
 * @author james
 *
 */
public class FreeMarkerStaticUtil {

	private static final BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
	private static final TemplateHashModel staticModels = wrapper.getStaticModels();

	public static TemplateModel useStaticPacker(String packname) {
		try {
			return staticModels.get(packname);
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}
	};

}
