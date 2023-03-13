# Test_BookLib_REST_API


<a target="_blank" href="https://github.com/mdgy/rest_api_test_task">приложение - Библиотека книг</a>



## Endpoints:

### GET /api/books - получить список всех книг
### GET /api/books/<book_id> - получить книгу по ее id <book_id>

### POST /api/books - добавить новую книгу

> - Тело request-а должно содержать Json с обязательным полем "name"
> - Поля "author", "year" и "isElectronicBook" являются необязательными, если их не передать, то заполнятся данными по-умолчанию. 
> - Поле "id" заполняется автоматически при добавлении новой книги к коллекцию.

### PUT /api/books/<book_id> - обновить информацию о книге по ее id <book_id>

> - Тело request-а должно содержать Json
> - с обязательным заполнением полей "name", "author", "year" и "isElectronicBook"

### DELETE /api/books/<book_id> - удалить книгу по ее id <book_id>
