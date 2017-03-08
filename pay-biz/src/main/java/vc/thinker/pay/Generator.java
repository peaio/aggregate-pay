package vc.thinker.pay;

import com.sinco.mybatis.generator.MyBatisGeneratorTool;

/**
 * 代码自动产生
 * @author james
 *
 */
public class Generator {

	public static void main(String[] args) {
		String genCfg = "/generator/generatorConfig.xml";
		args=new String[1];
		args[0]=genCfg;
		MyBatisGeneratorTool.main(args);
		System.out.println("代码生成成功");
	}
	
}
