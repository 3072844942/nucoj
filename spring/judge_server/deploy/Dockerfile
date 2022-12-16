FROM centos:7
WORKDIR .

COPY . .

# 安装运行依赖 以及运行环境的初始化
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' > /etc/timezone \
    && mkdir /usr/local/upload \
    && cp -r scripts /usr/local/upload \
    && mkdir /usr/local/upload/resolutions \
    && mkdir /usr/local/upload/submissions \
    && yum -y upgrade \
    && yum -y install java-1.8.0-openjdk.x86_64 java-1.8.0-openjdk-devel python3 gcc gcc-c++ libseccomp-devel git cmake make zip unzip \
    && chmod +x -R /usr/local/upload/scripts

# 暴露8080端口
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]