package com.pkpmdesktopcloud.desktopcloudbusiness.service;

import com.pkpmdesktopcloud.desktopcloudbusiness.dto.MyProduct;
import com.pkpmdesktopcloud.desktopcloudbusiness.page.PageBean;

public interface SubsDetailsService {
	
	//产品列表
	PageBean<MyProduct> showList(int userId, Integer currentPage, Integer pageSize);

}
