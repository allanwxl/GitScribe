package com.github.allanwxl.gitscribe.intellij.plugin

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import javax.swing.Icon

object Icons {

    data class GitScribeIcon(val bright: String, val dark: String?) {

        fun getThemeBasedIcon(): Icon {
            return if (JBColor.isBright() || dark == null) {
                IconLoader.getIcon(bright, javaClass)
            } else {
                IconLoader.getIcon(dark, javaClass)
            }
        }
    }

    val GITSCRIBE = GitScribeIcon("/icons/gitScribe15.svg", null)
    val OPEN_AI = GitScribeIcon("/icons/openai15bright.svg", "/icons/openai15dark.svg")
    val OLLAMA = GitScribeIcon("/icons/ollama15bright.svg", "/icons/ollama15dark.svg")
    val QIANFAN = GitScribeIcon("/icons/qianfan.png", null)
    val GEMINI_VERTEX = GitScribeIcon("/icons/geminiVertex.svg", null)
    val GEMINI_GOOGLE = GitScribeIcon("/icons/geminiGoogle.svg", null)
    val ANTHROPIC = GitScribeIcon("/icons/anthropic15bright.svg", "/icons/anthropic15dark.svg")
    val CLAUDE_CODE = GitScribeIcon("/icons/claudeCode15.svg", null)
    val CODEX = GitScribeIcon("/icons/codex15bright.svg", "/icons/codex15dark.svg")
    val AZURE_OPEN_AI = GitScribeIcon("/icons/azureOpenAi.svg", null)
    val HUGGING_FACE = GitScribeIcon("/icons/huggingface.svg", null)
    val GITHUB = GitScribeIcon("/icons/github15bright.svg", "/icons/github15dark.svg")
    val MISTRAL = GitScribeIcon("/icons/mistral.svg", null)
    val AMAZON_BEDROCK = GitScribeIcon("/icons/amazonBedrock15.svg", "/icons/amazonBedrock15.svg")
    val DEEP_SEEK = GitScribeIcon("/icons/deepseek15.svg", null)
    val ZHIPU_AI = GitScribeIcon("/icons/zhipuAi15.svg", null)
    val MINIMAX = GitScribeIcon("/icons/minimax15.svg", null)
    val SILICON_FLOW = GitScribeIcon("/icons/siliconFlow15.svg", null)

    object Process {
        val STOP = GitScribeIcon("/icons/stop.svg", "/icons/stop_dark.svg")
    }
}
