# at模式和tcc模式事务回滚demo
## 需自行部署nacos和seata服务端
nacos使用默认配置，standalone模式启动即可

seata服务端配置参加seata-server.yml

## rm业务库使用postgresql
需预先创建seata_test库，并执行script.sql初始化库体


## 主要依赖版本如下：
### java.version: 1.8
### seata.version: 1.7.0
### spring-cloud-alibaba.version: 2021.1
### spring-cloud.version: 2021.0.8
### spring-boot.version: 2.6.15




