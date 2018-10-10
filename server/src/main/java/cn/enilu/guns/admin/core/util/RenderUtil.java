package cn.enilu.guns.admin.core.util;

import cn.enilu.guns.bean.exception.GunsException;
import cn.enilu.guns.bean.exception.GunsExceptionEnum;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染工具类
 *
 * @author stylefeng
 * @date 2017-08-25 14:13
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new GunsException(GunsExceptionEnum.WRITE_ERROR);
        }
    }
}
