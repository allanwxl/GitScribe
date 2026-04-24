package com.github.allanwxl.gitscribe.intellij.plugin.settings.prompts

enum class DefaultPrompts(val prompt: Prompt) {

    // Generate UUIDs for game objects in Mine.py and call the function in start_game().
    BASIC(
        Prompt(
            "基础Commit",
            "基础Commit",
            """
            请根据我提供的 git diff --staged 代码变更内容，使用 {locale} 语言生成标准、简洁、信息完整的 Git 提交信息。

            输出必须为纯文本，不使用代码块、不添加任何解释、前缀或额外说明。

            提交信息格式如下：

            <type>(<scope>): <subject>

            <body>

            <footer>

            格式规则：
            1. `type` 为必填项，且只能使用以下值之一：
            feat: 新增功能或功能增强
            fix: 修复缺陷或问题
            chore: 构建、依赖、工具或其他非业务代码调整
            refactor: 代码重构（不改变现有功能）
            ci: CI/CD 流程调整
            test: 测试代码新增或修改
            docs: 文档更新
            revert: 代码回滚

            2. `scope` 为可选项，填写本次变更影响的模块、组件或文件名。
            如果无法明确归类，可省略；省略时输出格式为：
            <type>: <subject>

            3. `subject` 为必填项：
            - 50 个字符以内
            - 使用简洁明确的语句概括本次改动核心
            - 不要以句号结尾

            4. `body` 为可选项：
            - 用于补充说明变更细节、原因、影响范围或实现方式
            - 可写一行或多行
            - 如果没有必要说明，则省略整个 body
            - 不要输出“无详细说明”等占位内容

            5. `footer` 为可选项：
            - 用于填写 Issue、任务编号、关闭信息或其他关联说明
            - 如果没有相关内容，则省略整个 footer
            - 不要输出“无相关issue”等占位内容

            输出要求：
            - 严格使用 {locale} 语言
            - 只输出最终提交信息正文
            - 不要输出标题、说明、注释或示例
            - 如果 `body` 和 `footer` 都省略，则只输出一行标题
            - 如果仅有 `body`，则标题与 `body` 之间保留一个空行
            - 如果仅有 `footer`，则标题与 `footer` 之间保留一个空行
            - 如果 `body` 和 `footer` 都有，则它们之间保留一个空行

            以下是变更内容：
            {diff}
            """.trimIndent(),
            false
        )
    ),

    // feat: generate unique UUIDs for game objects on Mine game start
    CONVENTIONAL(
        Prompt(
            "Conventional",
            "Prompt for commit message in the conventional commit convention.",
            "Write a commit message in the conventional commit convention. I'll send you an output " +
                    "of 'git diff --staged' command, and you convert it into a commit message. " +
                    "Lines must not be longer than 74 characters. Use {locale} language to answer. " +
                    "End commit title with issue number if you can get it from the branch name: " +
                    "{branch} in parenthesis.\n" +
                    "{Use this hint to improve the commit message: \$hint}\n" +
                    "Previous commit messages:\n" +
                    "{previousCommitMessages}\n" +
                    "{diff}",
            false
        )
    ),

    // author: ljgonzalez1
    // source: https://github.com/allanwxl/GitScribe/discussions/18#discussioncomment-10718381
    // ✨ feat(conditions): add HpComparisonType enum and ICondition interface for unit comparison logic
    EMOJI(
        Prompt(
            "GitMoji",
            "Prompt for generating commit messages with GitMoji.",
            "Write a concise commit message from 'git diff --staged' output in the format " +
                    "`[EMOJI] [TYPE](file/topic): [description in {locale}]`. Use GitMoji emojis (e.g., ✨ → feat), " +
                    "present tense, active voice, max 120 characters per line, no code blocks.\n" +
                    "Previous commit messages:\n" +
                    "{previousCommitMessages}\n" +
                    "---\n" +
                    "{diff}",
            false
        )
    );

    companion object {
        fun toPromptsMap(): MutableMap<String, Prompt> {
            return entries.associateBy({ it.name.lowercase() }, { it.prompt }).toMutableMap()
        }
    }
}
