# smartdeltasystems
REST сервис получения информации о студентах.

Получение списка студентов (GET)

bash
curl -X GET http://localhost:8080/students -H "Authorization: Bearer YOUR_ACCESS_TOKEN"

Добавление нового студента (POST)

bash
curl -X POST http://localhost:8080/students -H "Authorization: Bearer YOUR_ACCESS_TOKEN" -H "Content-Type: application/json" -d '{
  "lastName": "Иванов",
  "firstName": "Иван",
  "middleName": "Иванович",
  "group": "Группа 1",
  "averageGrade": 4.5
}'

Обновление студента (PUT)

bash
curl -X PUT http://localhost:8080/students/{id} -H "Authorization: Bearer YOUR_ACCESS_TOKEN" -H "Content-Type: application/json" -d '{
  "lastName": "Петров",
  "firstName": "Петр",
  "middleName": "Петрович",
  "group": "Группа 2",
  "averageGrade": 4.8
}'

Удаление студента (DELETE)

bash
curl -X DELETE http://localhost:8080/students/{id} -H "Authorization: Bearer YOUR_ACCESS_TOKEN"

Развертывание проекта

  Убедитесь, что у вас установлен Java 17 и MongoDB.
  Клонируйте репозиторий с кодом.
  Запустите приложение с помощью команды:

  bash
  mvn spring-boot:run

Проверьте работу сервиса на локальном сервере по адресу http://localhost:8080.
