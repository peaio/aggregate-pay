package vc.thinker.pay.request.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @Author: HeTongHao
 * @Date: 2019/1/25 17:07
 * @Description:
 */
public class UrlUtils {

    /**
     * 拼接url，防止出现多个/拼在一起的情况
     *
     * @param head  URL开头
     * @param items rul多个拼接项
     * @return
     */
    public static String stitchingUrl(final String head, final String... items) {
        String returnUrl = head;
        if (returnUrl == null || returnUrl == null || items.length == 0) {
            return returnUrl;
        }
        for (String body : items) {
            if (StringUtils.isBlank(body)) {
                continue;
            }
            boolean headEndWithSeparator = returnUrl.lastIndexOf(File.separator) == returnUrl.length() - 1;
            boolean bodyFirstWithSeparator = body.indexOf(File.separator) == 0;
            if (headEndWithSeparator && bodyFirstWithSeparator) {
                returnUrl += body.substring(1);
            } else if (headEndWithSeparator || bodyFirstWithSeparator) {
                returnUrl += body;
            } else {
                returnUrl += File.separator + body;
            }
        }
        return returnUrl;
    }
}
