package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.siliconFlow

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientSharedState
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(name = "SiliconFlowClientSharedState", storages = [Storage("GitScribeSiliconFlow.xml")])
class SiliconFlowClientSharedState : PersistentStateComponent<SiliconFlowClientSharedState>, LlmClientSharedState {

    companion object {
        @JvmStatic
        fun getInstance(): SiliconFlowClientSharedState = service()
    }

    @XCollection(style = XCollection.Style.v2)
    override val hosts = mutableSetOf("https://api.siliconflow.cn/v1")

    @XCollection(style = XCollection.Style.v2)
    override val modelIds = mutableSetOf(
        "Pro/zai-org/GLM-5",
        "Pro/zai-org/GLM-4.7",
        "deepseek-ai/DeepSeek-V3.2",
        "Pro/deepseek-ai/DeepSeek-V3.2",
        "Qwen/Qwen3-8B"
    )

    override fun getState(): SiliconFlowClientSharedState = this

    override fun loadState(state: SiliconFlowClientSharedState) {
        hosts += state.hosts
        modelIds += state.modelIds
    }
}
