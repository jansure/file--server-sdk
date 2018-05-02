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
public interface PkpmCloudNavigationService {

	/**
     * 获取导航子目录并控制层级
     * @param parentNavId
     * @return
     */
    List<PkpmCloudNavigation> getNavByParentId(Integer parentNavId);

}
