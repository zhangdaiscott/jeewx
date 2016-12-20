<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <link type="text/css" rel="stylesheet" href="./plug-in/weixin/css/style.css">
     <div class="funcList" id="func">
            <ul class="list">
            	<li>
                    <img src="./plug-in/weixin/img/weixin.jpg" width="95" height="98">
                    <h3>微信公众号</h3>
                    <p>微信公众号管家，一站式管理，一步到位!<font color="red">[开源]</font></p>
                </li>
                <li>
                    <img src="./plug-in/weixin/img/wxqy.jpg" width="95" height="98">
                    <h3>微信企业号</h3>
                    <p>微信企业号管家，一站式管理，一步到位!<font color="red">[开源]</font></p>
                </li>
                 <li>
                   	<img src="./plug-in/weixin/img/alipay.jpg" width="95" height="98">
                   	<h3>支付服务窗</h3>
                   	<p>支付窗管家，一站式管理，一步到位!<font color="red">[开源]</font></p>
               	</li>
               	<li>
                    <img src="./plug-in/weixin/img/qq.jpg" width="95" height="98">
                    <h3>QQ公众号</h3>
                    <p>QQ公众号管家，一站式管理，一步到位!<font color="red">[未开源]</font></p>
                </li>
                	<li>
                    <img src="./plug-in/weixin/img/weibo.jpg" width="110" height="98">
                    <h3>微博</h3>
                    <p>微博管家，一站式管理，一步到位!<font color="red">[未开源]</font></p>
                </li>
               	
                 <a href="menuManagerController.do?list&clickFunctionId=4028d881436d514601436d5884be019d" target="_blank">
                     <li><img src="./plug-in/weixin/img/func06.jpg">
	                    <h3>自定义菜单</h3>
	                    <p>通过形象的菜单界面引导顾客进行微信互动操作。
						</p>
                	</li>
                </a>
                <a href="newsTemplateController.do?list" target="_blank">
	                <li>
	                    <img src="./plug-in/weixin/img/func05.jpg">
	                    <h3>图文素材</h3>
	                    <p>定期推送，图文并<br>茂，免费发布</p>
	                </li>
                </a>
                 
                    <a href="zpController.do?goZhuanpan&accountid=${sessionScope.WEIXIN_ACCOUNT.id}&openId=oGCDRjvr9L1NoqxbyXLReCVYVyV0" target="_blank">
                    <li><img  src="./plug-in/weixin/img/func08.jpg">
                    <h3>大转盘</h3>
                    <p>快乐大转盘<br>幸运落谁家</p>
                    </li>
                    </a>
                
                
                	<a href="zpController.do?goGglNew&accountid=${sessionScope.WEIXIN_ACCOUNT.id}&openId=oGCDRjvr9L1NoqxbyXLReCVYVyV0" target="_blank">
                    <li><img src="./plug-in/weixin/img/func10.jpg">
                    <h3>刮刮乐</h3>
                    <p>趣味游戏<br>吸引用户参与 沉淀用户</p>
                    </li>
                    </a>
                
                <a href="cmsController.do?goPage&page=index" target="_blank">
	                <li>
	                    <img src="./plug-in/weixin/img/func01.jpg" width="104" height="107">
	                    <h3>微网站 </h3>
	                    <p>5分钟轻松建站<br>打造酷炫微官网</p>
	                </li>
                </a>
                <a href="cmsController.do?goPage&page=photoAlbum" target="_blank">
                	<li>
                    	<img src="./plug-in/weixin/img/func04.jpg" width="106" height="107">
                    	<h3>微相册</h3>
                    	<p>各行各业<br> 照片展现轻松搞定</p>
                	</li>
                </a>
               
               	<li>
                   	<img src="./plug-in/weixin/img/func04.jpg" width="106" height="107">
                   	<h3>微翻译</h3>
                   	<p>微翻译</p>
               	</li>
                <li>
                    <img src="./plug-in/weixin/img/func11.jpg">
                    <h3>微投票</h3>
                    <p>用户通过微信问答<font color="red">[商业]</font></p>
                </li>
                <li>
                    <img src="./plug-in/weixin/img/func14.jpg">
                    <h3>微信墙 </h3>
                    <p>活跃现场气氛，让粉丝涨起来<font color="red">[商业]</font></p>
                </li>
                <li>
                    <img src="./plug-in/weixin/img/func15.jpg">
                    <h3>微社区</h3>
                    <p>移动社区交流<font color="red">[商业]</font></p>
                </li>
                 <li>
                    <img src="./plug-in/weixin/img/func02.jpg" width="105" height="107">
                    <h3>微会员</h3>
                    <p>方便携带 永不挂失<br>消费积分<font color="red">[商业]</font></p>
                </li>
              
                <li>
                    <img src="./plug-in/weixin/img/func03.jpg" width="107" height="107">
                    <h3>微商城</h3>
                    <p>小微信也有大商城<br>，移动营销<font color="red">[商业]</font></p>
                </li>
                <li>
                    <img src="./plug-in/weixin/img/func09.jpg">
                    <h3>支付组件</h3>
                    <p>微信、支付宝、财付通等支付方式<font color="red">[商业]</font></p>
                </li>
                <li>
                   	<img src="./plug-in/weixin/img/func04.jpg" width="106" height="107">
                   	<h3>微天气</h3>
                   	<p>通过城市：定位天气预报</p>
               	</li>
                <li>
                    <img src="./plug-in/weixin/img/func13.jpg">
                    <h3>微信插件机制</h3>
                    <p>集成插件机制，可以集成P3开发的微信插件 </p>
                </li>
                <li>
                    <img src="./plug-in/weixin/img/func12.jpg">
                    <h3>author2.0机制</h3>
                    <p>通过素材链接配置，生成author2.0链接</p>
                </li>
               
                  <a href="autoResponseController.do?list&clickFunctionId=402881e8460d7e5301460d81c7a60001" target="_blank">
                   <li><img src="./plug-in/weixin/img/func07.jpg">
                   <h3>智能客服</h3>
                   <p>关键词回复轻松设<br>置</p>
                   </li>
                   </a>
            </ul>
</div>
