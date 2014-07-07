package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IDeclarationInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IDeclarationInfoService;

@Service("declarationInfoService")
@Transactional
public class DeclarationInfoServiceImpl implements IDeclarationInfoService {
	@Resource(name = "declarationInfoDao")
	IDeclarationInfoDao declarationInfoDao;

	@Override
	public int save(DeclarationInfo dInfo) {
		// TODO Auto-generated method stub
		return declarationInfoDao.save(dInfo);
	}

	@Override
	public void update(DeclarationInfo dInfo) {
		// TODO Auto-generated method stub
		declarationInfoDao.update(dInfo);
	}

	@Override
	public DeclarationInfo getById(int id) {
		// TODO Auto-generated method stub
		return declarationInfoDao.getById(id);
	}
	
	@Override
	public List<DeclarationInfo> getDone(){
		return declarationInfoDao.getDone();
	}
	@Override
	public List<DeclarationInfo> getDonePage(int page,int num){
		return declarationInfoDao.getDonePage(page, num);
	}
	@Override
	public List<DeclarationInfo> getUnDone(){
		return declarationInfoDao.getUnDone();
	}
	@Override
	public List<DeclarationInfo> getUnDonePage(int page,int num){
		return declarationInfoDao.getUnDonePage(page, num);
	}
}
