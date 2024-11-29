书池商城
是一个c2c模式的图书商城结构，
可以当买家和卖家，主页图如下图所示

<img width="1022" alt="主页" src="https://github.com/user-attachments/assets/c2343b04-4766-4164-a663-a72bd00f0e44">

包含了图书管理 我的订单 购物车管理 好友管理暂时未完善 订单消息  实时推送在线人数 调用了qq邮箱的接口完成注册功能 用阿里云的对象存储服务来实现照片的上传 

具体功能 自己使用体会，可以自行完善项目。 

切记yaml文件中的配置换成自己的 主要是阿里云oss和Mysql数据库 QQ邮箱用自己的邮箱名和授权码 redis也是 如果有密码的话填自己的密码


# application.yml


jyx:
  alioss:
    endpoint: 
    access-key-id: 
    access-key-secret: 
    bucket-name: 



  data:
    redis:
      host: localhost
      port: 6379
      password:
      database: 0
      jedis:
        pool:
          enabled: true
          max-active: 10
          max-idle: 5
          min-idle: 2
          max-wait: 10000

  datasource:
    url: jdbc:mysql://localhost:3306/users
    username: root
    password: 
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      idle-timeout: 30000
      pool-name: HikariCP

  mail:
    host: smtp.qq.com
    port: 587
    username:    # 使用 QQ 邮箱地址
    password:    # 使用生成的授权码而不是邮箱密码
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
          ssl:
            trust: smtp.qq.com
    default-encoding: UTF-8
