package com.pkpmdesktopcloud.desktopcloudbusiness.service;

import java.util.List;

import com.gateway.common.dto.user.UserInfoForChangePassword;
import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudSubscription;
import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudUserInfo;

public interface PkpmCloudUserInfoService {

	void saveUserInfo(PkpmCloudUserInfo userInfo);
	
	boolean updateUserInfo(PkpmCloudUserInfo userInfo);

	/**
	 * @return
	 * @author xuhe
	 */
	String changeUserPassword(UserInfoForChangePassword newUserInfo, List<PkpmCloudSubscription> subsList);
	
	public PkpmCloudUserInfo findUser(Integer userID);

	PkpmCloudUserInfo findByUserNameOrTelephoneOrUserEmail(String name);

    List<PkpmCloudUserInfo> userList(PkpmCloudUserInfo userInfo);

	List<PkpmCloudUserInfo> userList();
}
