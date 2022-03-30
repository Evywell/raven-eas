package fr.raven.eas.test.unit.passport

import fr.raven.eas.passport.PassportBuilder
import fr.raven.eas.passport.PassportChecker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PassportBuilderTest {

    @Test
    fun `When creating a passport, it shouldn't be expired`() {
        // Arrange
        val builder = PassportBuilder()
        val checker = PassportChecker()

        // Act
        val passport = builder.create("")

        // Assert
        assertFalse(checker.isExpired(passport))
    }

    @Test
    fun `When creating a passport, the uuid should be correct`() {
        // Arrange
        val builder = PassportBuilder()
        val uuid = "ec239a69-af5a-4285-aee2-783befe7bb09"

        // Act
        val passport = builder.create(uuid)

        // Assert
        assertEquals(uuid, passport.userInfo.uuid)
    }

    @Test
    fun `When creating a passport, the creation date of user info should be correct`() {
        // Arrange
        val builder = PassportBuilder()
        val beforeDate = System.currentTimeMillis()

        // Act
        val passport = builder.create("ec239a69-af5a-4285-aee2-783befe7bb09")
        val afterDate = System.currentTimeMillis()

        // Assert
        assertTrue(passport.userInfo.createdAt >= beforeDate)
        assertTrue(passport.userInfo.createdAt <= afterDate)
    }
}
