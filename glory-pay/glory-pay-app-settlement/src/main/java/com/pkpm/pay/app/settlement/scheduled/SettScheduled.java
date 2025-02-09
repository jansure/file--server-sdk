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
package com.pkpm.pay.app.settlement.scheduled;


/**
 * 结算定时任务接口.
 * glory-cloud
 * @author
 */
public interface SettScheduled {

	/**
	 * 发起每日待结算数据汇总.
	 */
	public void launchDailySettCollect();
	
	/**
	 * 发起定期自动结算.
	 */
	public void launchAutoSett();

}
