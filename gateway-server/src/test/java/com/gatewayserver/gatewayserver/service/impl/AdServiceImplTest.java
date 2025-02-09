package com.gatewayserver.gatewayserver.service.impl;

import com.gateway.common.domain.CommonRequestBean;
import com.gateway.common.domain.PkpmCloudUserInfo;
import com.gateway.common.dto.ad.AdComputer;
import com.gateway.common.dto.ad.AdUser;
import com.gatewayserver.gatewayserver.dao.impl.PkpmCloudUserInfoDAOImpl;

import org.apache.http.util.Asserts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: xuhe
 * @Date: 2018/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServiceImplTest {

    @Autowired
    AdServiceImpl adService;

    @Autowired
    PkpmCloudUserInfoDAOImpl userInfoDAO;


    @Test
    public void addAdUser() {

        CommonRequestBean requestBean = new CommonRequestBean();
        requestBean.setAdId(4);
        requestBean.setProjectId("80367f2e346747139970a46e3e50c055");
        requestBean.setUserId(100);
        requestBean.setUserName("xuhetest");
        requestBean.setSubsId(1L);
        requestBean.setUserEmail("new@163.com");

        requestBean.setUserMobileNumber("1111177777");
        requestBean.setUserLoginPassword("Abc=1234");
        System.out.println(requestBean.getAdId());

        adService.addAdUser(requestBean);
    }

    @Test
    public void updateAdUser() {

        PkpmCloudUserInfo userInfo = userInfoDAO.selectById(2);
        CommonRequestBean requestBean = new CommonRequestBean();
        BeanUtils.copyProperties(userInfo, requestBean);
        requestBean.setAdId(1);
        requestBean.setUserName("xuhe10");
        requestBean.setUserEmail("new@163.com");
        requestBean.setUserMobileNumber("1111177777");
        requestBean.setUserLoginPassword("Abc=1234");
        System.out.println(requestBean.getAdId());

        adService.updateAdUser(requestBean);

    }
    @Test
    public void getUserCountByAdId(){
        int count =adService.getUserCountByAdIpAddress("114.115.223.153");
        System.out.println(count);
    }

    @Test
    public void selectUsersByAdId(){
        List<AdUser> adUsers = adService.getUsersByAdId(1);
        System.out.println(Arrays.toString(adUsers.toArray()));


    }

    @Test
    public void checkUser() {
        boolean result = adService.checkUser("xuhe10", 1);
        Asserts.check(result, "正确");
    }

    @Test
    public void selectComputersByAdId(){
        List<AdComputer> computers = adService.getComputersByAdId(1);
        System.out.println(Arrays.toString(computers.toArray()));

    }

    @Test
    public void deleteComputer() {
        adService.deleteComputer("123456789123456789", 1);

    }

    @Test
    public void check(){
        Boolean aBoolean = adService.checkComputer("pkpm-all-25",1);
        System.out.println(aBoolean);
    }

}