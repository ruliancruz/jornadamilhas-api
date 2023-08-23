# jornadamilhas-api

Project Developed for Alura Backend Challenge 7.

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Name        | **Jornada Milhas API**
| :label: Technologies | Java 20, Spring Boot, Maven, MySQL
| :rocket: URL         | Comming soon
| :fire: Challenge     | https://www.alura.com.br/challenges/back-end-7

<!-- Inserir imagem com a #vitrinedev ao final do link -->
<!-- ![](https://via.placeholder.com/1200x500.png?text=imagem+lindona+do+meu+projeto#vitrinedev) -->

## Project Details

This is an API REST project developed with Java 20, Spring Boot, Maven and MySQL to provide information and resources about travel destinations, with photos, descriptions, prices and testimonials of users. It also has an integration with OpenAI API.

## Description

The challenge goal is to develop an API to manage travel destinations and testimonials, to register, search, modify and remove it, having also integration with an AI-powered language model.

## API Endpoints

### Testimonial Registration Endpoint

`POST /depoimentos`

Allow registration of a new testimonial. The body of the request must have a JSON object with `personName` (name of the testimonial author), `testimonialText` (testimonial text) and `photoPath` (author photo URL) as required fields. If done correctly it will return the body of a JSON with the data of the created object.

Request Example:

```json
{
    "personName": "John Galt",
    "testimonialText": "It was fun.",
    "photoPath": "imageurlpath.jpg"
}
```

Response example:

```json
{
    "id": 1,
    "personName": "John Galt",
    "testimonialText": "It was fun.",
    "photoPath": "imageurlpath.jpg"
}
```

### Random Testimonials Endpoint

`GET /depoimentos-home`

Returns up to 3 randomly selected testimonials as a JSON list in the response body.

Response example:

```json
[
    {
        "id": 1,
        "personName": "John Galt",
        "testimonialText": "It was fun.",
        "photoPath": "imageurlpath.jpg"
    },
    {
        "id": 4,
        "personName": "Leornardo da Vinci",
        "testimonialText": "I was very fun.",
        "photoPath": "image2urlpath.jpg"
    },
    {
        "id": 10,
        "personName": "Michelangelo",
        "testimonialText": "Not funny at all.",
        "photoPath": "image3urlpath.jpg"
    }
]
```

### Detailed Testimonial Query By Id Endpoint

`GET /depoimentos/{id}`

Returns the testimonial that matches the informed id in the endpoint. It will be returned as a JSON object in the response body. It's used to return the created object in POST and PUT requests on `/depoimentos` endpoint.

Response example:

```json
{
    "id": 1,
    "personName": "John Galt",
    "testimonialText": "It was fun.",
    "photoPath": "imageurlpath.jpg"
}
```

### Testimonial Update Endpoint

`PUT /depoimentos`

Allow updating the `personName` (name of the testimonial author), `testimonialText` (testimonial text) and `photoPath` (author photo URL) fields of the testimonial that matches the id informed in the JSON object sent in the request body. If done correctly it will return the body of a JSON with the data of the updated object.

Request example:

```json
{
    "id": 1,
    "testimonialText": "This is not fun anymore.",
    "photoPath": "imageurl4path.jpg"
}
```

Response example:

```json
{
    "id": 1,
    "personName": "John Galt",
    "testimonialText": "This is not fun anymore.",
    "photoPath": "imageurl4path.jpg"
}
```

### Testimonial Removal Endpoint

`DELETE /depoimentos/{id}`

Allow to delete the testimonial that matches the informed id in the endpoint.

### Destination Registration Endpoint

`POST /destinos`

Allow registration of a new destination. The body of request must have a JSON object with `name` (destination name), `photoPath` (destination photo URL), `photoPath2` (second destination photo URL), `meta` (destination meta) and `price` (destination price) as required fields that can also have optionally the `description` (destination description) field. If the description isn't informed, the API will auto generate a description for it using the OpenAi API, that will require to setup your OpenAi API key in the environment variable `OPENAI_API_KEY`. If done correctly it will return the body of a JSON with the data of the created object.

Request Example:

```json
{
    "name": "Waterdeep",
    "photoPath": "imageurl10path.jpg",
    "photoPath2": "imageurl11path.jpg",
    "meta": "Lorem ipsum",
    "price": "42"
}
```

Response example:

```json
{
    "id": 1,
    "name": "Waterdeep",
    "photoPath": "imageurl10path.jpg",
    "photoPath2": "imageurl11path.jpg",
    "meta": "Lorem ipsum",
    "description": "Waterdeep é uma cidade portuária fascinante na costa nordeste do continente de Faerûn. O lugar é conhecido como a Meca do Comércio por sua vasta economia baseada em exportações e serviços. Além disso, é um lugar próspero para culturas diversas. Com mais de 100 tipos diferentes de personagens e dezenas de templos, Waterdeep é um lugar incrível para visitar.",
    "price": "42"
}
```

### Destination Search By Name Endpoint

`GET /destinos?name={name}`

Returns all destinations that match the name informed as a parameter in the endpoint. The destinations will be returned as a JSON list in the response body.

Response example:

```json
[
    {
        "id": 1,
        "name": "Waterdeep",
        "photoPath": "imageurl10path.jpg",
        "photoPath2": "imageurl11path.jpg",
        "meta": "Lorem ipsum",
        "description": "Waterdeep é uma cidade portuária fascinante na costa nordeste do continente de Faerûn. O lugar é conhecido como a Meca do Comércio por sua vasta economia baseada em exportações e serviços. Além disso, é um lugar próspero para culturas diversas. Com mais de 100 tipos diferentes de personagens e dezenas de templos, Waterdeep é um lugar incrível para visitar.",
        "price": "42"
    },
    {
        "id": 2,
        "name": "Waterdeep",
        "photoPath": "imageurl20path.jpg",
        "photoPath2": "imageurl20path.jpg",
        "meta": "Ipsum Lorem",
        "description": "Waterdeep era uma cidade portuária fascinante na costa nordeste do continente de Faerûn..",
        "price": "90"
    }
]
```

### Destination Query By Id Endpoint

`GET /destinos/{id}`

Returns the testimonial that matches the informed id in the endpoint as a JSON object in the response body. Only the `name` (destination name), `photoPath` (destination photo URL), `photoPath2` (second destination photo URL), `meta` (destination meta) and `description` (destination description) fields will be returned.

```json
{
    "id": 1,
    "name": "Waterdeep",
    "photoPath": "imageurl10path.jpg",
    "photoPath2": "imageurl11path.jpg",
    "meta": "Lorem ipsum",
    "description": "Waterdeep é uma cidade portuária fascinante na costa nordeste do continente de Faerûn. O lugar é conhecido como a Meca do Comércio por sua vasta economia baseada em exportações e serviços. Além disso, é um lugar próspero para culturas diversas. Com mais de 100 tipos diferentes de personagens e dezenas de templos, Waterdeep é um lugar incrível para visitar."
}
```

### Detailed Destination Query By Id Endpoint

`GET /destinos/detalhado/{id}`

Returns the testimonial that matches the informed id in the endpoint. It will be returned as a JSON object in the response body. It's used to return the created object in POST and PUT requests on the `/destinos` endpoint.

```json
{
    "id": 1,
    "name": "Waterdeep",
    "photoPath": "imageurl10path.jpg",
    "photoPath2": "imageurl11path.jpg",
    "meta": "Lorem ipsum",
    "description": "Waterdeep é uma cidade portuária fascinante na costa nordeste do continente de Faerûn. O lugar é conhecido como a Meca do Comércio por sua vasta economia baseada em exportações e serviços. Além disso, é um lugar próspero para culturas diversas. Com mais de 100 tipos diferentes de personagens e dezenas de templos, Waterdeep é um lugar incrível para visitar.",
    "price": "42"
}
```

### Destination Update Endpoint

`PUT /depoimentos`

Allow updating the `name` (destination name), `photoPath` (destination photo URL), `photoPath2` (second destination photo URL), `meta` (destination meta) `description` (destination description) and `price` (destination price) fields of the destination that matches the id informed in the JSON object sent in the request body. If done correctly it will return the body of a JSON with the data of the updated object.

Request example:

```json
{
    "id": 1,
    "name": "Watershallow",
    "photoPath": "imageurl42path.jpg",
    "photoPath2": "imageurl43path.jpg",
    "meta": "Lorem ipsum lorem",
    "description": "Watershallow é uma cidade portuária fascinante.",
}
```

Response example:

```json
{
    "id": 1,
    "name": "Watershallow",
    "photoPath": "imageurl42path.jpg",
    "photoPath2": "imageurl43path.jpg",
    "meta": "Lorem ipsum lorem",
    "description": "Watershallow é uma cidade portuária fascinante.",
    "price": "42"
}
```

### Destination Removal Endpoint

`DELETE /depoimentos/{id}`

Allow to delete the destination that matches the informed id in the endpoint.

## Development Progress

Project: `Finished`

Swagger: `In Progress`

Deploy on Render: `In Progress`
