# 🚀 IntelliJ-Launcher
Native Windows Kotlin program that launches the latest installed IntelliJ with a specified project.  
This can be useful to launch IntelliJ e.g. on system startup.  
It provides a permanent shortcut which survives any update.  

It's also a short sample Application to for native Kotlin on Windows.

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