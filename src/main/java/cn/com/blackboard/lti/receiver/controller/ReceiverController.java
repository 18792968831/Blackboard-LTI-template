package cn.com.blackboard.lti.receiver.controller;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.blackboard.lti.receiver.exception.GenericLTIException;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthValidator;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;

/**
 * ReceiverController
 * <p>
 * LTI LTI Provider的接收器
 * OAuth认证后,展示所有参数
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */
@Controller
@SuppressWarnings({"rawtypes","unused"})
public class ReceiverController {

	private static final String OAUTH_CONSUMER_SECRET = "oauth_consumer_secret";

	@RequestMapping(value = { "/showParam" })
	private ModelAndView showParam(HttpServletRequest req) throws GenericLTIException {
		
		ModelAndView mv = new ModelAndView();
		
		if(authenticate(req)) {
			//循环出参
			HashMap<String, String> headerMap = new HashMap<String, String>();
			HashMap<String, String> bodyMap = new HashMap<String, String>();
			
			Enumeration header = req.getHeaderNames();
			while (header.hasMoreElements()) {
				String name = (String) header.nextElement();
				String value = req.getHeader(name);
				headerMap.put(name, value);
			}
			Enumeration enu = req.getParameterNames();
			while (enu.hasMoreElements()) {
				String paraName = (String) enu.nextElement();
				bodyMap.put(paraName, req.getParameter(paraName));
			}
			
			mv.addObject("headerMap", headerMap);
			mv.addObject("bodyMap", bodyMap);

		}

		mv.setViewName("show");
		mv.addObject("method", req.getMethod());
		return mv;
	}

	public boolean authenticate(HttpServletRequest request) throws GenericLTIException {

		OAuthValidator validator = new SimpleOAuthValidator();
		String oauth_consumer_key = null;

		try {
			OAuthMessage oauthMessage = OAuthServlet.getMessage(request, null);
			oauth_consumer_key = oauthMessage.getConsumerKey();

			// callback URL syntax per LTI spec
			OAuthConsumer consumer = new OAuthConsumer("about:blank", oauth_consumer_key, OAUTH_CONSUMER_SECRET, null);

			String signatureMethod = oauthMessage.getSignatureMethod();
			String signature = URLDecoder.decode(oauthMessage.getSignature(), "UTF-8");

			// all tokens are empty
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			validator.validateMessage(oauthMessage, accessor);
			
		} catch (Exception e) {
			throw new GenericLTIException(e);
		}
		return true;
	}
}
