package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible

import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeUtils.getCredentialAttributes
import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeUtils.retrieveToken
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.Notification
import com.github.allanwxl.gitscribe.intellij.plugin.notifications.sendNotification
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientService
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.util.text.nullize
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.model.chat.StreamingChatModel
import dev.langchain4j.model.openai.OpenAiChatModel
import dev.langchain4j.model.openai.OpenAiStreamingChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

abstract class OpenAiCompatibleClientService<C : OpenAiCompatibleClientConfiguration>(
    private val cs: CoroutineScope
) : LlmClientService<C>(cs) {

    override suspend fun buildChatModel(client: C): ChatModel {
        val token = client.token.nullize(true) ?: retrieveToken(client.id)?.toString(true)
        val builder = OpenAiChatModel.builder()
            .apiKey(token ?: "")
            .modelName(client.modelId)
            .timeout(Duration.ofSeconds(client.timeout.toLong()))
            .topP(client.topP)
            .baseUrl(client.host)

        client.temperature.takeIf { it.isNotBlank() }?.let {
            builder.temperature(it.toDouble())
        }

        return builder.build()
    }

    override suspend fun buildStreamingChatModel(client: C): StreamingChatModel {
        val token = client.token.nullize(true) ?: retrieveToken(client.id)?.toString(true)
        val builder = OpenAiStreamingChatModel.builder()
            .apiKey(token ?: "")
            .modelName(client.modelId)
            .timeout(Duration.ofSeconds(client.timeout.toLong()))
            .topP(client.topP)
            .baseUrl(client.host)

        client.temperature.takeIf { it.isNotBlank() }?.let {
            builder.temperature(it.toDouble())
        }

        return builder.build()
    }

    fun saveToken(client: C, token: String) {
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
