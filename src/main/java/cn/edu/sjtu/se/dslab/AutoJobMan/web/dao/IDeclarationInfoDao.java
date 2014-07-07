package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;

public interface IDeclarationInfoDao {
	public int save(DeclarationInfo dInfo);
	public void update(DeclarationInfo dInfo);
	public DeclarationInfo getById(int id);
	public List<DeclarationInfo> getDone();
	public List<DeclarationInfo> getDonePage(int page,int num);
	public List<DeclarationInfo> getUnDone();
	public List<DeclarationInfo> getUnDonePage(int page,int num);
}
