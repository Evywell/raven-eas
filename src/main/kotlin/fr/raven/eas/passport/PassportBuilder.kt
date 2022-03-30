package fr.raven.eas.passport

class PassportBuilder {

    fun create(uuid: String): Passport {
        val userInfo = UserInfo.newBuilder()
            .setUuid(uuid)
            .setCreatedAt(System.currentTimeMillis())
            .build()

        return Passport.newBuilder()
            .setUserInfo(userInfo)
            .build()
    }
}
