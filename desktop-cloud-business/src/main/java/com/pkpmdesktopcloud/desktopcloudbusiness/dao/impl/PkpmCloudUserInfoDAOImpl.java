package com.pkpmdesktopcloud.desktopcloudbusiness.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkpmdesktopcloud.desktopcloudbusiness.dao.PkpmCloudUserInfoDAO;
import com.pkpmdesktopcloud.desktopcloudbusiness.dao.mapper.PkpmCloudUserInfoMapper;
import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudUserInfo;

import javax.annotation.Resource;

@Repository
public class PkpmCloudUserInfoDAOImpl implements PkpmCloudUserInfoDAO{
	
	@Resource
	private PkpmCloudUserInfoMapper userMapper;
	
	/* (非 Javadoc)  
	 *   
	 *   
	 * @param userInfo  
	 * @see com.pkpmdesktopcloud.desktopcloudbusiness.dao.UserDAO#saveUserInfo(com.pkpmdesktopcloud.desktopcloudbusiness.domain.UserInfo)  
	 */  
	@Override
	public void saveUserInfo(PkpmCloudUserInfo userInfo) {
		
		userInfo.setUserId(null);
		userMapper.insert(userInfo);
	}


	  
	/* (非 Javadoc)  
	 *   
	 *   
	 * @param userName
	 * @param userMobileNumber
	 * @param userEmail
	 * @return  
	 * @see com.pkpmdesktopcloud.desktopcloudbusiness.dao.UserDAO#findByUserNameOrTelephoneOrUserEmail(java.lang.String, java.lang.String, java.lang.String)  
	 */  
	@Override
	public PkpmCloudUserInfo findByUserNameOrTelephoneOrUserEmail(String userName, String userMobileNumber, String userEmail) {
		
		PkpmCloudUserInfo userInfo = new PkpmCloudUserInfo();
		userInfo.setUserName(userName);
		List<PkpmCloudUserInfo> list = userMapper.findUserInfoList(userInfo);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		userInfo = new PkpmCloudUserInfo();
		userInfo.setUserMobileNumber(userMobileNumber);
		list = userMapper.findUserInfoList(userInfo);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		userInfo = new PkpmCloudUserInfo();
		userInfo.setUserEmail(userEmail);
		list = userMapper.findUserInfoList(userInfo);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}


	  
	/* (非 Javadoc)  
	 *   
	 *   
	 * @param userInfo
	 * @return  
	 * @see com.pkpmdesktopcloud.desktopcloudbusiness.dao.UserDAO#updateUserInfo(com.pkpmdesktopcloud.desktopcloudbusiness.domain.UserInfo)  
	 */  
	@Override
	public int updateUserInfo(PkpmCloudUserInfo userInfo) {
		
		return userMapper.update(userInfo);
	}


	  
	/* (非 Javadoc)  
	 *   
	 *   
	 * @param userID
	 * @return  
	 * @see com.pkpmdesktopcloud.desktopcloudbusiness.dao.UserDAO#getUserById(int)  
	 */  
	@Override
	public PkpmCloudUserInfo getUserById(int userID) {
		PkpmCloudUserInfo userInfo = new PkpmCloudUserInfo();
		userInfo.setUserId(userID);
		
		List<PkpmCloudUserInfo> list = userMapper.findUserInfoList(userInfo );
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

}
