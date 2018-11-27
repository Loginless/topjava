Get all meals:
curl -X GET \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Postman-Token: c126cf64-fd86-4543-a6ab-f41f481c4e81' \
  -H 'cache-control: no-cache'
  
Get Meal by id:
  curl -X GET \
  http://localhost:8080/topjava/rest/meals/100002 \
  -H 'Postman-Token: 20d3c93e-a0f8-49f7-97d8-5c3ba44c6d81' \
  -H 'cache-control: no-cache'
  
Create Meal:
curl -X POST \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 23aeb4b5-8f25-408d-b73c-94130c39e616' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":"2015-05-25T10:00","description":"NewMeal","calories":2222}'
 
Update Meal:
curl -X PUT \
  http://localhost:8080/topjava/rest/meals/100002 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ece07c7a-64ce-48f1-b5d0-8868cc3fce3b' \
  -H 'cache-control: no-cache' \
  -d '{"id":100002,"dateTime":"2015-05-30T10:00","description":"UpdatedMeal","calories":1111}'
  
  
Get Meals filtered by date/time:
curl -X POST \
  'http://localhost:8080/topjava/meals/filter?startDate=2015-05-31&endDate=2015-05-31&startTime=10:00&endTime=20:00' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: e74ff009-43ee-46ca-80e3-6971b9f62158' \
  -H 'cache-control: no-cache'
  
 