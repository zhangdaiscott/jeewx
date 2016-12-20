package com.jeecg.qywx.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.jeecg.qywx.base.entity.QywxGroup;


/**
 * Description: 系统工具
 * @author junfeng.zhou
 * @version V1.0
 */
public class SystemUtil {
	/**
	 * 企业号系统ID[默认写死]
	 * @TODO
	 */
	public static final String QYWX_ACCOUNT_ID = "402880a94742e24e014742e8942b0002";
	
	/**
     * 拼树：
     * @param allList 所有可用的权限
     * @return
     */
    public static String listGroupToTree(List<QywxGroup> allList) {
        if(allList!=null && allList.size()>0) {
            List<TreeNode> treeList = new ArrayList<TreeNode>();
            for(QywxGroup group :allList) {
                TreeNode tn = new TreeNode();
                tn.setId(group.getId());
                String pId = "0";
                if(group.getParentid()!=null && !group.getParentid().equals("")){
                    pId = group.getParentid();
                }
                tn.setpId(pId);
                tn.setName(group.getName());
                tn.setOpen(false); //设置所有打开或所有闭关
                tn.setChecked(false);
                tn.setDoCheck(false);
                tn.setHalfCheck(false);
                tn.setParent(false);
                tn.setChkDisabled(false);
                tn.setNocheck(false);
                treeList.add(tn);
            }
            JSONArray jsonArray = JSONArray.fromObject(treeList);
            return jsonArray.toString();
        }
        return "";
    }
}
