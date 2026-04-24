package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.githubModels

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientSharedState
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(name = "GithubModelsClientSharedState", storages = [Storage("GitScribeGithubModels.xml")])
class GitHubModelsClientSharedState : PersistentStateComponent<GitHubModelsClientSharedState>, LlmClientSharedState {

    companion object {
        @JvmStatic
        fun getInstance(): GitHubModelsClientSharedState = service()
    }

    @XCollection(style = XCollection.Style.v2)
    override val hosts = mutableSetOf("http://localhost:11434/")

    @XCollection(style = XCollection.Style.v2)
    override val modelIds: MutableSet<String> = mutableSetOf("gpt-4o-mini")

    override fun getState(): GitHubModelsClientSharedState = this

    override fun loadState(state: GitHubModelsClientSharedState) {
        modelIds += state.modelIds
        hosts += state.hosts
    }
}
