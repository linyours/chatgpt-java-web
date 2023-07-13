 ### 使用Java语言作为后端对接 chatgpt，使用简单，并且有可以免费chatgpt3.5,没有套路
### 后端架构：mysql,springBoot 部署简单维护也简单
 ###蓝猫AI后端服务指引
### 效果可以体验 ： http://www.chosen1.xyz/
### 对应前端地址： https://github.com/linyours/chatgpt-web-java
## 后端定制化返回markdowm格式给前端进行图片展示：
![提示词]("url")

##上效果图
![pc端的聊天效果图](https://gitee.com/lixinjiuhao/chatgpt-java-web/raw/master/image/pc-chat-black.jpg)
![pc端的黑色背景聊天图](image/pc-chat-black.jpg)
![pc端的黑白色背景聊天图](image/image/pc-chat-white.jpg)
![pc端prompt工具图](image/pc-tool.jpg)
![手机端的黑色背景聊天图](image/phone-chat-black.jpg)
![手机端的白色背景聊天图](image/phone-chat-white.jpg)
![手机端的prompt工具图](image/phton-tool.jpg)

## 项目启动准备
### 添加gpt的token，和本地或者线上环境的 mysql连接信息
![项目启动前必填](image/项目启动前必填.jpeg)
### 本地根据vm指令去加载不同环境的配置
#### 步骤一
![步骤一](image/idea的vm配置步骤1.jpeg)
#### 步骤二
![步骤二](image/idea的vm配置步骤2.jpeg)
解释说明
按照上面操作：
vm options添加： -Dspring.profiles.active=dev 则代表 加载 application-dev.yml 配置
vm options添加： -Dspring.profiles.active=prod 则代表 加载 application-prod.yml 配置
对应下图的
![步骤二](image/vm配置解释说明.jpeg)

# 项目部署 方式1：
 ##服务器中运行脚本： 
    java -jar xxx.jar --spring.profiles.active=dev
 ## 本地运行：
     线上环境
     vm option添加：-Dspring.profiles.active=prod
     nohup java -jar -Xmx512m -Xms512m -XX:MaxPermSize=256m -XX:PermSize=128m -XX:MetaspaceSize=256M  -XX:MaxMetaspaceSize=256M  -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/var/log/myapp/gc.log  blue-cat-0.0.8-SNAPSHOT.jar --spring.profiles.active=prod 
     本地环境
     vm option添加：-Dspring.profiles.active=dev

## maven打包命令
    mvn clean install -U -Dmaven.test.skip=true

# 项目部署部署 方式2
## 通过脚本一键部署 
### 步骤一：
    前提： 需要在服务器安装 git , maven ， jdk  
### 步骤二：
    打开目标脚本： bin/server.sh  ，按照你自己的配置进行改动脚本里面的自定义脚本配置
   ![脚本需要关心的配置](image/脚本配置.jpeg)
### 步骤三：
    拷贝server.sh 到项目所需要保存的位置
    执行sh server.sh start // 启动命令 
    执行sh server.sh restart //重启项目
    执行sh server.sh stop // 停止项目 
# 疑问
    如果还有什么不清晰的，可以提 issues  ，希望大家点点 star
    如果有兴趣想要一起维护此项目的，可以留言，了解过后可以开给你权限
 
