package com.radiostations.app.data.api.exception

class ApiException(
    var code: Int,
    message: String?
) : Throwable(message)