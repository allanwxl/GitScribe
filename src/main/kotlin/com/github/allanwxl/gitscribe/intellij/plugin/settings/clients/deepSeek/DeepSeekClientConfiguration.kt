package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.deepSeek

import com.github.allanwxl.gitscribe.intellij.plugin.Icons
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientPanel
import com.intellij.openapi.project.Project
import com.intellij.vcs.commit.AbstractCommitWorkflowHandler
import kotlinx.coroutines.Job
import javax.swing.Icon

class DeepSeekClientConfiguration : OpenAiCompatibleClientConfiguration(
    CLIENT_NAME,
    "deepseek-v4-flash",
    "https://api.deepseek.com"
) {

    companion object {
        const val CLIENT_NAME = "DeepSeek"
    }

    override fun getClientName(): String {
        return CLIENT_NAME
    }

    override fun getClientIcon(): Icon {
        return Icons.DEEP_SEEK.getThemeBasedIcon()
    }

    override fun getSharedState(): DeepSeekClientSharedState {
        return DeepSeekClientSharedState.getInstance()
    }

    override fun generateCommitMessage(commitWorkflowHandler: AbstractCommitWorkflowHandler<*, *>, project: Project) {
        return DeepSeekClientService.getInstance().generateCommitMessage(this, commitWorkflowHandler, project)
    }

    override fun getGenerateCommitMessageJob(): Job? {
        return DeepSeekClientService.getInstance().generateCommitMessageJob
    }

    override fun clone(): LlmClientConfiguration {
        val copy = DeepSeekClientConfiguration()
        copyBaseTo(copy)
        return copy
    }

    override fun panel() = OpenAiCompatibleClientPanel(
        this,
        DeepSeekClientService.getInstance(),
        "settings.deepSeek.token.example",
        "settings.deepSeek.token.comment"
    )
}
