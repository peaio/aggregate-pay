package vc.thinker.sys.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.dao.OfficeDao;
import vc.thinker.sys.dao.UserOfficeDao;

@Service
@Transactional(readOnly = true)
public class UserOfficeService {

	private Logger logger = LoggerFactory.getLogger(UserOfficeService.class);

	@Autowired
	private UserOfficeDao userOfficeDao;
	
	@Autowired
	private OfficeDao officeDao;
	
	public List<OfficeBO> findByUser(Long userId){
		return officeDao.findDataScopeOfficeByUid(userId);
	}

}
