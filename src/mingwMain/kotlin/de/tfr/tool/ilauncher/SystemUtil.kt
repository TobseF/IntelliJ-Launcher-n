package de.tfr.tool.ilauncher

import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.windows.DWORD
import platform.windows.GetEnvironmentVariableW
import platform.windows.WCHARVar

object SystemUtil {
    /**
     * Maximum length of an Windows environment variable.
     * https://docs.microsoft.com/en-us/windows/desktop/api/winbase/nf-winbase-getenvironmentvariable
     */
    private const val environmentVariableMaxLength = 32767

    /**
     * @return System environment variable for the given key
     */
    fun getEnvironmentVariable(property: String): String {
        memScoped {
            val bufferLength = environmentVariableMaxLength
            val bufferSize: DWORD = bufferLength.toUInt()
            val allocArray = allocArray<WCHARVar>(bufferLength)
            GetEnvironmentVariableW(property, allocArray, bufferSize)
            return allocArray.toKString()
        }
    }

}