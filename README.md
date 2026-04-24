# GitScribe

GitScribe 是一个 IntelliJ Platform 插件，用于根据暂存或选中的 VCS 变更，通过大语言模型生成提交信息。

它会集成到 JetBrains IDE 的提交工作流中，读取本次提交涉及的文件或行级 diff，构造提示词，并把生成的提交信息写回提交窗口。

[![构建状态](https://github.com/allanwxl/GitScribe/actions/workflows/build.yml/badge.svg)](https://github.com/allanwxl/GitScribe/actions/workflows/build.yml)

## 功能

- 根据 Git 或 Subversion diff 生成提交信息。
- 支持只使用提交窗口中选中的文件和行。
- 支持配置多个 LLM 客户端，并在生成时切换使用。
- 支持自定义提示词模板，可使用 `{diff}`、`{branch}`、`{locale}`、`{hint}`、历史提交信息等变量。
- 支持按项目配置提示词、输出语言、LLM 客户端和排除规则。
- 支持用 glob 规则排除不想发送给模型的路径。
- 支持在设置页校验 API 客户端配置。

## 支持的 LLM 客户端

- Amazon Bedrock
- Anthropic
- Azure OpenAI
- Claude Code CLI
- Codex CLI
- Gemini Google AI
- Gemini Vertex AI
- GitHub Models
- Hugging Face
- Mistral AI
- Ollama
- OpenAI
- Qianfan

大部分在线模型客户端基于 [langchain4j](https://github.com/langchain4j/langchain4j) 实现。CLI 客户端会调用本机已安装的命令行工具，并使用其输出作为提交信息来源。

## 兼容性

- IntelliJ Platform：`2024.2+`
- Java 工具链：`21`
- 当前测试目标 IDE：IntelliJ IDEA Community
- VCS 集成：Git 和 Subversion

理论上，支持 IntelliJ Platform 提交流程的 JetBrains IDE 都可以使用，例如 IntelliJ IDEA、Android Studio、PyCharm、WebStorm、GoLand、CLion、PhpStorm、DataGrip、RubyMine、Rider 和 DataSpell。

## 安装

GitScribe 当前主要通过插件 ZIP 安装。

1. 从 [GitHub Releases](https://github.com/allanwxl/GitScribe/releases) 下载 ZIP，或在本地构建：

   ```bash
   ./gradlew buildPlugin
   ```

2. 在 JetBrains IDE 中打开 <kbd>Settings</kbd> > <kbd>Plugins</kbd>。
3. 点击齿轮图标，选择 <kbd>Install Plugin from Disk...</kbd>。
4. 选择 `build/distributions/` 目录下的插件 ZIP。
5. 根据提示重启 IDE。

## 使用方式

1. 打开 <kbd>Settings</kbd> > <kbd>Tools</kbd> > <kbd>GitScribe</kbd>。
2. 添加并配置一个 LLM 客户端。
3. 可选：编辑默认提示词，或添加自己的提示词模板。
4. 打开提交窗口。
5. 点击提交信息工具栏中的 GitScribe 按钮，或使用快捷键 <kbd>Ctrl</kbd> + <kbd>Alt</kbd> + <kbd>C</kbd>。

如果使用 Claude Code CLI 或 Codex CLI，请确保对应命令行工具已经安装并能通过 `PATH` 找到，或者在 GitScribe 设置页中手动指定 CLI 路径。

## 提示词变量

自定义提示词可用变量见 [PROMPT_VARIABLES.md](PROMPT_VARIABLES.md)。常用变量包括：

- `{diff}`：选中的 VCS diff 内容。
- `{branch}`：当前分支或公共分支名称。
- `{locale}`：配置的输出语言。
- `{hint}`：提交窗口中的可选提示。
- `{previousCommitMessages}`：最近的提交信息，用于提供上下文。

## 开发

克隆仓库：

```bash
git clone https://github.com/allanwxl/GitScribe.git
cd GitScribe
```

运行测试：

```bash
./gradlew test
```

执行一次干净的验证：

```bash
./gradlew clean test
```

构建插件 ZIP：

```bash
./gradlew buildPlugin
```

在沙箱 IDE 中运行插件：

```bash
./gradlew runIde
```

## 项目信息

- 插件 ID：`com.github.allanwxl.gitscribe`
- Gradle 项目名：`gitscribe-intellij-plugin`
- 仓库地址：[allanwxl/GitScribe](https://github.com/allanwxl/GitScribe)
- 当前插件版本：`2.19.1`

## 贡献

欢迎提交 issue 和 pull request。Bug、功能建议和提示词想法可以在 [GitHub Issues](https://github.com/allanwxl/GitScribe/issues) 中提出。

## 许可证

见 [LICENSE](LICENSE)。
