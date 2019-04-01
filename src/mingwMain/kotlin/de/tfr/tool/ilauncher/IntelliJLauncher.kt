package de.tfr.tool.ilauncher


val appData = SystemUtil.getEnvironmentVariable("LOCALAPPDATA")
var toolboxPath = """$appData\JetBrains\Toolbox"""
var channelsPath = """$toolboxPath\apps\IDEA-U\ch-0\"""
var runtimeSuffix = """\bin\idea64.exe"""


fun main(args: Array<String>) {
    runIntelliJ(getProjectPath(args))
}

private fun getProjectPath(args: Array<String>): String? {
    return args.firstOrNull { it.startsWith("project=") }?.substringAfter("project=")
}

fun isVersion(version: File) = version.isFolder() && isVersion(version.fileName!!)
fun isVersion(version: String) = version.matches("^[0-9]+(\\.[0-9]+)*$".toRegex())


fun runIntelliJ(projectPath: String?) {
    File(path = channelsPath)
        .listAllFolders().filter(::isVersion).map { Version(it.fileName!!) }
        .sortedDescending().firstOrNull()?.let { getIntelliJPath(it) }.let {
            it?.exec(projectPath)
        }
}

fun getIntelliJPath(version: Version) =
    File(
        path = channelsPath,
        fileName = version.version + runtimeSuffix,
        type = File.Type.File
    )