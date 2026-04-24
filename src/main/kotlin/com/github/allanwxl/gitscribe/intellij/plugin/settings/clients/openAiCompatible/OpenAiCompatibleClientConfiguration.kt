package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible

import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientConfiguration
import com.intellij.util.xmlb.annotations.Attribute
import com.intellij.util.xmlb.annotations.Transient

abstract class OpenAiCompatibleClientConfiguration(
    name: String,
    modelId: String,
    defaultHost: String
) : LlmClientConfiguration(name, modelId) {

    @Attribute
    var temperature: String = "0.7"

    @Attribute
    var host: String = defaultHost

    @Attribute
    var timeout: Int = 30

    @Attribute
    var tokenIsStored: Boolean = false

    @Transient
    var token: String? = null

    @Attribute
    var topP: Double? = null

    @Attribute
    var enableThinking: Boolean = false

    @Attribute
    var reasoningSplit: Boolean = false

    protected fun copyBaseTo(copy: OpenAiCompatibleClientConfiguration) {
        copy.id = id
        copy.name = name
        copy.modelId = modelId
        copy.cleanupRegex = cleanupRegex
        copy.cleanupRegexIgnoreCase = cleanupRegexIgnoreCase
        copy.temperature = temperature
        copy.host = host
        copy.timeout = timeout
        copy.tokenIsStored = tokenIsStored
        copy.topP = topP
        copy.enableThinking = enableThinking
        copy.reasoningSplit = reasoningSplit
    }
}
