package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.minimax

import com.github.allanwxl.gitscribe.intellij.plugin.Icons
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientPanel
import com.intellij.openapi.project.Project
import com.intellij.vcs.commit.AbstractCommitWorkflowHandler
import kotlinx.coroutines.Job
import javax.swing.Icon

class MiniMaxClientConfiguration : OpenAiCompatibleClientConfiguration(
    CLIENT_NAME,
    "MiniMax-M2.7-highspeed",
    "https://api.minimaxi.com/v1"
) {

    companion object {
        const val CLIENT_NAME = "MiniMax"
    }

    override fun getClientName(): String {
        return CLIENT_NAME
    }

    override fun getClientIcon(): Icon {
        return Icons.MINIMAX.getThemeBasedIcon()
    }

    override fun getSharedState(): MiniMaxClientSharedState {
        return MiniMaxClientSharedState.getInstance()
    }

    override fun generateCommitMessage(commitWorkflowHandler: AbstractCommitWorkflowHandler<*, *>, project: Project) {
        return MiniMaxClientService.getInstance().generateCommitMessage(this, commitWorkflowHandler, project)
    }

    override fun getGenerateCommitMessageJob(): Job? {
        return MiniMaxClientService.getInstance().generateCommitMessageJob
    }

    override fun clone(): LlmClientConfiguration {
        val copy = MiniMaxClientConfiguration()
        copyBaseTo(copy)
        return copy
    }

    override fun panel() = OpenAiCompatibleClientPanel(
        this,
        MiniMaxClientService.getInstance(),
        "settings.miniMax.token.example",
        "settings.miniMax.token.comment"
    )
}
