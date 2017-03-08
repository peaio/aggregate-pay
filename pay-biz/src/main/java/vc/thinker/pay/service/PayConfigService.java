package vc.thinker.pay.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.pay.bo.PayConfigBO;
import vc.thinker.pay.dao.PayConfigDao;
import vc.thinker.pay.model.PayConfig;

@Service
@Transactional
public class PayConfigService implements InitializingBean,ApplicationContextAware{
	
	private final static Logger log=LoggerFactory.getLogger(PayConfigDao.class);
	
	@Autowired
	private PayConfigDao payConfigDao;
	
	private DicContent dicContent;
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			dicContent=applicationContext.getBean(DicContent.class);
			log.info("支付启动 DicContent配制 ，PayConfig 使用DicContent缓存");
		} catch (BeansException e) {
			log.error("未找到 DicContent配制 ，无法使用DicContent缓存");
		}
		
		if(dicContent != null){
			dicContent.setDicCache(PayConfigBO.class, new DicLoadData() {
				@Override
				public List<DicBase> loadDate() {
					List<PayConfigBO> list=payConfigDao.findAll();
					return Lists.newArrayList(list.toArray(new DicBase[list.size()])) ;
				}
			});
		}
	}
	
	/**
	 * 从缓存中读取
	 * @param mark
	 * @return
	 */
	public PayConfigBO findCacheByMark(String mark){
		if(dicContent == null){
			return findByMark(mark);
		}
		return dicContent.getDic(PayConfigBO.class, mark);
	}
	
	/**
	 * find by mark
	 * @param mark
	 * @param install
	 * @return
	 */
	public PayConfigBO findByMark(String mark,Boolean install){
		return this.payConfigDao.findByMark(mark, install);
	}

	public PayConfig findById(Long PayConfigId){
		return this.payConfigDao.findOne(PayConfigId);
	}
	/**
	 * find by mark
	 * @param notMarks
	 * @param install
	 * @return
	 */
	public List<PayConfigBO> findByMark(String []  notMarks,Boolean install){
		return this.payConfigDao.findByMark(notMarks, install);
	}

	public PayConfigBO findByMark(String mark){
		return this.payConfigDao.findByMark(mark);
	}

	public boolean save(PayConfig PayConfig) {
		/**
		 * init other field here
		 */
		this.payConfigDao.save(PayConfig);
		
		if(dicContent != null){
			dicContent.cleanDic(PayConfigBO.class);
		}
		return true;
	}

	public PayConfigBO getObjById(Long id) {
		return  this.payConfigDao.findOne(id);
	}

	public boolean delete(Long id) {
		this.payConfigDao.remove(id);
		
		if(dicContent != null){
			dicContent.cleanDic(PayConfigBO.class);
		}
		return true;
	}

	public boolean batchDelete(List<Serializable> PayConfigIds) {
		// TODO Auto-generated method stub
		for (Serializable id : PayConfigIds) {
			payConfigDao.remove((Long) id);
		}
		if(dicContent != null){
			dicContent.cleanDic(PayConfigBO.class);
		}
		return true;
	}

	public boolean update(PayConfig PayConfig) {
		this.payConfigDao.update(PayConfig);
		
		if(dicContent != null){
			dicContent.cleanDic(PayConfigBO.class);
		}
		return true;
	}

	public List<PayConfigBO> findInstalledAll(){
		return this.payConfigDao.findInstalledAll();

	}
}
