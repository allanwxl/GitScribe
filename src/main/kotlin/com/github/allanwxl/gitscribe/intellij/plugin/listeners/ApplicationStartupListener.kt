package com.github.allanwxl.gitscribe.intellij.plugin.listeners

import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeBundle
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.Notification
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.sendNotification
import com.github.allanwxl.gitscribe.intellij.plugin.settings.AppSettings2
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class ApplicationStartupListener : ProjectActivity {

    private var firstTime = true
    override suspend fun execute(project: Project) {
        showVersionNotification(project)
    }
    private fun showVersionNotification(project: Project) {
        val settings = AppSettings2.instance
        val version = GitScribeBundle.plugin()?.version

        if (version == settings.lastVersion) {
            return
        }

        settings.lastVersion = version
        if (firstTime && version != null) {
            sendNotification(Notification.welcome(version), project)
        }
        firstTime = false
    }
}
