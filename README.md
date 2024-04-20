# Rock Paper Scissors Server

Это серверная часть игры "Камень, ножницы, бумага", написанная на Java и собранная с помощью Maven.

## Запуск приложения с помощью Docker

1. Сначала вам нужно склонировать репозиторий:

```bash
git clone https://github.com/erkinjavadeveloper/rock_paper_scissors.git
cd rock_paper_scissors
```

2. Затем вы должны собрать Docker образ:
```bash
docker build -t erkinjavadeveloper/rock_paper_scissors:1.0
```

3. После сборки образа, вы можете запустить его:
```bash
docker run -p 12345:12345 erkinjavadeveloper/rock_paper_scissors:1.0
```

После выполнения этих команд, ваше приложение должно быть доступно по адресу http://localhost:12345.

## Подключение к серверу с помощью Telnet

После запуска сервера, вы можете подключиться к нему с помощью Telnet. Для этого вам нужно открыть новое окно терминала и выполнить следующую команду:
```bash
telnet localhost 12345
```
