package com.express.test;

import com.express.model.Express;
import com.express.service.ExpressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by wshibiao on 2017/4/7.
 */
@ContextConfiguration(locations={"classpath*:config/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpressServiceImplTest {
    @Autowired
    private ExpressService expressService;

    @Test
    public void queryExpressInfo() throws Exception {
        List<Express> expresses=expressService.queryExpressInfo("b2",null);
        Express express1=expressService.getExpressInfoById(new Long(1));
        for (Express express :
                expresses) {
            System.out.println(express.getExpressNo());
            System.out.println(express.getConsignee());
        }

        // Express express2=new Express();
        // express2.setContact("18689597978");
        // express2.setConsignee("王试标");
        // express2.setCompany("sto");
        // expressService.createExpress(express2);


    }
    @Test
    public void updateExpress(){
        Express express1=expressService.getExpressInfoById(new Long(1));
        express1.setAddressDest("a1");
        express1.setContact("b2");
        express1.setAddressSource("c3");
        express1.setCompany("eee4");
        expressService.updateExpress(express1);
    }

}