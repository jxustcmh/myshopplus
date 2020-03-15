文档地址：http://qfdmy.com/%E8%AF%BE%E7%A8%8B/%e5%be%ae%e6%9c%8d%e5%8a%a1%e6%9e%b6%e6%9e%84-2-0/
1.启动nacos注册中心【单机模式】：docker-compose -f example/standalone-mysql.yaml up
2.启动redis

创建myshopplus项目 添加pom依赖
MyShopPlus 部署注册中心
MyShopPlus 用户服务提供者
用户注册服务






项目启动步骤：
1-启动[用户]服务提供者-UmsAdminProviderApplication
2-启动网关-GatewayApplication
3-启动用户注册服务-BusinessRegApplication
4-启动文件上传服务-CloudUploadApplication
5-启动认证服务-BusinessOAuth2Application
6-启动个人信息服务-BusinessProfileApplication
7-启动消息生产者服务-CloudMessageApplication
8-启动消息消费者服务
9-启动登录认证服务





