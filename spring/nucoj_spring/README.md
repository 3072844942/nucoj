**NUCOJ的后端项目**

### 启动
1. 确保applicaion.yml的配置为prod， 且配置正确
2. mvn package 或者使用第三方工具完成打包
3. 将打包文件放到本地deploy文件夹下
4. 将deploy文件夹下的全部内容发送到服务器上
5. 运行build.sh

### 遗漏

+ [ ] 重新设计一套API

+ [ ] 题目提交后的记录没有写入到用户记录
  + 对于用户的已完成题目和尝试题目还需要更新
+ [ ] websocket连接
+ [ ] 后台发送通知后， websocket发送到前台
+ [ ] 定时任务， 比赛开始， 比赛结束时间提醒
+ [ ] 比赛最后一小时封榜， 另一种形式的存储策略