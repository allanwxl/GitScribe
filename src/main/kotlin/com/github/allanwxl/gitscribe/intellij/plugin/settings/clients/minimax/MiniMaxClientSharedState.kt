package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.minimax

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientSharedState
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(name = "MiniMaxClientSharedState", storages = [Storage("GitScribeMiniMax.xml")])
class MiniMaxClientSharedState : PersistentStateComponent<MiniMaxClientSharedState>, LlmClientSharedState {

    companion object {
        @JvmStatic
        fun getInstance(): MiniMaxClientSharedState = service()
    }

    @XCollection(style = XCollection.Style.v2)
    override val hosts = mutableSetOf("https://api.minimaxi.com/v1")

    @XCollection(style = XCollection.Style.v2)
    override val modelIds = mutableSetOf(
        "MiniMax-M2.7",
        "MiniMax-M2.7-highspeed",
        "MiniMax-M2.5",
        "MiniMax-M2.5-highspeed",
        "MiniMax-M2.1",
        "MiniMax-M2.1-highspeed",
        "MiniMax-M2"
    )

    override fun getState(): MiniMaxClientSharedState = this

    override fun loadState(state: MiniMaxClientSharedState) {
        hosts += state.hosts
        modelIds += state.modelIds
    }
}
