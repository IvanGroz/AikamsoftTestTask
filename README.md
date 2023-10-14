# AikamsoftTestTask

## Стек технологий
* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
* ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
* ![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
* Clean Architecture
  
## Проверка работоспособности
 Для проверки работоспособности приложения необходимо запустить jar файл.  
 Исходные данные для jar-файла (дамп БД, json)  находится в директории `jarTest`
### Этапы проверки
1. Необходимо перенести БД из файла `dump` [таким образом](https://info-comp.ru/migrating-postgresql-database-to-another-server)
2. Внести изменения в /src/main/java/ru/ivan/Main.java, а именно в инициализации подключения к БД (в переменную `DATABASE`) необходимо указать местрополоение ВАШЕЙ БД и логин,пароль: <hr> ![image](https://github.com/IvanGroz/AikamsoftTestTask/assets/60167833/627ff6df-78b5-4ed7-a250-ceb99f917595)<hr>
3. Создать jar-артефакт в InteliJIDEA в папку `jarTest`
4. Открыв консоль `cmd` из директории `jarTest` исполнить следующие команды:
*  `java -jar testTask.jar search json.json out.json`
*  `java -jar testTask.jar stat json2.json out.json`
5. Все выходные данные будут появлять в указанном `out.json` файле. 
