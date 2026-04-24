package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.zhipuAi

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientSharedState
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(name = "ZhipuAiClientSharedState", storages = [Storage("GitScribeZhipuAi.xml")])
class ZhipuAiClientSharedState : PersistentStateComponent<ZhipuAiClientSharedState>, LlmClientSharedState {

    companion object {
        @JvmStatic
        fun getInstance(): ZhipuAiClientSharedState = service()
    }

    @XCollection(style = XCollection.Style.v2)
    override val hosts = mutableSetOf("https://open.bigmodel.cn/api/paas/v4/")

    @XCollection(style = XCollection.Style.v2)
    override val modelIds = mutableSetOf(
        "glm-5.1",
        "glm-4.7",
        "glm-4.6",
        "glm-4.5",
        "glm-z1-flash"
    )

    override fun getState(): ZhipuAiClientSharedState = this

    override fun loadState(state: ZhipuAiClientSharedState) {
        hosts += state.hosts
        modelIds += state.modelIds
    }
}
