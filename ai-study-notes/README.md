# AI 学习笔记助手

Spring Boot + Thymeleaf + H2 的笔记管理工具，专为学习 Spring Boot 全栈开发而设计。

## 项目结构（你现在已经掌握的）

```
ai-study-notes/
├── pom.xml                          # Maven 依赖配置
└── src/main/
    ├── java/com/example/notes/
    │   ├── NotesApplication.java     # 启动类（含 @SpringBootApplication）
    │   ├── entity/
    │   │   └── Note.java          # 实体类（JPA 注解，对应数据库表）
    │   ├── repository/
    │   │   └── NoteRepository.java # 数据访问层（继承 JpaRepository）
    │   ├── service/
    │   │   └── NoteService.java    # 业务逻辑层
    │   └── controller/
    │       └── NoteController.java # 控制器（处理 HTTP 请求）
    └── resources/
        ├── application.yml           # 配置文件（H2 内存数据库）
        ├── static/css/style.css     # 样式
        └── templates/
            ├── index.html           # 笔记列表页
            └── form.html           # 新建/编辑表单页
```

## 快速启动

### 方式一：IntelliJ IDEA（推荐）

1. 打开 IDEA → `File` → `Open` → 选择 `ai-study-notes` 文件夹
2. 等待 Maven 自动导入依赖（右下角有进度条）
3. 找到 `NotesApplication.java` → 右键 → `Run 'NotesApplication'`
4. 浏览器打开 `http://localhost:8080`

### 方式二：命令行

```bash
cd C:\Users\ZX\WorkBuddy\20260510121117\ai-study-notes
"D:\apache-maven-3.9.9\apache-maven-3.9.9\bin\mvn.cmd" spring-boot:run
```

## H2 数据库控制台

应用启动后访问：`http://localhost:8080/h2-console`

```
JDBC URL: jdbc:h2:mem:study_notes;DB_CLOSE_DELAY=-1;MODE=MySQL
用户名: sa
密码: （留空）
```

## 下一步扩展方向

- [ ] 接入 AI API（DeepSeek/MiMo）自动生成笔记内容
- [ ] 笔记标签系统（多对多关系）
- [ ] Markdown 渲染
- [ ] 用户登录（Spring Security）
- [ ] 部署到云服务器
