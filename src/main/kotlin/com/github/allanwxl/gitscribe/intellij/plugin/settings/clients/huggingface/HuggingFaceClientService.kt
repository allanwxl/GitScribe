package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.huggingface

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
import dev.langchain4j.model.huggingface.HuggingFaceChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

@Service(Service.Level.APP)
class HuggingFaceClientService(private val cs: CoroutineScope) : LlmClientService<HuggingFaceClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): HuggingFaceClientService = service()
    }

    override suspend fun buildChatModel(client: HuggingFaceClientConfiguration): ChatModel {
        val token = client.token.nullize(true) ?: retrieveToken(client.id)?.toString(true)

        return HuggingFaceChatModel.builder()
            .accessToken(token)
            .modelId(client.modelId)
            .temperature(client.temperature.toDouble())
            .timeout(Duration.ofSeconds(client.timeout.toLong()))
            .maxNewTokens(client.maxNewTokens)
            .waitForModel(client.waitForModel)
            .build()
    }

    override suspend fun buildStreamingChatModel(client: HuggingFaceClientConfiguration) = null

    fun saveToken(client: HuggingFaceClientConfiguration, token: String) {
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
