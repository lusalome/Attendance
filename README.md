**项目说明** 

![项目主页](http://i.imgur.com/p5rT1ry.jpg)


# Attendance
考勤小应用（一键计算出勤率）
- Attendance一键计算出勤率的小应用，其核心设计目标是解放每周计算出勤率、迟到率的人和工作量。
- 打卡机导出的实验室出勤结果统计、方便老板询问，以计算机方式解决生活中的小问题。



**项目结构** 
```
Attendance
├─data  导入数据
├─java  核心代码
│  ├─main 主入口
│  ├─messagebox 弹框提醒
│  ├─tools 工具类
│  └─ui 各UI组件
│ 
├─resources 资源文件
│  ├─extra 设置等properties文件
│  └─icons 图标文件

```


**交流反馈** 
- 项目动机：http://www.jianshu.com/p/79d5e684892e
- github仓库：https://github.com/lusalome/Attendance.git


**主题风格：**
CremeSkin

```
Substance总共定义了27种皮肤，分别是：
org.pushingpixels.substance.api.skin.AutumnSkin,  
org.pushingpixels.substance.api.skin.BusinessSkin,  
org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin,  
org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin,  
org.pushingpixels.substance.api.skin.ChallengerDeepSkin,  
org.pushingpixels.substance.api.skin.CremeSkin,  
org.pushingpixels.substance.api.skin.CremeCoffeeSkin,  
org.pushingpixels.substance.api.skin.DustSkin,  
org.pushingpixels.substance.api.skin.DustCoffeeSkin,  
org.pushingpixels.substance.api.skin.EmeraldDuskSkin,  
org.pushingpixels.substance.api.skin.GeminiSkin,  
org.pushingpixels.substance.api.skin.GraphiteSkin,  
org.pushingpixels.substance.api.skin.GraphiteAquaSkin,  
org.pushingpixels.substance.api.skin.GraphiteGlassSkin,  
org.pushingpixels.substance.api.skin.MagellanSkin,  
org.pushingpixels.substance.api.skin.MarinerSkin,  
org.pushingpixels.substance.api.skin.MistAquaSkin,  
org.pushingpixels.substance.api.skin.MistSilverSkin,  
org.pushingpixels.substance.api.skin.ModerateSkin,  
org.pushingpixels.substance.api.skin.NebulaSkin,  
org.pushingpixels.substance.api.skin.NebulaBrickWallSkin,  
org.pushingpixels.substance.api.skin.OfficeBlack2007Skin,  
org.pushingpixels.substance.api.skin.OfficeBlue2007Skin,  
org.pushingpixels.substance.api.skin.OfficeSilver2007Skin,  
org.pushingpixels.substance.api.skin.RavenSkin,  
org.pushingpixels.substance.api.skin.SaharaSkin  
```


 **软件需求** 
- JDK1.7+
- Maven3.0+



 **本地部署**
- 通过git下载源码
- 运行MainApp主程序，导入data目录下的数据表，计算考勤结果
