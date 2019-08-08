# API-DEMO

1. JAVA工程
2. 通过`./gradlew bootRun`启动项目，API接口为`/api/increase?number=323`，返回num+1(0<num<1000),形式如`{error:0, data:324}`
3. 包含JUnit单元测试，通过`./gradlew clean build`运行测试
4. 异常处理
    1. num不在0～1000，返回`{error:1, data:'please input [0,1000]'}`
    2. num后两位与当前时间的分钟数一致时，返回`{error:1, data:'internal error'}`