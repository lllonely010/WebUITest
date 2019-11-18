# WebUITest
Using Java, Junit5, Selenium WebDriver, Allue, Log4j

#Run all tests 
mvn test

#Report will be generated under project basedir /allure-report
mvn allure:report

#Report will be generated into temp folder. Web server with results will start. 
mvn allure:serve
