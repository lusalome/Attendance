package tools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import messagebox.ShowMessageDialog;

/**
 * 考勤管理应用 - 计算
 *
 * @author wanglu lusalome@163.com
 * @version 2015-09-08 21:18
 */
public class Calculate {
	// 记录日志
	private static Logger logger = Logger.getLogger(Calculate.class);

	static final int BUFFER = 8192;

	public static List<Result> calculateExcel(String fileName) {

		// 第1步: 读取源数据表Excel，并计算
		ReadExcel poi = new ReadExcel();
		// 存储从Excel中读取的结果
		List<Result> resultList = new ArrayList<Result>();
		int size = 0;// 记录当前resultList的记录条数
		Result tempResult = new Result();
		// 存储当前Excel中所有的人名
		List<String> staffNameList = new ArrayList<String>();
		// flag标记：当前读取的打卡记录是属于上一个人(false)还是一个新的人(true)
		boolean flag = false;
		// 当前读取的那个人的编号
		String currNo = "";
		double tempAcHours = 0.0;
		double a = 0.0;
		String b = "";
		//List<List<String>> list = poi.read("D:/MyEclipseWorkspace/Attendance/extra/ExportTemplate.xlsx");
		// 测试源数据表报错: 解决方案是将考勤机倒出来的数据Excel要另存为一下！！
		try {
			List<List<String>> list = poi.read(fileName);
			if (list != null) {
				// 第0行是表头
				for (int i = 1; i < list.size()-1; i++) {
					// System.out.print("第" + (i) + "行");
					List<String> cellList = list.get(i);
					if(i == 1) {
						flag = false;
						currNo = cellList.get(0).toString();
						// 对于编号为个位数的补齐一个0
						if(currNo.length() == 1) {
							tempResult.setStaffNo("0" + currNo);
						} else {
							tempResult.setStaffNo(currNo);
						}
						// 姓名
						tempResult.setStaffName(cellList.get(2).toString());
						// 迟到次数
						String str = cellList.get(3).toString();
						if(str.charAt(0) == '0') {// 0*表示是上午
							if(str.charAt(1) == '9') {
								tempResult.setLateTimes(tempResult.getLateTimes()+1);
							} else if(str.charAt(1) == '8'){
								if(str.charAt(3) == '3') {
									if(str.charAt(4) != '0') {
										tempResult.setLateTimes(tempResult.getLateTimes()+1);
									}
								}
								else if(str.charAt(3) == '4' || str.charAt(3) == '5') {
									tempResult.setLateTimes(tempResult.getLateTimes()+1);
								}
							}
						}
						if(str.charAt(0) == '1') {
							if(str.charAt(1) == '0') {
								tempResult.setLateTimes(tempResult.getLateTimes()+1);
							}
						}

						// 实到小时
						tempAcHours = tempResult.getActualHours();
						b = cellList.get(9).toString();
						String[] split = b.split(":");
						tempResult.setActualHours(tempAcHours + Double.parseDouble(split[0]) + Double.parseDouble(split[1])/60);

						resultList.add(tempResult);
						size++;
					} else {
						// flag = false表示下一条记录是和上一条记录属于同一个人
						if(currNo.equals(cellList.get(0).toString())) {
							flag = false;
						} else {
							flag = true;
						}

						// 记录属于一个新的人
						if(flag) {
							currNo = cellList.get(0).toString();
							tempResult = new Result();
							// 对于编号为个位数的补齐一个0
							if(currNo.length() == 1) {
								tempResult.setStaffNo("0" + currNo);
							} else {
								tempResult.setStaffNo(currNo);
							}
							// 姓名
							tempResult.setStaffName(cellList.get(2).toString());
							// 迟到次数
							String str = cellList.get(3).toString();
							if(str.charAt(0) == '0') {// 0*表示是上午
								if(str.charAt(1) == '9') {
									tempResult.setLateTimes(tempResult.getLateTimes()+1);
								} else if(str.charAt(1) == '8'){
									if(str.charAt(3) == '3') {
										if(str.charAt(4) != '0') {
											tempResult.setLateTimes(tempResult.getLateTimes()+1);
										}
									}
									else if(str.charAt(3) == '4' || str.charAt(3) == '5') {
										tempResult.setLateTimes(tempResult.getLateTimes()+1);
									}
								}
							}
							if(str.charAt(0) == '1') {
								if(str.charAt(1) == '0') {
									tempResult.setLateTimes(tempResult.getLateTimes()+1);
								}
							}
							// 实到小时
							tempAcHours = tempResult.getActualHours();
							b = cellList.get(9).toString();
							String[] split = b.split(":");
							tempResult.setActualHours(tempAcHours + Double.parseDouble(split[0]) + Double.parseDouble(split[1])/60);
							//System.out.println(tempResult.getActualHours());
							resultList.add(tempResult);
							size++;
						} else {
							currNo = cellList.get(0).toString();
							//tempResult = new Result();
							// 对于编号为个位数的补齐一个0
							if(currNo.length() == 1) {
								tempResult.setStaffNo("0" + currNo);
							} else {
								tempResult.setStaffNo(currNo);
							}
							// 姓名
							tempResult.setStaffName(cellList.get(2).toString());
							// 迟到次数
							String str = cellList.get(3).toString();
							if(str.charAt(0) == '0') { // 0*表示是上午
								if(str.charAt(1) == '9') {
									tempResult.setLateTimes(tempResult.getLateTimes()+1);
								} else if(str.charAt(1) == '8'){
									if(str.charAt(3) == '3') {
										if(str.charAt(4) != '0') {
											tempResult.setLateTimes(tempResult.getLateTimes()+1);
										}
									}
									else if(str.charAt(3) == '4' || str.charAt(3) == '5') {
										tempResult.setLateTimes(tempResult.getLateTimes()+1);
									}
								}
							}
							if(str.charAt(0) == '1') {
								if(str.charAt(1) == '0') {
									tempResult.setLateTimes(tempResult.getLateTimes()+1);
								}
							}

							// 实到小时
							tempAcHours = tempResult.getActualHours();
							b = cellList.get(9).toString();
							String[] split = b.split(":");
							tempResult.setActualHours(tempAcHours + Double.parseDouble(split[0]) + Double.parseDouble(split[1])/60);

							resultList.remove(size - 1);
							resultList.add(tempResult);
						}
					}
				}
			}

			// 将完整的信息保持到ArrayList中
			List<Result> forReturnList = new ArrayList<Result>();
			double totalTime = Double.parseDouble(ReadWriteProperties.readPropertiesFile(Calculate.class.getClassLoader().getResource("extra/Setting.properties").getPath(), "totalTime"));
			Result tempObj = new Result();
			DecimalFormat df = new DecimalFormat("0.00");
			ReadWriteTxt rwt = new ReadWriteTxt();
			rwt.setPath(Calculate.class.getClassLoader().getResource("extra/Staff.txt").getPath());
			rwt.creatTxtFile("Staff");
			for(int k = 0; k < resultList.size(); k++) {

				tempObj = resultList.get(k);
				tempObj.setActualHours(Calculate.get2Double(tempObj.getActualHours()));
				tempObj.setShouldHours(Calculate.get2Double(totalTime));
				tempObj.setdValue(Calculate.get2Double(tempObj.getActualHours() - tempObj.getShouldHours()));
				tempObj.setAttendRate(Calculate.get2Double(tempObj.getActualHours()/tempObj.getShouldHours()*100));
				tempObj.setLateTimes((Integer)tempObj.getLateTimes());
				// 将当前的人员信息保存到TXT中
				rwt.writeTxtFile(tempObj.getStaffNo() + "\t" + tempObj.getStaffName() + "");
				forReturnList.add(tempObj);
			}
			return forReturnList;
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"请将从考勤机导出的源Excel文件另存为一个新的.xls/.xlsx文件(因为考勤机导出的文件头有错误)!", "错误",
					JOptionPane.ERROR_MESSAGE,
					ShowMessageDialog.iconError);
			return null;
		}
	}

	/**
	 * 保留2位小数
	 * @param a
	 * @return
     */
	public static double get2Double(double a){
		DecimalFormat df=new DecimalFormat("0.00");
		return new Double(df.format(a).toString());
	}
}