package com.jeecg.alipay.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeecg.alipay.base.dao.AlipayMessagelogDao;
import com.jeecg.alipay.base.entity.AlipayMessageLog;
import com.jeecg.alipay.util.SystemUtil;

@Controller
@RequestMapping("/alipay/alipayMessagelog")
public class AlipayMessageLogController extends BaseController{
	@Autowired
	private AlipayMessagelogDao alipayMessagelogDao;
	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@ModelAttribute AlipayMessageLog query, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<AlipayMessageLog> list = alipayMessagelogDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("alipayMessageLog", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "alipay/base/alipayMessagelog-list.vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	@RequestMapping(params = "toDetail", method = RequestMethod.GET)
	public void alipayMessageLogDetail(@RequestParam(required = true, value = "id") String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		// update-start--Author:chenchunpeng  Date:20160715 for：修改群发记录详情页面的页面名
		String viewName = "alipay/base/alipayMessagelog-detail.vm";
		AlipayMessageLog alipayMessageLog = alipayMessagelogDao.get(id);
		// update-end--Author:chenchunpeng  Date:20160715 for：修改群发记录详情页面的页面名
		velocityContext.put("alipayMessagelog", alipayMessageLog);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}
}
