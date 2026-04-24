package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.deepSeek

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientSharedState
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(name = "DeepSeekClientSharedState", storages = [Storage("GitScribeDeepSeek.xml")])
class DeepSeekClientSharedState : PersistentStateComponent<DeepSeekClientSharedState>, LlmClientSharedState {

    companion object {
        @JvmStatic
        fun getInstance(): DeepSeekClientSharedState = service()
    }

    @XCollection(style = XCollection.Style.v2)
    override val hosts = mutableSetOf(
        "https://api.deepseek.com",
        "https://api.deepseek.com/v1"
    )

    @XCollection(style = XCollection.Style.v2)
    override val modelIds = mutableSetOf(
        "deepseek-v4-flash",
        "deepseek-v4-pro",
        "deepseek-chat",
        "deepseek-reasoner"
    )

    override fun getState(): DeepSeekClientSharedState = this

    override fun loadState(state: DeepSeekClientSharedState) {
        hosts += state.hosts
        modelIds += state.modelIds
    }
}
