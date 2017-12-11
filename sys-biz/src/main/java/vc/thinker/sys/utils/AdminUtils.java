package vc.thinker.sys.utils;

public class AdminUtils {

	private static final Long ADMIN_UID=1L;
	public static boolean isAdmin(Long uid){
		return ADMIN_UID.equals(uid);
	}
}
