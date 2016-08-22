package weixin.idea.huodong.utils;

public class HdUtils {
	
	public static int createPrice(int fz,int fm){
		int randomNum = 0;
		for(int i = 0 ;i<fz;i++){
			randomNum = (int)(Math.random()*fm);
			if(randomNum==1||randomNum==2||randomNum==3){
				return randomNum;
			}
		}
		return randomNum;
	}
	
}
