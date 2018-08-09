# epam.com.Market
Для запуска данного проекта я использовал следующие версии программ:
JDK 1.8 (java version “1.8.0_161)
ItellijIdea 2018.1.5 Ultimate Edition
Tomcat 8.5
Apache Maven 3.5.0
MySQL 8.0
Chrome версия 67
MySQL
Выполнение проекта из среды разработки (Intellij Idea):
Откройте  Intellij Idea
В верхнем меню выберите VCS->Check out from Version Control ->Git
В появившемся окне вставьте url: https://github.com/Oleg700/epam.com.Market.git
Откройте проект
Установите Tomcat и MySQL сервер  если еще не установлен.
Скопируте в консоль базы данных скрипты из файлов ScriptsCreateDataBaseMarket.txt и ScriptsFillDataBaseMarket.txt
Зайдите в resources/dataBaseConfiguration.properties и измените login password на те какие у вас для вашей базы данных
Зайдите в resources/log4j.properties и измените путь log4j.appender.file.File на тот, куда будут сохраняться логи
В правом верхнем углу экрана выберите editConfiguration, слева +(add new Configuration) выберите Tomcat Server->Local
Далее в меню Deployment выберите + ->Market.war exploded
Проект готов к запуску, нажмите Run, и введите в адресной строке браузера http://localhost:8080/

В приложении, для входа как посетитель введите: 
Login: user
Password: user
Для входа как администратор введите:
Login: admin
Password: admin

Задание проекта:
Система Интернет-магазин. Администратор осуществляет ведение ката-
лога Товаров. Клиент делает и оплачивает Заказ на Товары. Администратор
может занести неплательщиков в «черный список».

