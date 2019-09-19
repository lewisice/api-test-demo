---
description: >-
  A demo Jave project with tool gradle,for our CICD.
  Hope you gays can understand the process of CICD.
---


### API-TEST-DEMO
>Java project



#### 1.junit test
包含JUnit单元测试，运行`./gradlew clean build`

#### 2.build package
生成jar包，方便CI/CD及docker，运行`./gradlew -x jar build`

#### 3. Run

- local run

启动项目，通过`./gradlew bootRun`

API接口为`http://localhost:8080/api/increase?number=323`

返回num+1(0<num<1000),形式如`{'errorCode':0, 'data':'324'}`

- docker run

build docker image

`docker build -t api-demo:0.1 .`

run docker container

`docker run -d -p 8080:8080 api-demo:0.1 `

浏览器中访问 http://localhost:8080/api/increase?number=323


#### 4. Sonar Scan
>before run below command ,please set up sonarqube

```bash
./gradlew sonarqube \
    -Dsonar.projectKey=xxx \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=admin \
    -Dsonar.password=admin
```

#### 5. api test with jmeter

需要提前安装好jmeter

`/opt/apache-jmeter-5.1.1/bin/jmeter.sh  -n -t api-test/api-test-demo.jmx`


#### 4.Exception handle
- num不在0～1000，返回`{'errorCode':1, 'data':'please input [0,1000]'}`
- num在每100个数中，必有一个错误数，返回`{'errorCode':1, 'data':'internal error'}`