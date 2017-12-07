package vc.thinker.sys;



import java.util.List;

import com.google.common.collect.Lists;
import com.sinco.mybatis.generator.GeneratorTable;
import com.sinco.mybatis.generator.MyBatisGeneratorTool2;
import com.sinco.mybatis.generator.config.JdbcConfig;

/**
 * 代码自动产生
 * @author james
 *
 */
public class Generator {

	public static void main(String[] args) {
		

		//自动产生代码生成改造 by james
		JdbcConfig jdbc = new JdbcConfig(
				"jdbc:mysql://192.168.1.250:3306/cabbage?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
				"root", "admin123",
				"com.mysql.jdbc.Driver");

//		String itemPath = "D:\\workspace\\cabbage\\sinco-cabbage\\sys-biz\\src\\main\\java";
		String itemPath = "/Users/james/git/cabbage-core/cabbage-core/sys-biz/src/main/java";

		String rootPackage = "vc.thinker.sys";

		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
//				new GeneratorTable("simserver_center", "user_customer_member", "CustomerMember")
				new GeneratorTable("cabbage", "sys_setting_key_val", "SettingKeyVal")
				);

		tool.generator(tableList);
	}
	
}
