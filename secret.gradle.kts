val secretDebug by extra(
    listOf(
        Pair("fileName", "debug.keystore"),
        Pair("storePassword", "android"),
        Pair("keyPassword", "android"),
        Pair("keyAlias", "androiddebug_alias")
    )
)

val secretProd by extra(
    listOf(
        Pair("fileName", ""),
        Pair("storePassword", ""),
        Pair("keyPassword", ""),
        Pair("keyAlias", "")
    )
)

val stagingApi by extra(
    listOf(
        Pair("BASE_URL", "https://api-playground.appetiserdev.tech")
    )
)

val releaseApi by extra(
    listOf(
        Pair("BASE_URL", "https://api.appetiserdev.tech")
    )
)