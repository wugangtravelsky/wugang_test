package com.express.timer.impl;

import com.express.model.Express;
import com.express.model.OverDueExpress;
import com.express.service.OverDueExpressService;
import com.express.service.SmsService;
import com.express.timer.IExecuteTimer;
import com.express.util.PropertyUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

/**
 * Created by wshibiao on 2017/4/6.
 */
@Component
public class SendSMSTimer implements IExecuteTimer {

	@Autowired
	private SmsService smsService;
	@Resource
	private OverDueExpressService overDueExpressService;

	// 每天早上8点
	// @Scheduled(cron = "0 */1 * * * ?")
	@Scheduled(cron = "0 0 8 * * ?")
	@Override
	public void execute() {
		// 获取隔日件的收件人联系方式
		String smsContent = "";
		List<Express> expresses = overDueExpressService.getExpressWithOverDue();


	}

	// 每隔3小时
	@Scheduled(cron = "0 0 0/3 * * ?")
	public void sendMessage() {
		System.out.println("This is send message timer running");
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	public void sendOverDueMessage() throws IOException {
		OverDueExpress params = new OverDueExpress();
		params.setStatus("0");
		List<OverDueExpress> overDueExpressList = overDueExpressService.queryShelfListByParams(params);
		for (OverDueExpress overDueExpress : overDueExpressList) {
			Express express = overDueExpress.getExpress();
			String contact = express.getContact();// 获取手机号
			String verificationCode = DigestUtils.md5DigestAsHex((express.getVerificationCode() + PropertyUtil.getProperty("Salt")).getBytes())
					.substring(0, 6); // 获取验证码
			smsService.sendMessage(contact, "Your vertificationConde is " + verificationCode);
		}
	}
}
