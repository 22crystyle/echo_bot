# echoBot

**echoBot** — это простой бот на Spring Boot, который работает с [VK Callback API](https://dev.vk.com/ru/api/callback/getting-started) и отвечает на входящие сообщения, цитируя текст пользователя в формате:

> Вы сказали: _текст сообщения_

---

## 🚀 Возможности

- Поддержка событий VK типа `confirmation` и `message_new`
- Отправка сообщений пользователю через VK API
- Асинхронная отправка сообщений (`@Async`)
- Конфигурация через `.env` или `application.properties`
- Без использования сторонних библиотек VK SDK

---

## 📦 Стек технологий

- Java 21+
- Spring Boot 3.x
- Spring Web
- Spring Async
- RestClient (Spring 6+)
- Gradle
- Lombok

---

## ⚙️ Конфигурация

Настройки проекта задаются через `application.properties` или `.env`. Пример:

```properties
vk.token=ВАШ_ТОКЕН
vk.confirmation=СТРОКА_ПОДТВЕРЖДЕНИЯ
vk.apiUrl=https://api.vk.com/method/messages.send
vk.v=5.199
```
⚠️ Все параметры обязательны.

## 📂 Структура проекта
```bash
src/
├── config/             # Конфигурационные классы
├── controller/         # Контроллер обработки Callback API
├── dto/                # DTO классы для запроса и ответа
├── handler/            # Основная логика обработки событий
├── service/            # Сервис для отправки сообщений
```

## 🧪 Запуск проекта
```bash
./gradlew bootRun
```
