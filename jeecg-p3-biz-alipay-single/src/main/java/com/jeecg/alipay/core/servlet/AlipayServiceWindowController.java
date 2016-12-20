package com.jeecg.alipay.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.core.contants.AlipayServiceEnvConstants;
import com.jeecg.alipay.core.exception.MyException;
import com.jeecg.alipay.core.service.AlipayCoreService;
import com.jeecg.alipay.core.util.LogUtil;
import com.jeecg.alipay.util.RequestUtil;

//@Controller
//@RequestMapping("/gateway")
public class AlipayServiceWindowController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AlipayServiceWindowController.class);
	
	@Autowired
	private AlipayAccountService accountService;
	@Autowired
	private AlipayCoreService alipayCoreService;
	@RequestMapping(method = RequestMethod.POST)
	public void alipay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//动态初始化支付宝服务窗参数，包括APPID,PUBLIC_KEY,ALIPAY_PUBLIC_KEY
		AlipayConfig config = accountService.getAlipayConfig();
        if(config == null){
        	 MyException exception = new MyException("未获取到账号配置信息");
        	 exception.printStackTrace();
        	 return;
        }
		//支付宝响应消息  
        String responseMsg = "";

        //1. 解析请求参数
        Map<String, String> params = RequestUtil.getRequestParams(request);

        //打印本次请求日志，开发者自行决定是否需要
        LogUtil.log("支付宝请求串", params.toString());
        
      
        
        try {
            //2. 验证签名
            this.verifySign(params);

            //3. 执行业务逻辑
            responseMsg = alipayCoreService.excute(params);
            if(responseMsg == null){
            	responseMsg = "";
            }
        } catch (AlipayApiException alipayApiException) {
            //开发者可以根据异常自行进行处理
            alipayApiException.printStackTrace();

        } catch (Exception exception) {
            //开发者可以根据异常自行进行处理
            exception.printStackTrace();

        } finally {
            //5. 响应结果加签及返回
            try {
                //对响应内容加签
            	//logger.info(" - ALIPAY_PUBLIC_KEY - " + AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);
            	//logger.info(" - PRIVATE_KEY - " + AlipayServiceEnvConstants.PRIVATE_KEY);
                responseMsg = AlipaySignature.encryptAndSign(responseMsg,
                    AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY,
                    AlipayServiceEnvConstants.PRIVATE_KEY, AlipayServiceEnvConstants.CHARSET,
                    false, true);

                //http 内容应答
                response.reset();
                response.setContentType("text/xml;charset=GBK");
                PrintWriter printWriter = response.getWriter();
                printWriter.print(responseMsg);
                response.flushBuffer();

                //开发者自行决定是否要记录，视自己需求
                LogUtil.log("开发者响应串", responseMsg);

            } catch (AlipayApiException alipayApiException) {
                //开发者可以根据异常自行进行处理
                alipayApiException.printStackTrace();
            }
        }
    }

    /**
     * 验签
     * 
     * @param request
     * @return
     */
    private void verifySign(Map<String, String> params) throws AlipayApiException {

        if (!AlipaySignature.rsaCheckV2(params, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY,
            AlipayServiceEnvConstants.SIGN_CHARSET)) {

            throw new AlipayApiException("verify sign fail.");
        }
    }

}
