### API-TEST-DEMO
>Java project

#### 1.junit test
包含JUnit单元测试，通过`./gradlew clean build`运行测试

#### 2.build package
生成jar包，方便CI/CD及docker，通过`./gradlew -x jar build`运行测试

#### 3. Run

- local run

启动项目，通过`./gradlew bootRun`
API接口为`http://localhost:8080/api/increase?number=323`，返回num+1(0<num<1000),形式如`{error:0, data:'324'}`

- docker run

build docker image
`docker build -t api-demo:0.1 .`

run docker container
`docker run -d -p 8080:8080 spring-boot-app:latest`

浏览器中访问 http://localhost:8080/api/increase?number=323

#### 4.Exception handle
- num不在0～1000，返回`{error:1, data:'please input [0,1000]'}`
- num在每100个数中，必有一个错误数，返回`{error:1, data:'internal error'}`


