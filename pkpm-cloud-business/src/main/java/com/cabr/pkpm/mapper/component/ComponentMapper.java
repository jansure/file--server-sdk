package com.cabr.pkpm.mapper.component;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ComponentMapper {
	/**
     * 根据componentId和componentType获取对应的子产品名
     * @param componentId,componentId
     * @return
     */
    String getComponentName(@Param("componentId") Integer componentId, @Param("componentType") Integer componentType);
}
