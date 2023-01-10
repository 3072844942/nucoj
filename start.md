## 环境配置

#### redis

> docker pull redis *//下载Redis镜像* 
>
> docker run --name redis  --restart=always -p 6379:6379 -d redis --requirepass "powerSma" *//启动Redis*

#### nginx

>docker pull nginx //下载nginx镜像
>docker run --name nginx --restart=always -p 80:80 -p 443:443 -d -v /usr/local/nginx/nginx.conf:/etc/nginx/nginx.conf -v /usr/local/react:/usr/local/react -v /usr/local/nginx/cert:/usr/local/nginx/cert nginx  //启动nginx，映射本地配置文件

#### rabbitmq

> docker pull rabbitmq:management //下载RabbitMQ镜像
> docker run --name rabbit --restart=always -p 15672:15672 -p 5672:5672  -d  rabbitmq:management   //启动RabbitMQ,默认guest用户，密码也是guest。

#### mongodb

> docker pull mongo
>
> docker run -itd --name mongo -p 27017:27017 mongo --auth // 开启身份认证

1. 进入docker

    > docker exec -it mongo

2. 创建根用户

    > ```
    > use admin;
    > db.createUser({ user:'root',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
    > db.auth('root', '123456')
    > ```

 3. 创建默认用户

    > use web;
    >
    > db.createUser({ user: 'admin', pwd: '124609', roles: [{ role: 'readWrite', db : 'web' }]});