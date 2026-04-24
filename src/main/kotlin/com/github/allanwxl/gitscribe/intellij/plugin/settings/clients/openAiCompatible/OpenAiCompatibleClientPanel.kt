package com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.openAiCompatible

import com.github.allanwxl.gitscribe.intellij.plugin.GitScribeBundle.message
import com.github.allanwxl.gitscribe.intellij.plugin.emptyText
import com.github.allanwxl.gitscribe.intellij.plugin.settings.clients.LlmClientPanel
import com.github.allanwxl.gitscribe.intellij.plugin.temperatureValidNullable
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.ValidationInfoBuilder

class OpenAiCompatibleClientPanel<C : OpenAiCompatibleClientConfiguration>(
    private val clientConfiguration: C,
    private val service: OpenAiCompatibleClientService<C>,
    private val tokenExampleKey: String,
    private val tokenCommentKey: String
) : LlmClientPanel(clientConfiguration) {

    private val tokenPasswordField = JBPasswordField()
    private val topPTextField = JBTextField()

    override fun create() = panel {
        nameRow()
        hostRow(clientConfiguration::host.toNullableProperty())
        timeoutRow(clientConfiguration::timeout)
        tokenRow()
        modelIdRow()
        temperatureRow(clientConfiguration::temperature.toMutableProperty(), ValidationInfoBuilder::temperatureValidNullable)
        topPDoubleRow(topPTextField, clientConfiguration::topP.toNullableProperty())
        row {
            checkBox(message("settings.llmClient.enableThinking"))
                .bindSelected(clientConfiguration::enableThinking)
                .comment(message("settings.llmClient.enableThinking.comment"))
        }
        row {
            checkBox(message("settings.llmClient.reasoningSplit"))
                .bindSelected(clientConfiguration::reasoningSplit)
                .comment(message("settings.llmClient.reasoningSplit.comment"))
        }
        cleanUpRegexRow()
        verifyRow()
    }

    override fun verifyConfiguration() {
        clientConfiguration.host = hostComboBox.item
        clientConfiguration.timeout = socketTimeoutTextField.text.toInt()
        clientConfiguration.modelId = modelComboBox.item
        clientConfiguration.temperature = temperatureTextField.text
        clientConfiguration.token = String(tokenPasswordField.password)
        clientConfiguration.topP = topPTextField.text.toDoubleOrNull()

        service.verifyConfiguration(clientConfiguration, verifyLabel)
    }

    private fun Panel.tokenRow() {
        row {
            label(message("settings.llmClient.token"))
                .widthGroup("label")
            cell(tokenPasswordField)
                .bindText(getter = { "" }, setter = {
                    service.saveToken(clientConfiguration, it)
                })
                .emptyText(if (clientConfiguration.tokenIsStored) message("settings.llmClient.token.stored") else message(tokenExampleKey))
                .resizableColumn()
                .align(Align.FILL)
                .comment(message(tokenCommentKey), 50)
        }
    }
}
