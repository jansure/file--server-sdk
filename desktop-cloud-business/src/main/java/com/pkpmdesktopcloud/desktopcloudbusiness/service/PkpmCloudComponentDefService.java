package com.pkpmdesktopcloud.desktopcloudbusiness.service;

import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudComponentDef;
import com.pkpmdesktopcloud.desktopcloudbusiness.dto.ComponentVO;

import java.util.List;

/**
 * 产品接口类
 * @author yangpengfei
 * @date 2017/12/25
 *
 */
public interface PkpmCloudComponentDefService {
	
	/**
	 * 根据产品类型id获取自动配置的components
	 * @param productType 父类id
	 * @return
	 */
    List<ComponentVO> getComponentListByProductType(Integer productType);
    
    /**
	 * 
	 * @Title: getList  
	 * @Description: 获取所有列表
	 * @param @return    参数  
	 * @return List<ComponentVO>    返回类型  
	 * @throws
	 */
	List<PkpmCloudComponentDef> getList();

}
