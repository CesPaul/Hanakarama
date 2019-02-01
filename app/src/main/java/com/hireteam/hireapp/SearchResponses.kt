package com.hireteam.hireapp

/*
object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}
*/
// Объекты хранят в себе ответы от сервера - результаты поиска или запроса
// Ответ на запрос регистрации
object RegistrationResponse{
    data class Result(val message: String)
}

// Ответ на запрос аутентофикации
object AuthorizationResponse{
    data class Result(val login: String, val name: String, val phone: String)
}
