mvn clean package && ^
mvn tomee:build && ^
copy .env_local .\target\apache-tomee\.env && ^
mvn tomee:run