package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.zhipuAi

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible.OpenAiCompatibleClientService
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.APP)
class ZhipuAiClientService(cs: CoroutineScope) : OpenAiCompatibleClientService<ZhipuAiClientConfiguration>(cs) {

    companion object {
        @JvmStatic
        fun getInstance(): ZhipuAiClientService = service()
    }
}
