package com.pkpmdesktopcloud.desktopcloudbusiness.service;

import java.util.List;
import java.util.Map;

import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudNavigation;
import com.pkpmdesktopcloud.desktopcloudbusiness.domain.PkpmCloudProductDef;
import com.pkpmdesktopcloud.desktopcloudbusiness.dto.ComponentVO;

/**
 * 产品接口类
 * @author yangpengfei
 * @date 2017/12/25
 *
 */
public interface ProductService {
	/**
	 * 根据父id获取子目录
	 * @param productType 父类id
	 * @return
	 */
	List<PkpmCloudProductDef> getProductByType(Integer productType);
	
    /**
     * 返回产品套餐类型列表
     * @return
     */
    List<Map<String, Object>> getProductTypeList();
    
    /**
     * 根据配置项类型返回对应的所有配置项
     * @param componentType
     * @return
     */
    List<Map<String, Object>> getConfigByComponentType(Integer componentType);

}
