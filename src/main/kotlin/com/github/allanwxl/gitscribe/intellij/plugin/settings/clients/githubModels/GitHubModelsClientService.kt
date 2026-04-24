package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.githubModels

import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeUtils.getCredentialAttributes
import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeUtils.retrieveToken
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.Notification
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.sendNotification
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientService
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.util.text.nullize
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.model.chat.StreamingChatModel
import dev.langchain4j.model.github.GitHubModelsChatModel
import dev.langchain4j.model.github.GitHubModelsStreamingChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

@Service(Service.Level.APP)
class GitHubModelsClientService(private val cs: CoroutineScope) : LlmClientService<GitHubModelsClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): GitHubModelsClientService = service()
    }

    override suspend fun buildChatModel(client: GitHubModelsClientConfiguration): ChatModel {
        val token = client.token.nullize(true) ?: retrieveToken(client.id)?.toString(true)
        return GitHubModelsChatModel.builder()
            .gitHubToken(token)
            .modelName(client.modelId)
            .temperature(client.temperature.toDouble())
            .timeout(Duration.ofSeconds(client.timeout.toLong()))
            .topP(client.topP)
            .build()
    }

    override suspend fun buildStreamingChatModel(client: GitHubModelsClientConfiguration): StreamingChatModel? {
        val token = client.token.nullize(true) ?: retrieveToken(client.id)?.toString(true)
        return GitHubModelsStreamingChatModel.builder()
            .gitHubToken(token)
            .modelName(client.modelId)
            .temperature(client.temperature.toDouble())
            .timeout(Duration.ofSeconds(client.timeout.toLong()))
            .topP(client.topP)
            .build()
    }

    fun saveToken(client: GitHubModelsClientConfiguration, token: String) {
        cs.launch(Dispatchers.Default) {
            try {
                PasswordSafe.instance.setPassword(getCredentialAttributes(client.id), token)
                client.tokenIsStored = true
            } catch (e: Exception) {
                sendNotification(Notification.unableToSaveToken(e.message))
            }
        }
    }
}
