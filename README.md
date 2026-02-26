# DocuMind AI - Backend

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Spring Cloud Alibaba](https://img.shields.io/badge/Spring%20Cloud-Alibaba-blue.svg)](https://github.com/alibaba/spring-cloud-alibaba)

**DocuMind AI** 后端服务，基于 **Spring Cloud Alibaba** 微服务架构构建，提供高可用、可扩展的文档处理、知识库管理和 AI 服务调度能力。

## 🚀 项目愿景

打造开箱即用、高性能、可扩展的智能文档处理平台，解决企业在文档管理、知识检索、智能问答等场景下的核心痛点。

## 📂 仓库结构

本项目采用多仓库管理模式，核心仓库如下：

| 仓库名称 | 说明 | 技术栈 |
| :--- | :--- | :--- |
| **[DocuMind-AI-Main](https://github.com/q941299664/DocuMind-AI-Main)** | 主仓库 | 项目文档、部署脚本、公共资源 |
| **[DocuMind-AI-Backend](https://github.com/q941299664/DocuMind-AI-Backend)** | 后端服务 | Java 21, Spring Cloud Alibaba, Nacos, MySQL, Redis |
| **[DocuMind-AI-Frontend](https://github.com/q941299664/DocuMind-AI-Frontend)** | 前端应用 | React 18, TypeScript, Vite, Ant Design, Tailwind CSS |
| **[DocuMind-AI-Python](https://github.com/q941299664/DocuMind-AI-Python)** | AI 核心服务 | Python 3.10+, PyTorch, LangChain, OCR, LLM |

## 🏗️ 技术栈

- **核心框架**: Spring Boot 3.2.x, Spring Cloud 2023.x, Spring Cloud Alibaba 2023.x
- **服务治理**: Nacos 2.x (注册中心 & 配置中心)
- **网关服务**: Spring Cloud Gateway
- **数据库**: MySQL 8.0+, MyBatis Plus 3.5.x, Druid 1.2.x
- **缓存**: Redis 7.0+, Spring Data Redis
- **安全**: Spring Security, JWT
- **文档**: Knife4j, Swagger 3
- **工具**: Lombok, Hutool, Maven 3.8+

## 📦 模块结构

```text
DocuMind-AI-Backend
├── documind-dependencies      # 统一依赖版本管理
├── documind-common            # 通用模块聚合
│   ├── documind-common-core   # 核心工具类、统一响应、异常处理
│   ├── documind-common-web    # Web 全局配置、拦截器
│   ├── documind-common-database # 数据库配置、MyBatis Plus
│   ├── documind-common-redis  # Redis 配置、工具类
│   ├── documind-common-security # 安全认证、JWT 工具
│   └── documind-common-swagger # 接口文档配置
├── documind-infrastructure    # 基础设施服务
│   ├── documind-gateway       # API 网关
│   └── documind-auth          # 认证中心 (OAuth2/SSO)
└── documind-modules           # 业务模块聚合
    ├── documind-system        # 系统基础服务 (用户、角色、菜单)
    ├── documind-document      # 文档管理服务 (上传、解析、存储)
    ├── documind-search        # 搜索服务 (ES/向量检索)
    └── documind-ai            # AI 核心服务 (LLM 调用、RAG 编排)
```

## 🚀 快速启动

### 1. 环境准备

确保已安装并启动：
- Nacos (默认端口 8848)
- MySQL (默认端口 3306, 创建 `documind` 数据库)
- Redis (默认端口 6379)

### 2. 配置修改

修改 Nacos 配置中心地址 (如需)：
`documind-modules/documind-system/documind-system-server/src/main/resources/application.yml`

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
```

### 3. 编译打包

```bash
mvn clean install -DskipTests
```

### 4. 启动服务

建议按以下顺序启动：
1. `documind-gateway` (网关)
2. `documind-auth` (认证中心)
3. `documind-system-server` (系统服务)
4. 其他业务服务...

### 5. 接口文档

启动后访问 Knife4j 文档地址：
`http://localhost:<port>/doc.html`

## 🤝 贡献代码

请遵循 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) 并在提交前运行 `mvn checkstyle:check`。
