package com.gatewayserver.gatewayserver.service.impl;

import com.gateway.common.domain.CommonRequestBean;
import com.gateway.common.dto.Desktop;
import com.gatewayserver.gatewayserver.service.DesktopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Desktop Service Impl 单元测试
 *
 * @author xuhe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DesktopServiceImplTest {

    @Autowired
    DesktopService desktopService;

    /*
    *@author xuhe
    */
    @Test
    public void createAdAndDesktopTest() throws Exception {


        CommonRequestBean info = new CommonRequestBean();
        info.setUserName("glglgl");
        info.setUserLoginPassword("Abc=5678");
        info.setUserEmail("cherishpf@163.com");
        info.setProjectId("9487c2cb4c4d4c828868098b7a78b497");
        info.setImageId("997488ed-fa23-4671-b88c-d364c0405334");// 默认的镜像Workspace-User-Template
        info.setDataVolumeSize(100);
        info.setHwProductId("workspace.c2.large.windows");
        info.setGloryProductName("XUHE-TEST");
        info.setOuName("pkpm");
        info.setAdId(1);
        info.setUserId(145);
        info.setSubsId(4L);
        info.setOperatorStatusId(14);// AD
        desktopService.createAdAndDesktop(info);

    }

    /*
    *@author xuhe
    */
    @Test
    public void changeDesktopSpecTest() {
        CommonRequestBean requestBean = new CommonRequestBean();
        requestBean.setUserName("glglgl");
        requestBean.setUserLoginPassword("Abc=5678");
        requestBean.setUserEmail("cherishpf@163.com");
        requestBean.setProjectId("9487c2cb4c4d4c828868098b7a78b497");
        requestBean.setImageId("997488ed-fa23-4671-b88c-d364c0405334");// 默认的镜像Workspace-User-Template
        requestBean.setDataVolumeSize(100);
        requestBean.setHwProductId("workspace.c2.xlarge.windows");
        requestBean.setGloryProductName("PLGAAA");
        requestBean.setOuName("pkpm");
        requestBean.setAdId(1);
        requestBean.setUserId(145);
        requestBean.setSubsId(4L);
        Desktop desktop = new Desktop();
        desktop.setDesktopId("8ff115ca-61bb-4573-822b-ab143f293d2e");
        desktop.setProductId("workspace.c2.xlarge.windows");
        List desktopList = new ArrayList<Desktop>();
        desktopList.add(desktop);
        requestBean.setDesktops(desktopList);
        desktopService.changeDesktopSpec(requestBean);
    }

}