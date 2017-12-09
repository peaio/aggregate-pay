package vc.thinker.sys.utils;

import vc.thinker.sys.model.User;

public class AdminUtils {

	public static boolean isAdmin(User user){
		return "1".equals(user.getId());
	}
}
