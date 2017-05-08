package work.productDefinition.sqldefinition;

import work.productDefinition.utils.BeetlUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SQLDefinition {

	/**
	 * @author lixing 2015.7.29 用于自动初始化产品定义SQL语句
	 */
	public static Map<String, String> initMapData = new HashMap<String, String>();

	/*
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */

	public static void readTxtFile(String filePath) {

		try {

			String encoding = "GBK";

			File file = new File(filePath);

			if (file.isFile() && file.exists()) { // 判断文件是否存在

				InputStreamReader read = new InputStreamReader(

				new FileInputStream(file), encoding);// 考虑到编码格式

				BufferedReader bufferedReader = new BufferedReader(read);

				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {

					System.out.println(lineTxt);
					String[] StrArray = lineTxt.split("	");

					// for(int i=0;i<StrArray.length;i++){
					// System.out.println(StrArray[i]);
					// }

					if (StrArray.length == 3) {
						System.out.println(StrArray[1]);
						System.out.println(StrArray[2]);
						initMapData.put(StrArray[1], StrArray[2]);
					} else {
						System.out.println(StrArray[0] + "行输入格式有问题，请检查！");
					}
				}

				read.close();

			} else {

				System.out.println("找不到指定的文件");

			}

		} catch (Exception e) {

			System.out.println("读取文件内容出错");

			e.printStackTrace();

		}

	}

	/*
	 * 从map中取出新产品数据，开始自动生成SQL语句 lixing 2015.7.29
	 */
	public static void makeSQL(String filePath) {
		String allStr = "";
		String str = "alter session set nls_date_format='YYYY:MM:DD HH24:MI:SS';\n\n";
		allStr = allStr + str;
		// writeTxtFile(filePath, str);
		// 险种代码
		String riskcode = initMapData.get("code").toString();
		str = "delete from FORMLSRiskType where code='" + riskcode + "';\n"
				+ "insert into FORMLSRiskType values ('riskType','" + riskcode
				+ "','1',null,null);\n";
		allStr = allStr + str;
		// writeTxtFile(filePath, str);

		// 险种代码变式
		String code = "111111---" + riskcode;
		str = "delete from lspol where polno='"
				+ code
				+ "';\n"
				+ "INSERT INTO lspol VALUES (	'00000000000000000000',	'00000000000000000000',	'6A7066F0-1D6B-0F09-8649-8E957B0244FF',	'"
				+ code
				+ "',	'DC9FE282-7B07-60E8-F028-372749119D32',	'00000000000000000000',	'1',	NULL,	'"
				+ code
				+ "',	NULL,	NULL,	'"
				+ riskcode
				+ "',	NULL,	'86000',	NULL,	NULL,	'root',	'suggest',	NULL,	NULL,	NULL,	'D40C1D34-F912-B1D6-AFCD-594E5D0400D0',	'男0',	'0',	'1981-02-01',	30,	1,	'010101',	'EFC0544C-E9A0-9CA9-8BA1-CED30CC042A8',	'投保人',	'2011-09-13', 	NULL,	NULL,	NULL,	'2011-10-13', 	'2019-09-13', 	NULL,	'2054-09-13', 	'2054-09-13', 	NULL,	'A',	60,	'Y',	10,	'A',	105,	NULL,	0,	NULL,	NULL,	NULL,	NULL,	12,	10,	75,	0,	0,	0,	1,	1,	0,	10000,	0,	9831,	0,	0,	0,	0,	0,	0,	NULL,	NULL,	NULL,	NULL,	NULL,	0,	NULL,	NULL,	NULL,	NULL,	'M',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	'0',	NULL,	NULL,	'9',	NULL,	NULL,	NULL,	'2011-09-13', 	'0',	NULL,	NULL,	NULL,	NULL,	'root',	'2011-09-13', 	'15:19:07',	'2011-09-13', 	'15:19:07');\n";
		allStr = allStr + str;
		// writeTxtFile(filePath, str);

		// 险种名称
		String riskName = initMapData.get("riskName").toString();
		String riskShortName = initMapData.get("riskShortName").toString();
		// 产品年份
		String riskYear = initMapData.get("riskYear").toString();
		str = "delete from lmrisk where riskcode='"
				+ riskcode
				+ "';\n"
				+ "INSERT INTO lmrisk VALUES (	'"
				+ riskcode
				+ "',	'"
				+ riskYear
				+ "',	'"
				+ riskName
				+ "',	'"
				+ riskShortName
				+ "',	NULL,	NULL,	'N',	'Y',	'Y',	'Y',	'N',	'Y',	'Y',	'N',	NULL,	NULL,	NULL,	NULL);\n";
		allStr = allStr + str;
		// writeTxtFile(filePath, str);

		// 险种类别1：L长险，U万能，C卡折短险
		// 险种类别2：Y有红利累积生息，N无累积生息选项
		// 险种类别3: 1普通，2分红（红利领取方式）
		String riskType1 = initMapData.get("riskType1").toString();
		String riskType2 = initMapData.get("riskType2").toString();
		String riskType3 = initMapData.get("riskType3").toString();
		// 主附险定义：M主险，S附加险
		String subRiskFlag = initMapData.get("subRiskFlag").toString();
		// 开售时间、结束时间
		String startDate = initMapData.get("startDate").toString();
		String endDate = initMapData.get("endDate").toString();
		// 投保人年龄范围
		String minAppntAge = initMapData.get("minAppntAge").toString();
		String maxAppntAge = initMapData.get("maxAppntAge").toString();
		// 被保人年龄范围
		String maxInsuredAge = initMapData.get("maxInsuredAge").toString();
		String minInsuredAge = initMapData.get("minInsuredAge").toString();
		String maxAppntAgeFlag = initMapData.get("maxAppntAgeFlag").toString();
		String minAppntAgeFlag = initMapData.get("minAppntAgeFlag").toString();
		// 万能险标记，1表示为万能，0表示不是万能；
		String riskFlag = initMapData.get("riskFlag").toString();

		if ("1".equals(riskFlag)) {
			riskFlag = "'Y'";
		} else {
			riskFlag = "NULL";
		}

		// 销售渠道标记[I]个险,[B]银保,[SP]柜面,[BS]团险 ，为空表示停售（电子投保）
		String saleFlag = initMapData.get("saleFlag").toString();
		// 产品渠道I个险，B银保，W停售（电子投保，计划书）
		String mngCon = initMapData.get("mngCon").toString();

		str = "delete from lmriskapp where riskcode='"
				+ riskcode
				+ "';\n"
				+ "INSERT INTO lmriskapp VALUES (	'"
				+ riskcode
				+ "',	'"
				+ riskYear
				+ "',	'"
				+ riskName
				+ "',	'L',	'L',	'"
				+ riskType1
				+ "',	'"
				+ riskType2
				+ "',	'I',	'L',	'P',	"
				+ riskFlag
				+ ",	'P',	'Y',	'1',	NULL,	'A',	'"
				+ subRiskFlag
				+ "',	NULL,	'1',	NULL,	NULL,	NULL,	NULL,	1,	NULL,	NULL,	NULL,	NULL,	NULL,	 '"
				+ startDate
				+ "', 	'"
				+ endDate
				+ "',	"
				+ minAppntAge
				+ ",	"
				+ maxAppntAge
				+ ",	"
				+ maxInsuredAge
				+ ",	"
				+ minInsuredAge
				+ ",	NULL,	NULL,	'S',	NULL,	NULL,	'Y',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	'"
				+ saleFlag + "',	NULL,	'" + mngCon + "',	NULL,	NULL,	NULL,	'"
				+ riskType3 + "',	'4',	'2',	NULL,	NULL,	NULL,	NULL,	NULL);\n\n";
		allStr = allStr + str;

		str = "delete from lmriskcomctrl where riskcode='" + riskcode + "';\n";
		allStr = allStr + str;
		str = "INSERT INTO lmriskcomctrl VALUES (	'" + riskcode
				+ "',	'000000',	 '" + startDate + "', 	'" + endDate
				+ "',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL);\n";
		allStr = allStr + str;

		str = "delete from lmriskduty where riskcode='"
				+ riskcode
				+ "';\n"
				+ "INSERT INTO lmriskduty VALUES (	'"
				+ riskcode
				+ "',	'"
				+ riskYear
				+ "',	'"
				+ riskcode
				+ "001',	'M',	'N');\n"
				+ "delete from lmriskrole where riskcode='"
				+ riskcode
				+ "';\n"
				+ "INSERT INTO lmriskrole VALUES (	'"
				+ riskcode
				+ "',	'"
				+ riskYear
				+ "',	'01',	'0',	'01',	'Y',	"
				+ minInsuredAge
				+ ",	'Y',	"
				+ maxInsuredAge
				+ ");\n"
				+ "delete from lmcalmode where riskcode='"
				+ riskcode
				+ "';\n"
				+ "INSERT INTO lmcalmode VALUES (	'"
				+ riskcode
				+ "301',	'"
				+ riskcode
				+ "',	'P',	'select ?Prem? From ldsysvar where sysvar=''onerow''',	'计算保费');\n"
				+ "INSERT INTO lmcalmode VALUES (	'"
				+ riskcode
				+ "302',	'"
				+ riskcode
				+ "',	'A',	'select ?Amnt? From ldsysvar where sysvar=''onerow''',	'保额');\n\n";
		allStr = allStr + str;

		String duytName = initMapData.get("duytName");
		// 保险期间
		String insuYear = initMapData.get("insuYear");
		String insuYearFlag = initMapData.get("insuYearFlag");
		// 保额保费互算，P为保费算保额，G为保额算保费
		String calmode = initMapData.get("calmode");

		// 保险期间读取方式，0从界面读，4从表读
		String payEndYearRela = initMapData.get("payEndYearRela");
		// 领取年龄读取方式，0从界面读，4从表读
		String getYearRela = initMapData.get("getYearRela");
		// 保险期间读取方式，0从界面读，4从表读，2与起领期间一致
		String insuYearRela = initMapData.get("insuYearRela");
		str = "delete from lmduty where dutycode='" + riskcode + "001';\n"
				+ "INSERT INTO lmduty VALUES (	'" + riskcode + "001',	'"
				+ duytName + "责任',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	"
				+ insuYear + ",	'" + insuYearFlag + "',	NULL,	NULL,	'"
				+ calmode + "',	'" + payEndYearRela + "',	'" + getYearRela
				+ "',	'" + insuYearRela
				+ "',	'000003',	'000003',	'1',	1000.00,	'00',	'0');\n";
		allStr = allStr + str;

		str = "delete from lmdutyget where getdutycode='" + riskcode
				+ "201';\n";
		allStr = allStr + str;
		String calCode = "NULL";
		String centerCalCode = "NULL";
		String calCode2 = "NULL";
		String centerCalCode2 = "NULL";
		if (calmode.equals("P")) {
			calCode = "'" + riskcode + "302" + "'";
		} else {
			centerCalCode = "'" + riskcode + "302" + "'";
		}
		str = "INSERT INTO lmdutyget VALUES (	'"
				+ riskcode
				+ "201',	'"
				+ duytName
				+ "给付',	'1',	NULL,	NULL,		"
				+ calCode
				+ ","
				+ centerCalCode
				+ ",	NULL,	0,	'Y',	'S',	'0',	NULL,	NULL,	'Y',	'S',	'3',	NULL,	'N',	'N',	NULL,	'Y',	NULL,	NULL,	NULL,	NULL,	NULL,	'Y',	NULL,	NULL,	NULL,	NULL,	NULL,	'0',	'1',	'0',	NULL,	'N',	'0');\n";
		allStr = allStr + str;

		str = "delete from lmdutygetrela where dutycode='" + riskcode
				+ "001';\n" + "INSERT INTO lmdutygetrela VALUES (	'" + riskcode
				+ "001',	'" + riskcode + "201',	'" + duytName + "给付');\n";
		allStr = allStr + str;

		str = "delete from lmdutypay where payplancode='" + riskcode
				+ "101';\n";
		allStr = allStr + str;

		if (calmode.equals("P")) {
			centerCalCode2 = "'" + riskcode + "301" + "'";
		} else {
			calCode2 = "'" + riskcode + "301" + "'";
		}
		str = "INSERT INTO lmdutypay VALUES (	'"
				+ riskcode
				+ "101',	'"
				+ duytName
				+ "缴费',	'0',	'-1',	'Y',	NULL,	'S',	'0',	NULL,	"
				+ calCode2
				+ ",	"
				+ centerCalCode2
				+ ",	NULL,	NULL,	NULL,	NULL,	NULL,	'0',	'Y',	'N',	'N',	NULL,	'N',	60,	NULL,	'N',	'0',	'1');\n";
		allStr = allStr + str;

		str = "delete from lmdutypayrela where dutycode='" + riskcode
				+ "001';\n";
		allStr = allStr + str;

		str = "INSERT INTO lmdutypayrela VALUES (	'" + riskcode + "001',	'"
				+ riskcode + "101',	'" + duytName + "缴费');\n";
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = "delete from lmriskscreen where riskcode='" + riskcode + "';\n";
		allStr = allStr + str;

		String readOnly = "1";
		if ("P".equals(calmode)) {
			str = "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	0,	'TurnMetic',	'TurnMetic',	'测算模式',	'1',	'01',	110,	25,	100,	20,	NULL,	20,	25,	90,	20,	NULL,	NULL,	'select code,codename from ldcode where codetype=''TurnMetic'' order by othersign desc',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	3,	'PayIntv',	'PayIntv',	'交费方式',	'1',	'01',	320,	25,	100,	20,	NULL,	230,	25,	90,	20,	NULL,	NULL,	'select code,codename from ldcode where codetype=''PTV_"
					+ riskcode
					+ "'' order by othersign',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	4,	'PayEndYear',	'PayEndYear',	'交费期间',	'1',	'01',	320,	25,	100,	20,	NULL,	230,	25,	90,	20,	NULL,	NULL,	'select code||''/''||codealias,codename from ldcode where codetype=''PayEndYear_"
					+ riskcode
					+ "'' and code+?AppAge?<=70  order by othersign',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	9,	'Amnt',	'Amnt',	'基本保额',	'1',	'00',	530,	25,	100,	20,	NULL,	440,	25,	90,	20,	NULL,	NULL,	NULL,	'01', '"
					+ readOnly
					+ "',	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	10,	'Prem',	'Prem',	'保费',	'1',	'00',	530,	25,	100,	20,	NULL,	440,	25,	90,	20,	NULL,	NULL,	NULL,	'01',	NULL,	NULL);\n";
		} else {
			str = "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	0,	'TurnMetic',	'TurnMetic',	'测算模式',	'1',	'01',	110,	25,	100,	20,	NULL,	20,	25,	90,	20,	NULL,	NULL,	'select code,codename from ldcode where codetype=''TurnMetic'' order by othersign desc',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	3,	'PayIntv',	'PayIntv',	'交费方式',	'1',	'01',	320,	25,	100,	20,	NULL,	230,	25,	90,	20,	NULL,	NULL,	'select code,codename from ldcode where codetype=''PTV_"
					+ riskcode
					+ "'' order by othersign',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	4,	'PayEndYear',	'PayEndYear',	'交费期间',	'1',	'01',	320,	25,	100,	20,	NULL,	230,	25,	90,	20,	NULL,	NULL,	'select code||''/''||codealias,codename from ldcode where codetype=''PayEndYear_"
					+ riskcode
					+ "'' and code+?AppAge?<=70  order by othersign',	'01',	NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	9,	'Amnt',	'Amnt',	'基本保额',	'1',	'00',	530,	25,	100,	20,	NULL,	440,	25,	90,	20,	NULL,	NULL,	NULL,	'01', NULL,	NULL);\n"
					+ "INSERT INTO lmriskscreen VALUES (	'"
					+ riskcode
					+ "',	10,	'Prem',	'Prem',	'保费',	'1',	'00',	530,	25,	100,	20,	NULL,	440,	25,	90,	20,	NULL,	NULL,	NULL,	'01',	'"
					+ readOnly + "',	NULL);\n";
		}
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = "delete from lmriskscreenvalidate where riskcode='" + riskcode
				+ "';\n";
		allStr = allStr + str;

		str = "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "00',	'"
				+ riskcode
				+ "',	'Amnt',	NULL,	'1',	NULL,	NULL,	'Prem',	'0',	'select premium*?Amnt?/face_amnt*(case when ?PayIntv?=12 then 1 else 0.09 end) from prem_rate a where a.pol_code=''"
				+ riskcode
				+ "'' and a.appl_age=?AppAge? and a.sex=(case when ?Sex?=0 then ''M'' when ?Sex?=1 then ''F'' else ''B'' end) And a.moneyin_dur=(case when ''?PayEndYearFlag?''=''Y'' then (case when ?PayEndYear?=1000 then 1 else ?PayEndYear? end ) else (?PayEndYear?-?AppAge?) end) and a.MONEYIN_ITRVL=(case when ?PayEndYear?=1000 then ''W'' else ''Y'' end)',	NULL,	'保费计算失败，投保条件不完整或不符合投保要求');\n"
				+ "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "01',	'"
				+ riskcode
				+ "',	'Prem',	NULL,	'1',	NULL,	NULL,	'Amnt',	'0',	'select face_amnt*?Prem?/(case when ?PayIntv?=12 then 1 else 0.09 end)/premium from prem_rate a where a.pol_code=''"
				+ riskcode
				+ "'' and a.appl_age=?AppAge? and a.sex=(case when ?Sex?=0 then ''M'' when ?Sex?=1 then ''F'' else ''B'' end) And a.moneyin_dur=(case when ''?PayEndYearFlag?''=''Y'' then (case when ?PayEndYear?=1000 then 1 else ?PayEndYear? end ) else (?PayEndYear?-?AppAge?) end) and a.MONEYIN_ITRVL=(case when ?PayEndYear?=1000 then ''W'' else ''Y'' end)',	NULL,	'保额计算失败，投保条件不完整或不符合投保要求');\n"
				+ "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "04',	'"
				+ riskcode
				+ "',	'TurnMetic',	'1',	'3',	'Amnt',	'0',	NULL,	'0',	NULL,	NULL,	NULL);\n"
				+ "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "05',	'"
				+ riskcode
				+ "',	'TurnMetic',	'1',	'3',	'Prem',	'1',	NULL,	'0',	NULL,	NULL,	NULL);\n"
				+ "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "06',	'"
				+ riskcode
				+ "',	'TurnMetic',	'2',	'3',	'Amnt',	'1',	NULL,	'0',	NULL,	NULL,	NULL);\n"
				+ "INSERT INTO lmriskscreenvalidate VALUES (	'"
				+ riskcode
				+ "07',	'"
				+ riskcode
				+ "',	'TurnMetic',	'2',	'3',	'Prem',	'0',	NULL,	'0',	NULL,	NULL,	NULL);\n";
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = "delete from ldcode where codetype in ('PTV_" + riskcode
				+ "','PayEndYear_" + riskcode + "');\n";
		allStr = allStr + str;

		str = "INSERT INTO ldcode VALUES (	'PayEndYear_" + riskcode
				+ "',	'3',	'3年',	'Y',	NULL,	'1');\n";
		allStr = allStr + str;
		str = "INSERT INTO ldcode VALUES (	'PayEndYear_" + riskcode
				+ "',	'5',	'5年',	'Y',	NULL,	'2');\n";
		allStr = allStr + str;
		str = "INSERT INTO ldcode VALUES (	'PayEndYear_" + riskcode
				+ "',	'10',	'10年',	'Y',	NULL,	'3');\n";
		allStr = allStr + str;

		str = "INSERT INTO ldcode VALUES (	'PTV_" + riskcode
				+ "',	'12',	'年交',	NULL,	NULL,	'1');\n";
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = "delete from ldcode where code='"
				+ riskcode
				+ "' and codetype in ('riskkind','bonandgenRisk001','bonRisk001','genRisk001','ljscRisk001');\n";
		allStr = allStr + str;

		String codeName = initMapData.get("codeName");
		str = "INSERT INTO ldcode VALUES ('riskkind','" + riskcode + "','"
				+ codeName + "',NULL,	NULL,	NULL);\n"
				+ "INSERT INTO ldcode VALUES ('genRisk001','" + riskcode
				+ "',NULL,NULL,	NULL,	'传统险收益表打印');\n";
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = PVcaculate(riskcode);
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		writeTxtFile(filePath, allStr);

	}

	/*
	 * 利益演示配置 lixing 2016.1.15
	 */
	public static String PVcaculate(String riskcode) {
		String str = "";
		String allStr = "";
		str = "delete from proceedsexpress where riskcode='" + riskcode
				+ "';\n";
		allStr = allStr + str;

		str = "INSERT INTO proceedsexpress VALUES (	'" + riskcode
				+ "',	'BF',	'BF1+BF2',	NULL,	1,	'1',	NULL,	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		str = "INSERT INTO proceedsexpress VALUES (	'"
				+ riskcode
				+ "',	'LJBF',	'BF+$Self$',	NULL,	2,	'3',	'0',	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		str = "INSERT INTO proceedsexpress VALUES (	'" + riskcode
				+ "',	'XJ',	'XJ',	NULL,	3,	'1',	NULL,	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		str = "\n";
		allStr = allStr + str;

		str = "delete from calcuteelemet where riskcode='" + riskcode + "';\n";
		allStr = allStr + str;

		str = "INSERT INTO calcuteelemet VALUES (	'"
				+ riskcode
				+ "',	'BF',	2,	'BF1',	'1',	'0',	NULL,	'select ?Prem?*(case when ?PayIntv?=12 then 1 else 12 end) from ldsysvar where sysvar = ''onerow''',	'1',	'1',	'Y',	'?PayYears?',	'Y',	NULL,	'1',	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		str = "INSERT INTO calcuteelemet VALUES (	'"
				+ riskcode
				+ "',	'BF',	1,	'BF2',	'1',	'0',	NULL,	NULL,	'2',	'?PayYears?+1',	'Y',	'?Years?',	'Y',	'0',	'0',	NULL,	0,	NULL);\n";
		allStr = allStr + str;

		str = "INSERT INTO calcuteelemet VALUES (	'"
				+ riskcode
				+ "',	'LJBF',	1,	'BF',	'-1',	'0',	'BF',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		str = "INSERT INTO calcuteelemet VALUES (	'"
				+ riskcode
				+ "',	'XJ',	1,	'XJ',	'1',	'0',	NULL,	'select CASHVALUE*(case when (select CASHVAL_TBL_TYPE from LONG_TERM_INSUR where pol_code=''?RiskCode?'')=''0'' then ?Amnt? else ?Prem? end)/1000.0 from cashval where pol_code=''"
				+ riskcode
				+ "'' and appl_age=?AppAge? and sex=(case when ?Sex?=0 then ''M'' when ?Sex?=1 then ''F'' else ''B'' end) and moneyin_dur=(case when ?PayEndYear?=1000 then ''1'' else ''?PayEndYear?'' end)  and MONEYIN_ITRVL=(case when ?PayEndYear?=1000 then ''W'' else ''Y'' end) order by INSUR_YEAR',	'0',	'1',	'Y',	'?Years?',	'Y',	NULL,	'1',	NULL,	8,	NULL);\n";
		allStr = allStr + str;

		return allStr;
	}

	/*
	 * 写数据 lixing 2015.7.29
	 */
	public static void writeTxtFile(String filePath, String content) {

		try {

			File file = new File(filePath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(filePath, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content);
			bufferWritter.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String argv[]) {

		String infilePath = "template/sqlDevelop.txt";
		String outfilePath = "template/sqlOK.txt";

		// String data ="This content will append to the end of the file\n";
		readTxtFile(infilePath);
		// writeTxtFile(outfilePath,data);
		makeSQL(outfilePath);

		Map<String, Object> sqlData = new HashMap<String, Object>();
		Set<Entry<String, String>> set = initMapData.entrySet();
		Iterator<Entry<String, String>> itor = set.iterator();
		while (itor.hasNext()) {
			Entry<String, String> en = itor.next();
			sqlData.put(en.getKey(), en.getValue());
		}

		BeetlUtils bu = new BeetlUtils();
		bu.createSql("/SQLTemplate.txt", sqlData);

	}

}
