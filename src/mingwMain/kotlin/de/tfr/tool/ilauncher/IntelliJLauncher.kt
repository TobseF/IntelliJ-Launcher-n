package de.tfr.tool.ilauncher


val appData = SystemUtil.getEnvironmentVariable("LOCALAPPDATA")
val toolboxPath = """$appData\JetBrains\Toolbox"""
val channelsPath = """$toolboxPath\apps\IDEA-U\ch-0\"""
val runtimeSuffix = """\bin\idea64.exe"""


fun main(args: Array<String>) {
    runIntelliJ(getProjectPath(args))
}

fun runIntelliJ(projectPath: String?) {
    File(path = channelsPath)
        .listAllFolders()
        .filter(::isVersion)
        .mapToVersion()
        .findNewestVersion()
        ?.getIntellIJPath()
        ?.exec(params  = projectPath)
}

private fun getProjectPath(args: Array<String>): String? {
    return args.firstOrNull {
        it.startsWith("project=") }
         ?.substringAfter("project=")
}

fun isVersion(version: File) = version.isFolder() && isVersion(version.fileName!!)

fun isVersion(version: String) = version.matches("^[0-9]+(\\.[0-9]+)*$".toRegex())

fun Iterable<Version>.findNewestVersion(): Version? {
    return this.sortedDescending().firstOrNull()
}

fun Iterable<File>.mapToVersion(): List<Version> {
    return this.map { Version(it.fileName!!) }
}

fun Version.getIntellIJPath(): File =
    File(
        path = channelsPath,
        fileName = this.version + runtimeSuffix,
        type = File.Type.File
    )