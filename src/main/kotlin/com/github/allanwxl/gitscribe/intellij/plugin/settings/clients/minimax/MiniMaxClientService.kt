package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.minimax

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientService
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.APP)
class MiniMaxClientService(cs: CoroutineScope) : OpenAiCompatibleClientService<MiniMaxClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): MiniMaxClientService = service()
    }
}
