package org.common.util;
import javax.servlet.http.HttpServletResponse;

import org.common.vo.User;
import org.springframework.util.StringUtils;


public class AuthUtil {
	public static boolean authUser(User user, HttpServletResponse respone) throws Exception{
		if(StringUtils.isEmpty(user.getUserId())) {
			return false;
		}else {
			return true;
		}
	}
}
