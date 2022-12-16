# nucoj管理端

### 时间线
+ 2022/12/11 比赛
+ 2022/12/09 比赛添加
+ 2022/11/18 系统管理模块
+ 2022/11/07 文章类模块
+ 2022/11/05 侧边栏
+ 2022/11/01 初步计划

### BUG?
+ [ ] 比我想象中的难搞， 动态创建路由的方法不太适用， 于是打算直接写好全部路由，由页面来单独判断是否拥有权限。
+ + 带来问题： 管理端需要每次判断权限， 从后端获取所有权限， 权限不足需要提醒

### 思路
+ 管理端将全部路由写好， 页面单独判断是否拥有权限提醒用户
+ 侧边栏导航可以动态获取