package vc.thinker.sys.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.core.dal.MyPage;
import vc.thinker.sys.bo.TemplateBO;
import vc.thinker.sys.dao.TemplateDao;
import vc.thinker.sys.model.Template;
import vc.thinker.sys.vo.TemplateVO;

@Service
@Transactional
public class TemplateService{
	
	@Autowired
	private TemplateDao templateDao;
	
	public Template getObjByMark(String mark) {
		return this.templateDao.findByMark(mark);
	}

	public boolean save(Template template) {
		/**
		 * init other field here
		 */
		try {
			this.templateDao.save(template);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Template getObjById(Long id) {
		Template template = this.templateDao.get(id);
		if (template != null) {
			return template;
		}
		return null;
	}

	public boolean delete(Long id) {
		try {
			this.templateDao.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean batchDelete(List<Serializable> templateIds) {
		// TODO Auto-generated method stub
		for (Serializable id : templateIds) {
			delete((Long) id);
		}
		return true;
	}

//	public IPageList list(IQueryObject properties) {
//		if (properties == null) {
//			return null;
//		}
//		String query = properties.getQuery();
//		String construct = properties.getConstruct();
//		Map params = properties.getParameters();
//		GenericPageList pList = new GenericPageList(Template.class,construct, query,
//				params, this.templateDao);
//		if (properties != null) {
//			PageObject pageObj = properties.getPageObj();
//			if (pageObj != null)
//				pList.doList(
//						pageObj.getCurrentPage() == null ? 0 : pageObj
//								.getCurrentPage(),
//						pageObj.getPageSize() == null ? 0 : pageObj
//								.getPageSize());
//		} else
//			pList.doList(0, -1);
//		return pList;
//	}

	public boolean update(Template template) {
		try {
			this.templateDao.update(template);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	public List<Template> query(String query, Map params, int begin, int max) {
//		return this.templateDao.query(query, params, begin, max);
//
//	}

//	@Override
//	public Template getObjByProperty(String construct, String propertyName,
//			Object value) {
//		// TODO Auto-generated method stub
//		return this.templateDao.getBy(construct, propertyName, value);
//	}
	
	public MyPage<TemplateBO> selectListByVO(MyPage<TemplateBO> page,TemplateVO vo){
		this.templateDao.selectListByVO(page,vo);
		return page;
	}
	
	public List<TemplateBO> verifyMark(Long id,String mark){
		return templateDao.verifyMark(id,mark);
	}
}
