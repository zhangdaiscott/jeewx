package weixin.idea.huodong.service.impl;



import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.idea.huodong.service.PrizeRecordServiceI;

@Service("prizeRecordService")
@Transactional
public class PrizeRecordServiceImpl extends CommonServiceImpl implements PrizeRecordServiceI {
	
}