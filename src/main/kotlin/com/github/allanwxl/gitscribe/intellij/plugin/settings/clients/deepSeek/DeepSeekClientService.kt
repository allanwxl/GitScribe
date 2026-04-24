package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.deepSeek

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientService
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.APP)
class DeepSeekClientService(cs: CoroutineScope) : OpenAiCompatibleClientService<DeepSeekClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): DeepSeekClientService = service()
    }
}
