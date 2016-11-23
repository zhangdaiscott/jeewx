JeeWx 微信管家平台，简称“捷微”.
===============
  （一款免费开源的JAVA多触点管理平台）
===============
当前最新版本： 2.4.2（发布日期：20160912）
官网：[www.jeewx.com](http://www.jeewx.com) 

![github](http://img.blog.csdn.net/20161123110044167?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
<br>

一、平台简介
-----------------------------------
Jeewx是一款开源、免费的微信管家系统（多触点管理平台）。采用JAVA语言，支持微信公众号、微信企业号、支付宝服务窗、QQ公众号、微博账号等多触点管理。Jeewx实现了微信、支付窗、微信企业号、微博等触点的基础管理功能，便于用户二次开发。
![github](http://img.blog.csdn.net/20161123104736053?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")

二、主要特性
-----------------------------------
* 	1、JEEWX基于快速开发平台jeecg 3.4.4 版本开发，采用Springmvc+Hibernate+Bootstrap+Velicity等主流架构技术
*   2、支持企业快速开发，完善的用户组织机构，报表，强大的代码生成器，有效的提高开发效率
*   3、开源免费，jeewx遵循Apache2开源协议
*   4、多触点管理系统：支持微信、支付窗、微信企业号、微博、QQ公众号等多触点
*   5、详细的二次开发文档，并不断更新增加相关开发案例提供学习参考
*   6、采用微服务架构Jeecg-p3，支持插件化开发，更易于集成


三、系统主要功能
-----------------------------------

【微信公众号】
*   1，微信接口认证
*   2，菜单自定义
*   3，文本管理和回复
*   4，关注欢迎语
*   5，关键字管理
*   6，文本模板管理
*   7，图文模板管理
*   8，账号管理
*   9，用户管理
*   10，角色管理
*   11，菜单管理
*   12, 多用户多公众号
*   13，微信大转盘
*   14，微信刮刮乐
*   15，微信CMS
*   16，自定义接口回复
*   17，翻译
*   18，天气
*   21, author2.0链接
*   22, 微信插件机制
*   23, 用户消息
*   24, 微信第三方平台（全网发布）
*   25, 支持微信企业号(微信企业号管理平台)


【微信企业号】
*   1，微信企业号管理
*   2，微信应用管理
*   3，素材管理：文本素材
*   4，素材管理：图文素材
*   5，菜单管理
*   6，通讯录管理
*   7，用户管理
*   8，关键字管理
*   9，关注回复管理

    源码地址：http://git.oschina.net/jeecg/jeecg-p3
	
【支付宝服务窗】
*   1，支付窗账号管理
*   2，关键字管理
*   3，素材管理：文本素材
*   4，素材管理：图文素材
*   5，关注回复
*   6，菜单管理
*   7，用户管理
*   8，用户消息

    源码地址：[http://git.oschina.net/jeecg/jw-alipay](http://git.oschina.net/jeecg/jw-alipay)
	
	技术文档：[http://blog.csdn.net/zhangdaiscott/article/details/53156710](http://blog.csdn.net/zhangdaiscott/article/details/53156710)

四、开发环境
-----------------------------------
  * eclipse + maven + jdk7 + tomcat6 + mysql （注意：暂时不支持jdk8）
  * 如果不熟悉maven可以下载非maven版本: [更多版本下载地址](http://www.jeecg.org/forum.php?mod=viewthread&tid=1229&page=1&extra=#pid3642)
  * Maven依赖下载慢解决方案 => [享受一下mvn时飞的感觉，配置阿里云maven镜像](http://www.jeecg.org/forum.php?mod=viewthread&tid=3949)

五、系统安装
-----------------------------------
* 	1、将项目采用maven方式导入eclipse中，通过maven下载项目依赖。
* 	2、采用Mysql手工创建数据库jeewx 采用UTF-8编码，执行JEEWX数据初始化SQL脚本 ：doc/db/jeewx-mysql.sql
* 	3、采用maven方式启动项目，首次在浏览器中访问 http://localhost:8080/jeewx/，默认admin登录，一个账号只能配置一个微信公众账号。
* 	4、微信域名配置（重要）
* 	   修改：src/main/resources/sysConfig.properties
* 	   参数：domain={http://localhost:8080/jeewx/}
* 	5、微信后台绑定请求URL      
        URL:   http://*地址*/jeewx/wechatController.do?wechat<br>
        Token:  jeewx<br>
*   6. [开发环境搭建入门](http://www.jeecg.org/forum.php?mod=viewthread&tid=2080&extra=page%3D2) 
*   7. Jeewx依赖本地Maven仓库下载 (http://git.oschina.net/jeecg/jeecg-local-maven) 

六、技术交流
-----------------------------------
* 	论 坛:  www.jeecg.org
* 	QQ 群（微信）:  ④289709451、③175449166(满)、②129190229(满)、①287090836(满)
* 	QQ 群（微信企业号）:  390507007
* 	QQ 群（支付窗）:  495450056
* 	官 网:  www.jeewx.com
* 	邮 箱： jeecg@sina.com


七、技术文档
-----------------------------------
* [JEEWX 系统操作手册](http://blog.csdn.net/zhangdaiscott/article/details/50950739)
* [JEEWX 开发环境搭建入门](http://blog.csdn.net/zhangdaiscott/article/details/50915206)
* [JEEWX 开发入门视频](http://www.jeecg.org/forum.php?mod=viewthread&tid=2309&extra=page%3D1)
* [JEEWX 入门常见问题](http://www.jeecg.org/forum.php?mod=viewthread&tid=1830&extra=page%3D1)

八、在线体验
-----------------------------------
*   在线演示: [http://www.jeewx.com/jeewx](http://www.jeewx.com/jeewx)
*   体验账号：ceshi/123456 (可注册)

    官方公众号："JEECG"  "H5互动汇"
![github](http://img.blog.csdn.net/20160323155143399?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323154916164?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")


系统截图 
-----------------------------------
![github](http://img.blog.csdn.net/20160908175834009?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160822173828381?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160822173833177?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323152508827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323153059001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323153104923?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323153117501?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
![github](http://img.blog.csdn.net/20160323153122251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center "jeewx")
