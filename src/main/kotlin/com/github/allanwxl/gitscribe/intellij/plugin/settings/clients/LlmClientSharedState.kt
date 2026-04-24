package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients

interface LlmClientSharedState {

    val hosts: MutableSet<String>

    val modelIds: MutableSet<String>
}
