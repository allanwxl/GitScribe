package com.github.allanwxl.gitscribe.intellij.plugin

import com.github.allanwxl.gitscribe.intellij.plugin.settings.AppSettings2
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.DumbAware

class GitScribeSplitButtonAction : SplitButtonAction(object : ActionGroup() {

    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val configurations = AppSettings2.instance.llmClientConfigurations.sortedWith(
            compareBy<LlmClientConfiguration> {
                it.id != AppSettings2.instance.activeLlmClientId
            }.thenBy {
                it.name
            }
        )

        val actions = mutableListOf<AnAction>()
        if (configurations.isNotEmpty()) {
            actions.add(configurations.first())

            if (configurations.size > 1) {
                actions.add(Separator.getInstance())
            }

            actions.addAll(configurations.drop(1))
        }

        return actions.toTypedArray()
    }
}), DumbAware {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }
}
