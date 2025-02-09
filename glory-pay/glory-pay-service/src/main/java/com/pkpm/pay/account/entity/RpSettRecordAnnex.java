/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pkpm.pay.account.entity;

import java.io.Serializable;

import com.pkpm.pay.common.core.entity.BaseEntity;
import com.pkpm.pay.common.core.enums.PublicEnum;

/**
 * 结算附件表
 * glory-cloud
 * @author：
 */
public class RpSettRecordAnnex extends BaseEntity implements Serializable {

	/** 是否删除 **/
	private String isDelete = PublicEnum.NO.name();

	/** 附件名称 **/
	private String annexName;

	/** 附件地址 **/
	private String annexAddress;

	/** 结算id **/
	private String settlementId;

	private static final long serialVersionUID = 1L;

	public RpSettRecordAnnex() {
		super();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public String getAnnexName() {
		return annexName;
	}

	public void setAnnexName(String annexName) {
		this.annexName = annexName == null ? null : annexName.trim();
	}

	public String getAnnexAddress() {
		return annexAddress;
	}

	public void setAnnexAddress(String annexAddress) {
		this.annexAddress = annexAddress == null ? null : annexAddress.trim();
	}

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}
}