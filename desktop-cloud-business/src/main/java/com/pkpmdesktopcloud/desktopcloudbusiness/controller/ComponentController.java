package com.pkpmdesktopcloud.desktopcloudbusiness.controller;


import com.desktop.utils.page.ResultObject;
import com.pkpmdesktopcloud.desktopcloudbusiness.dto.ComponentVO;
import com.pkpmdesktopcloud.desktopcloudbusiness.service.PkpmCloudComponentDefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@RestController
@Api("产品详情")
@RequestMapping(value = "component")
public class ComponentController {
	
	@Resource
	private PkpmCloudComponentDefService componentDefService;
	
	/**
	 * 根据产品类型id获取自动配置的components
	 * 
	 * @param productType
	 * @return
	 */
	@ApiOperation("根据id获取产品详情信息")
	@ResponseBody
	@RequestMapping(value = "/subComponents", method = RequestMethod.POST)
	public ResultObject getComponentByProductType(Integer productType, HttpServletResponse response) {
		// 允许跨域调用
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		List<ComponentVO> componentVOList = componentDefService.getComponentListByProductType(productType);
		
		Map<Integer, List<ComponentVO>> componentsMap = new LinkedHashMap<Integer, List<ComponentVO>>();
		
		// 每种产品配置类别中的配置项
		if(componentVOList != null && componentVOList.size() > 0) {
			componentsMap = componentVOList.stream().collect(Collectors.groupingBy(ComponentVO::getComponentType));
		}
		
		return ResultObject.success(componentsMap);
	}
	
}
