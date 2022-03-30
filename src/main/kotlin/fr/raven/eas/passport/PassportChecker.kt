package fr.raven.eas.passport

class PassportChecker {

    fun isExpired(passport: Passport): Boolean {
        val now = System.currentTimeMillis()

        return now - passport.userInfo.createdAt > PASSPORT_VALIDITY_MILLIS
    }

    companion object {
        const val PASSPORT_VALIDITY_MILLIS = 60 * 60 * 1000 // 1 hour
    }
}
