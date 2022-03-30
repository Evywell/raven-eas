package fr.raven.eas.test.unit.passport

import fr.raven.eas.passport.Passport
import fr.raven.eas.passport.PassportChecker
import fr.raven.eas.passport.UserInfo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PassportCheckerTest {

    @Test
    fun `A passport with a creation date older than the expiration limit should be expired`() {
        // Arrange
        val checker = PassportChecker()
        val now = System.currentTimeMillis()
        // Expiration of 1 minute
        val date = now - PassportChecker.PASSPORT_VALIDITY_MILLIS - (1000 * 60)
        val userInfo = UserInfo.newBuilder().setCreatedAt(date).build()
        val passport = Passport.newBuilder().setUserInfo(userInfo).build()

        // Act
        val isExpired = checker.isExpired(passport)

        // Assert
        assertTrue(isExpired)
    }

    @Test
    fun `A passport with a creation date younger than the expiration limit should not be expired`() {
        // Arrange
        val checker = PassportChecker()
        val now = System.currentTimeMillis()
        // 1 minute remaining
        val date = now - PassportChecker.PASSPORT_VALIDITY_MILLIS + (1000 * 60)
        val userInfo = UserInfo.newBuilder().setCreatedAt(date).build()
        val passport = Passport.newBuilder().setUserInfo(userInfo).build()

        // Act
        val isExpired = checker.isExpired(passport)

        // Assert
        assertFalse(isExpired)
    }
}
