package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.siliconFlow

import com.github.allanwxl.gitscribe.intellij.plugin.Icons
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientPanel
import com.intellij.openapi.project.Project
import com.intellij.vcs.commit.AbstractCommitWorkflowHandler
import kotlinx.coroutines.Job
import javax.swing.Icon

class SiliconFlowClientConfiguration : OpenAiCompatibleClientConfiguration(
    CLIENT_NAME,
    "Pro/zai-org/GLM-4.7",
    "https://api.siliconflow.cn/v1"
) {

    companion object {
        const val CLIENT_NAME = "硅基流动"
    }

    override fun getClientName(): String {
        return CLIENT_NAME
    }

    override fun getClientIcon(): Icon {
        return Icons.SILICON_FLOW.getThemeBasedIcon()
    }

    override fun getSharedState(): SiliconFlowClientSharedState {
        return SiliconFlowClientSharedState.getInstance()
    }

    override fun generateCommitMessage(commitWorkflowHandler: AbstractCommitWorkflowHandler<*, *>, project: Project) {
        return SiliconFlowClientService.getInstance().generateCommitMessage(this, commitWorkflowHandler, project)
    }

    override fun getGenerateCommitMessageJob(): Job? {
        return SiliconFlowClientService.getInstance().generateCommitMessageJob
    }

    override fun clone(): LlmClientConfiguration {
        val copy = SiliconFlowClientConfiguration()
        copyBaseTo(copy)
        return copy
    }

    override fun panel() = OpenAiCompatibleClientPanel(
        this,
        SiliconFlowClientService.getInstance(),
        "settings.siliconFlow.token.example",
        "settings.siliconFlow.token.comment"
    )
}
