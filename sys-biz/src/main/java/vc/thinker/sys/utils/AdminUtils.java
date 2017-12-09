package vc.thinker.sys.utils;

public class AdminUtils {

	public static boolean isAdmin(Long uid){
		return "1".equals(uid);
	}
}
