package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.zhipuAi

import com.github.allanwxl.gitscribe.intellij.plugin.Icons
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientConfiguration
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientPanel
import com.intellij.openapi.project.Project
import com.intellij.vcs.commit.AbstractCommitWorkflowHandler
import kotlinx.coroutines.Job
import javax.swing.Icon

class ZhipuAiClientConfiguration : OpenAiCompatibleClientConfiguration(
    CLIENT_NAME,
    "glm-5.1",
    "https://open.bigmodel.cn/api/paas/v4/"
) {

    companion object {
        const val CLIENT_NAME = "智谱"
    }

    override fun getClientName(): String {
        return CLIENT_NAME
    }

    override fun getClientIcon(): Icon {
        return Icons.ZHIPU_AI.getThemeBasedIcon()
    }

    override fun getSharedState(): ZhipuAiClientSharedState {
        return ZhipuAiClientSharedState.getInstance()
    }

    override fun generateCommitMessage(commitWorkflowHandler: AbstractCommitWorkflowHandler<*, *>, project: Project) {
        return ZhipuAiClientService.getInstance().generateCommitMessage(this, commitWorkflowHandler, project)
    }

    override fun getGenerateCommitMessageJob(): Job? {
        return ZhipuAiClientService.getInstance().generateCommitMessageJob
    }

    override fun clone(): LlmClientConfiguration {
        val copy = ZhipuAiClientConfiguration()
        copyBaseTo(copy)
        return copy
    }

    override fun panel() = OpenAiCompatibleClientPanel(
        this,
        ZhipuAiClientService.getInstance(),
        "settings.zhipuAi.token.example",
        "settings.zhipuAi.token.comment"
    )
}
