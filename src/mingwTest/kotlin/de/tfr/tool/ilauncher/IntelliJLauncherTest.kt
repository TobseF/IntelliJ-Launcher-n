package de.tfr.tool.ilauncher

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IntelliJLauncherTest {

    @Test
    fun testFindNewestVersion(){
        val versions = mutableListOf<Version>()
        versions += Version("191.0001.02")
        versions += Version("191.0001.01")
        assertEquals(Version("191.0001.02"), versions.findNewestVersion())

        versions += Version("191.0002.01")
        assertEquals(Version("191.0002.01"), versions.findNewestVersion())

        versions += Version("200.0001.01")
        assertEquals(Version("200.0001.01"), versions.findNewestVersion())
    }

    @Test
    fun testIsVersion(){
        assertTrue { isVersion("000.000") }
        assertTrue { isVersion("000.000.000") }
        assertTrue { isVersion("000") }

        assertFalse { isVersion("ab.cd") }
        assertFalse { isVersion("00a") }
    }
}