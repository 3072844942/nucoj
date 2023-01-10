**判题后端**

**改编自YJudge[https://github.com/yuzhanglong/YuJudge-JudgeHost](https://github.com/yuzhanglong/YuJudge-JudgeHost)**

### 启动
1. 确保applicaion.yml配置正确
2. mvn package 或者使用第三方工具完成打包
3. 将打包文件放到本地deploy文件夹下
4. 将deploy文件夹下的全部内容发送到服务器上
5. 运行build.sh

### 遗漏
+ [ ] 作为一个判题后端， 不再需要存储判题记录
  + 删除redis记录结果部分， 但是要缓存测试输入
  + 允许删除redis的所有测试输入记录