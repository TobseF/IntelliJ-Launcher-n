package de.tfr.tool.ilauncher

import kotlinx.cinterop.alloc
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import platform.windows.*

data class File(val fileName: String? = null, val path: String, val type: Type = Type.Unknown) {

    enum class Type { File, Folder, Unknown }

    fun toAbsolutePath(): String {
        return path + fileName
    }

    override fun toString() = "$type:${toAbsolutePath()}"

    fun listAllFolders(): List<File> {
        return listAllFiles().filter { file -> file.type == Type.Folder }
    }

    fun isFolder() = type == Type.Folder

    fun listAllFiles(): List<File> {
        val searchPath = "$path*".replace("/", "\\")
        val files = mutableListOf<File>()
        val fileData = nativeHeap.alloc<_WIN32_FIND_DATAA>()
        val handle: HANDLE? = FindFirstFileA(searchPath, fileData.ptr)
        println("Search: $searchPath")


        var fileNumber = 0
        val maxFilesToScan = 1000
        if (handle != INVALID_HANDLE_VALUE) {
            do {
                val fileName = fileData.cFileName.toKString()
                println(fileName)
                var type = Type.Unknown

                if (fileData.dwFileAttributes.toInt() and FILE_ATTRIBUTE_NORMAL > 0) {
                    type = Type.File
                }
                if (fileData.dwFileAttributes.toInt() and FILE_ATTRIBUTE_DIRECTORY > 0) {
                    type = Type.Folder
                }
                files += File(fileName = fileName, path = path, type = type)
                fileNumber++
            } while (FindNextFileA(handle, fileData.ptr) != 0 && fileNumber < maxFilesToScan)

            FindClose(handle)
        } else {
            println("Illegal Path")
        }
        return files

    }

    fun exec(params: String? = "") {
        val command = "${toAbsolutePath()} ${params ?: ""}"
        println("Running: $command")
        WinExec(command, SW_SHOW)
    }

}