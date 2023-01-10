# nucoj管理端

### 启动
1. npm run build
2. 将build文件夹传输到服务器的/usr/local/react文件夹下

### 遗漏
+ [ ] 比赛实时应当可以给某部分人发通知
+ [ ] 题单不能上到vjudge那种程度的话， 没有意义
+ [ ] 角色的创建管理和权限菜单管理
+ [ ] 用户的热力图
  + https://echarts.apache.org/examples/zh/editor.html?c=calendar-heatmap&lang=ts

### BUG?
+ [ ] 比我想象中的难搞， 动态创建路由的方法不太适用， 于是打算直接写好全部路由，由页面来单独判断是否拥有权限。
+ + 带来问题： 管理端需要每次判断权限， 从后端获取所有权限， 权限不足需要提醒