# 🚀 IntelliJ-Launcher
[![Kotlin Native](https://img.shields.io/badge/Kotlin_Native-1.3.72-blue.svg?style=flat&logo=kotlin&logoColor=white)](http://kotlinlang.org)

Native Windows Kotlin program that launches the latest installed IntelliJ with a specified project.  
This can be useful to launch IntelliJ e.g. on system startup.  
It provides a permanent shortcut which survives any update.  

It's also a short sample application to for Kotlin Native on Windows.

### 📦 [Download](https://github.com/TobseF/IntelliJ-Launcher-n/releases/latest/download/intellij-launcher.exe)

## ✅ Requirements
* IntelliJ Ultimate

> The executable runs without a Java Virtual machine!

## 💡 Usage
Run intellij-runner.exe with click, bash or link:
``` shell
intellij-runner.exe project={your-path}
```

## 🔨 Build
Run gradle command __run\\__ `runReleaseExecutableMingw`.
