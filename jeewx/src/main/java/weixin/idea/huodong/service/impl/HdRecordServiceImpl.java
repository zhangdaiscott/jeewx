package weixin.idea.huodong.service.impl;



import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.idea.huodong.service.HdRecordServiceI;

@Service("hdRecordService")
@Transactional
public class HdRecordServiceImpl extends CommonServiceImpl implements HdRecordServiceI {
	
}