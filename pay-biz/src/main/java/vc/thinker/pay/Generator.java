package vc.thinker.pay;

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
				"jdbc:mysql://192.168.1.250:3306/cabbage?useUnicode=true&characterEncoding=utf8",
				"root", "admin123",
				"com.mysql.jdbc.Driver");

		String itemPath = "/Users/james/git/cabbage-core/cabbage-core/pay-biz/src/main/java";
		String rootPackage = "vc.thinker.pay";

		MyBatisGeneratorTool2 tool = new MyBatisGeneratorTool2(jdbc, itemPath, rootPackage);
		tool.setGeneratorBO(false);
		tool.setGeneratorDao(false);
		tool.setGeneratorMapperJava(false);
		
		List<GeneratorTable> tableList=Lists.newArrayList(
				new GeneratorTable("cabbage", "pay_config", "PayConfig")
				);

		tool.generator(tableList);
	}
	
}
