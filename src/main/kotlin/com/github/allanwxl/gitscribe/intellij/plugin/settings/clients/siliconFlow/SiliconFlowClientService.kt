package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.siliconFlow

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientService
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.APP)
class SiliconFlowClientService(cs: CoroutineScope) : OpenAiCompatibleClientService<SiliconFlowClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): SiliconFlowClientService = service()
    }
}
