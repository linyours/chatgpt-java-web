 
 ###蓝猫AI后端服务指引
### 效果可以体验 ： http://www.chosen1.xyz/
### 对应前端地址： https://gitee.com/lixinjiuhao/chatgpt-web-java
 
 ##服务器中运行脚本： 
    java -jar xxx.jar --spring.profiles.active=dev
 ## 本地运行：
     线上环境
     vm option添加：-Dspring.profiles.active=prod
     nohup java -jar -Xmx512m -Xms512m -XX:MaxPermSize=256m -XX:PermSize=128m -XX:MetaspaceSize=256M  -XX:MaxMetaspaceSize=256M  -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/var/log/myapp/gc.log  blue-cat-0.0.8-SNAPSHOT.jar --spring.profiles.active=prod 
     本地环境-Dspring.profiles.active=dev
     vm option添加：

## maven打包命令
    mvn clean install -U -Dmaven.test.skip=true

## 后端定制化返回markdowm格式给前端进行图片展示：
![提示词]("url")
