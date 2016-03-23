package weixin.guanjia.core.util.weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 描述:天气工具类
 * 
 * @date 2013-12-26
 * */
public class Weather {

	/**
	 * 描述:根据名称来获取天气信息
	 * 
	 * @param name
	 *            城市名或者编号
	 * @return 天气信息
	 */
	public Map<String, Object> report(String name , String filepach) {
		String placeId = MapUtil.getPlaceIdByName(name,filepach);
		Map<String, Object> map = new HashMap<String, Object>();
		String url = "http://m.weather.com.cn/data/" + placeId + ".html";
		String content = UrlUtil.getURLContent(url);
		
		map = setData(content);
		return map;
	}
	
	/**
	 * 描述：实时天气接口
	 * @param name
	 * @param filepach
	 * @return
	 */
	public Map<String, Object> report2(String name , String filepach) {
		String placeId = MapUtil.getPlaceIdByName(name,filepach);
		Map<String, Object> map = new HashMap<String, Object>();
		String url = "http://www.weather.com.cn/data/sk/" + placeId + ".html";
		String content = UrlUtil.getURLContent(url);
		
		map = setData2(content);
		return map;
	}

	
	/**
	 * {
	"weatherinfo":{
		<!-- 基本信息 -->
		"city":"福州",
		"city_en":"fuzhou",
		"date_y":"2012年5月14日",
		"date":"",
		"week":"星期一",
		"fchh":"08",
		"cityid":"101230101",
		<!-- 从今天开始到第六天的每天的天气情况，这里的温度是摄氏温度 -->
		"temp1":"29℃~23℃","temp2":"26℃~20℃","temp3":"24℃~20℃","temp4":"25℃~20℃","temp5":"24℃~21℃","temp6":"25℃~22℃",
		<!-- 从今天开始到第六天的每天的天气情况，这里的温度是华氏温度 -->
		"tempF1":"84.2℉~73.4℉","tempF2":"78.8℉~68℉","tempF3":"75.2℉~68℉","tempF4":"77℉~68℉","tempF5":"75.2℉~69.8℉","tempF6":"77℉~71.6℉",
		<!-- 天气描述 -->
		"weather1":"阵雨转中雨","weather2":"中雨转小雨","weather3":"小雨","weather4":"小雨","weather5":"小雨转阵雨","weather6":"阵雨转小雨",
		<!-- 天气描述图片序号 -->
		"img1":"3","img2":"8","img3":"8","img4":"7","img5":"7","img6":"99","img7":"7","img8":"99","img9":"7","img10":"3","img11":"3","img12":"7","img_single":"3",
		<!-- 图片名称 -->
		"img_title1":"阵雨","img_title2":"中雨","img_title3":"中雨","img_title4":"小雨","img_title5":"小雨","img_title6":"小雨","img_title7":"小雨","img_title8":"小雨","img_title9":"小雨","img_title10":"阵雨","img_title11":"阵雨","img_title12":"小雨","img_title_single":"阵雨",
		<!-- 风速描述 -->
		"wind1":"微风","wind2":"微风","wind3":"微风","wind4":"微风","wind5":"微风","wind6":"微风","fx1":"微风","fx2":"微风",
		<!-- 风速级别描述 -->
		"fl1":"小于3级","fl2":"小于3级","fl3":"小于3级","fl4":"小于3级","fl5":"小于3级","fl6":"小于3级",
		<!-- 今天穿衣指数 -->
		"index":"热",
		"index_d":"天气较热，建议着短裙、短裤、短套装、T恤等夏季服装。年老体弱者宜着长袖衬衫和单裤。",
		<!-- 48小时穿衣指数 -->
		"index48":"暖","index48_d":"较凉爽，建议着长袖衬衫加单裤等春秋过渡装。年老体弱者宜着针织长袖衬衫、马甲和长裤。",
		<!-- 紫外线及48小时紫外线 -->
		"index_uv":"弱","index48_uv":"最弱",
		<!-- 洗车 -->
		"index_xc":"不宜",
		<!-- 旅游 -->
		"index_tr":"适宜",、
		<!-- 舒适指数 -->
		"index_co":"较不舒适",
		"st1":"27","st2":"21","st3":"24","st4":"18","st5":"22","st6":"18",
		<!-- 晨练 -->
		"index_cl":"较不宜",
		<!-- 晾晒 -->
		"index_ls":"不太适宜",
		<!-- 过敏 -->
		"index_ag":"不易发"
	}
}
	 */
	/**
	 * 描述:解析并设置天气数据
	 * 
	 * @param datas
	 *            json数据
	 * @param map
	 *            存储Map
	 */
	private Map<String, Object> setData(String datas) {
		if (!datas.startsWith("{")) {
			org.jeecgframework.core.util.LogUtil.info("数据获取失败");
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject data = JSONObject.fromObject(datas);
		JSONObject info = data.getJSONObject("weatherinfo");
		org.jeecgframework.core.util.LogUtil.info(info);
		for (int i = 1; i <= 6; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, i - 1);
			Date date = cal.getTime();
			SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
			map.put("mydate"+i, sf.format(date));
			map.put("myweek"+i, getWeek(cal.get(Calendar.DAY_OF_WEEK)));
			map.put("weather"+i, info.getString("weather" + i));// 天气 
			map.put("img"+i, info.getString("img" + i));// 天气图片  
			map.put("img_title"+i, info.getString("img_title" + i));// 图片名称
			map.put("temp"+i, info.getString("temp" + i));// 气温
			map.put("wind"+i, info.getString("wind" + i));// 风况
			map.put("fl"+i, info.getString("fl" + i));// 风速
		}
		map.put("city", info.getString("city"));// 城市
		map.put("city_en", info.getString("city_en"));// 城市拼音
		map.put("date_y", info.getString("date_y"));// 星期
		map.put("date", info.getString("date"));// 当天日期
		map.put("week", info.getString("week"));// 日期
		map.put("fchh", info.getString("fchh"));// 发布时间
		map.put("cityid", info.getString("cityid"));// 城市ID
		
		map.put("index_d", info.getString("index_d"));// 今天的穿衣指数
//		map.put("index_uv", info.getString("index_uv"));// 紫外指数
//		map.put("index_tr", info.getString("index_tr"));// 旅游指数
//		map.put("index_co", info.getString("index_co"));// 舒适指数
//		map.put("index_cl", info.getString("index_cl"));// 晨练指数
//		map.put("index_xc", info.getString("index_xc"));// 洗车指数
//		map.put("index_d", info.getString("index_d"));// 天气详细穿衣指数
		
		return map;
	}
	
