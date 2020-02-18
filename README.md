#### 介绍
jxadmin 的基础版


#### 项目结构
- base-common 系统公共模块
- base-main 核心业务模块（系统启动入口）
- base-system 系统管理模块
- base-login 登录授权模块
- base-rbac 权限管理模块
- base-log 系统日志模块


#### 用法
rsa前端公钥加密：
```bash 
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCluMT0YKLYd2Cx4trilhl/5+wf
dSGr8dvHOmt1gJQOaOxJhJLXwZbhz6I/1Ra9y0rCzg2ZGM+fCooY2tmnLa35veQw
Ss1wTTUNfy+cVD7gtGKhJkdKSv3rcnGQv1PVg9jf6XyDm8L8e6zy4C12TzjJ4Lel
o6GfVL/iQenwQzChLQIDAQAB
```

登录密码为：123456，登录密码加密后为：
```bash
VlTQ+I5aLBMlB7SF2ZI7nAuYTdy6bca+x9yJyNndNC+ymLemi0DymVlepcOuLF59XveYIkkX3UXc2CXJS4ngnNGlRh1g/eqawi2fpvfIiukmauzG4Y7EdRb5yCdHgD2HOsiUR3DwI2v8ERp3dOKFPzyhlGFnl6DAvD5YGOCNlxs=
```