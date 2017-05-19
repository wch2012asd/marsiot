# marsiot-pi-sdk
SDK source code for raspberry pi to connect to marsiot.

www.marsiot.com

 配置开发包中的相关参数
1. 打开并编辑目录下的config.propetites文件
2. 修改 site.token=你的SITE TOKEN（登录火星互联控制台后获取）
3. 修改 model.name=你的设备型号
4. 修改 model.description=设备型号的描述

开发及编译说明
1. 程序入口文件 src/main/java/com/example/Main.java
2. 自定义命令实现文件 src/main/java/com/example/MyCommandProcessor.java,其中包含一个helloworld命令的处理函数的实例，供参考
3. 编译代码./mybuild.sh

运行程序连接云平台
1. 执行编译后的代码./myrun.sh 


