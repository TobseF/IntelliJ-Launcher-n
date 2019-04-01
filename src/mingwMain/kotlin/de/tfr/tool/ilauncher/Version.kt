package de.tfr.tool.ilauncher

import kotlin.math.max

data class Version(val version: String) : Comparable<Version> {

    private fun String.split() = this.split("\\.".toRegex()).toTypedArray()

    override fun compareTo(other: Version): Int {
        val thisParts = version.split()
        val thatParts = other.version.split()
        val length = max(thisParts.size, thatParts.size)
        for (i in 0 until length) {
            val thisPart = if (i < thisParts.size) thisParts[i].toInt()
            else 0
            val thatPart = if (i < thatParts.size) thatParts[i].toInt()
            else 0
            if (thisPart < thatPart) return -1
            if (thisPart > thatPart) return 1
        }
        return 0
    }
}