	/**
	 * 解析实时天气
	 * {"weatherinfo":{"city":"北京","cityid":"101010100","temp":"0","WD":"西风",
	 * "WS":"2级","SD":"20%","WSE":"2","time":"18:10","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB"}}
	 * @param datas
	 * @return
	 */
	private Map<String, Object> setData2(String datas) {
		if (!datas.startsWith("{")) {
			org.jeecgframework.core.util.LogUtil.info("数据获取失败");
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject data = JSONObject.fromObject(datas);
		JSONObject info = data.getJSONObject("weatherinfo");
		org.jeecgframework.core.util.LogUtil.info(info);
		map.put("city", info.getString("city"));// 城市
		map.put("cityid", info.getString("cityid"));// 城市代码
		map.put("temp", info.getString("temp"));// 气温
		map.put("WD", info.getString("WD"));// 风向
		map.put("WS", info.getString("WS"));// 风向等级
		map.put("SD", info.getString("SD"));// 湿度
		map.put("WSE", info.getString("WSE"));// 
		map.put("time", info.getString("time"));//
		map.put("isRadar", info.getString("isRadar"));//
		map.put("Radar", info.getString("Radar"));// 
		
		return map;
	}
	
	public String getWeek(int iw) {
		String weekStr = "";
		switch (iw) {
		case 1:
			weekStr = "星期天";
			break;
		case 2:
			weekStr = "星期一";
			break;
		case 3:
			weekStr = "星期二";
			break;
		case 4:
			weekStr = "星期三";
			break;
		case 5:
			weekStr = "星期四";
			break;
		case 6:
			weekStr = "星期五";
			break;
		case 7:
			weekStr = "星期六";
			break;
		default:
			break;
		}
		return weekStr;
	}
}