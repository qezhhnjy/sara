# Ubuntu Mysql多实例及遇到的问题

标签（空格分隔）： mysql sysv-re-conf service psmouse

---

>**首先是关于打开和关闭触摸板的指令**

    sudo modprobe -r psmouse #关闭
    sudo modprobe psmouse #打开

##**Mysql5.7多实例安装部署实例**
https://www.linuxidc.com/Linux/2017-07/145343.htm
1. 通过官方下载对应的Linux,格式为tar.gz的完整二进制包(612M).
2. 解压MySql5.7包到指定目录
    
    tar -zxvf mysql.tar.gz -C /home/cotek/tools
3. 创建MySql软链接
    
    ln -s /home/cotek/tools/mysql /usr/local/mysql
4. 创建MySql用户
    
    useradd -r -s /sbin/nologin mysql
5. 在MySql二进制包目录中创建mysql-files目录[MySql数据导入/导出数据专放目录]
    
    mkdir -v /usr/local/mysql/mysql-files
6. 创建多实例数据目录

    mkdir -vp /data/mysql_data1
    mkdir -vp /data/mysql_data2
7. 修改MySql二进制包目录的所属用户及用户组

    chown root.mysql -R /home/cotek/tools/mysql #不行可以试试mysql.mysql
8. 修改MySql多实例数据目录的所属用户和用户组
    
    chown mysql.mysql -R /data
9. 配置MySql配置文件 /etc/my.cnf
```shell
[mysqld_multi]
mysqld    = /usr/local/mysql/bin/mysqld 
mysqladmin = /usr/local/mysql/bin/mysqladmin
log        = /tmp/mysql_multi.log
 
[mysqld1]
# 设置数据目录　[多实例中一定要不同]
datadir = /data/mysql_data1
# 设置sock存放文件名　[多实例中一定要不同]
socket = /tmp/mysql.sock1
# 设置监听开放端口　[多实例中一定要不同]
port = 3306
# 设置运行用户
user = mysql
# 关闭监控
performance_schema = off
# 设置innodb 缓存大小
innodb_buffer_pool_size = 32M
# 设置监听IP地址
bind_address = 0.0.0.0
# 关闭DNS 反向解析
skip-name-resolve = 0
 
[mysqld2]
datadir = /data/mysql_data2
socket = /tmp/mysql.sock2
port = 3307
user = mysql
performance_schema = off
innodb_buffer_pool_size = 32M
bind_address = 0.0.0.0
skip-name-resolve = 0
 
[mysqld3]
datadir = /data/mysql_data3
socket = /tmp/mysql.sock3
port = 3308
user = mysql
performance_schema = off
innodb_buffer_pool_size = 32M
bind_address = 0.0.0.0
skip-name-resolve = 0
 
[mysqld4]
datadir = /data/mysql_data4
socket = /tmp/mysql.sock4
port = 3309
user = mysql
performance_schema = off
innodb_buffer_pool_size = 32M
bind_address = 0.0.0.0
skip-name-resolve = 0
```
10. 初始化各个实例 [ 初始化完成后会自带随机密码在输出日志中 ]
```
[root@MySQL ~]# /usr/local/mysql/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data1
[root@MySQL ~]# /usr/local/mysql/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data2
[root@MySQL ~]# /usr/local/mysql/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data3
[root@MySQL ~]# /usr/local/mysql/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data4
```
11. 各实例开启 SSL 连接--这里成功会显示一堆密文乱码
```
[root@MySQL ~]# /usr/local/mysql/bin/mysql_ssl_rsa_setup --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data1
[root@MySQL ~]# /usr/local/mysql/bin/mysql_ssl_rsa_setup --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data2
[root@MySQL ~]# /usr/local/mysql/bin/mysql_ssl_rsa_setup --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data3
[root@MySQL ~]# /usr/local/mysql/bin/mysql_ssl_rsa_setup --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql_data4
```

12. 复制多实例脚本到服务管理目录下 [ /etc/init.d/ ]
```
[root@MySQL ~]# cp /usr/local/mysql/support-files/mysqld_multi.server /etc/init.d/mysqld_multi
```

13. 添加脚本执行权限
```[root@MySQL ~]# chmod +x /etc/init.d/mysqld_multi```

14. 添加进service服务管理--即开机自启动--可见下一个问题
15. 启动多实例:`/etc/init.d/mysqld_multi start`
16. 启动测试,查看是否启动成功:`/etc/init.d/mysqld_multi report`
17. 查看实例监听端口:`netstat -lntp |grep mysqld`
18. 连接测试
```
#这里的乱码就对应之前初始化的密码,如果前面配置成功并启动多实例,这里会进入一个mysql.
/usr/local/mysql/bin/mysql -S/tmp/mysql.sock1 -p'asdfasdf';
#修改密码
mysql>set password='123456';
```
到这里如果都没有问题,重启后就可以实现`mysql -uroot -p -S/tmp/mysql.sock1`的方式启动3306端口的MySql.

>**关于Ubuntu的开机启动命令**
https://www.linuxidc.com/Linux/2017-09/147166.htm
一. **可以在/etc/rc.local脚本里面,"exit 0"前面加入要执行的指令或脚本.**
二. **update-rc.d增加开机启动服务**
    1. 把要执行的脚本文件放到/etc/init.d,并给它执行权限.(可以手动通过`sudo /etc/init.d/test.sh start/stop/report/restart`来运行及查看)
    2. 执行`sudo update-rc.d test.sh defaults 90`添加到启动.(后面数字越高执行越晚)
    3. 添加后可通过一些方法来查看数据.
        ```sudo service xxx status
        sudo service xxx start
        sudo service xxx stop
        sudo service xxx restart
        sudo service --status-all #查看所有自启动服务
        update-rc.d -f startBlog remove 卸载服务
        ```
    4. 可以通过`suod apt-get install sysv-rc-conf`安装sysc-rc-conf,好像比较好用.
        
    




