# Aphrodite Spring Boot 3 API Scaffold

[English](README.md) | [简体中文](README-zh.md) | [日本語](README-ja.md) | [한국어](README-ko.md)

Aphrodite は Spring Boot 3をベースに開発されたテンプレートプロジェクトであり、開発者が素早く始め、フレームワークの使用プロセスを深く理解するのを支援することを目的としています。このプロジェクトでは、一般的な開発シーンを網羅する包括的なサンプルコードと設定が提供されており、学習と実践が容易になります。さらに、Aphroditeにはコンテナデプロイメントテンプレートも含まれているため、プロジェクトは現代のクラウド環境で簡単にデプロイされ、管理することができ、開発者がアプリケーションを効率的に構築し、公開するのを支援します。

## 技術スタック

| 技術                                                                                                                                               | 説明                             |
|--------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------|
| [spring-boot-starter](https://spring.io/projects/spring-boot)                                                                                    | Spring Boot の基本的な依存関係          |
| [kotlin-reflect](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/)                                                                   | Kotlin のリフレクションライブラリ           |
| [spring-boot-configuration-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-configuration-processor.html) | Spring Boot の設定プロセッサ           |
| [spring-boot-starter-web](https://spring.io/projects/spring-boot)                                                                                | Web アプリケーション開発に必要な依存関係         |
| [spring-boot-starter-undertow](https://spring.io/projects/spring-boot)                                                                           | Undertow 埋め込み Web サーバー         |
| [spring-boot-starter-validation](https://spring.io/projects/spring-boot)                                                                         | データ検証サポート                      |
| [spring-boot-starter-aop](https://spring.io/projects/spring-boot)                                                                                | AOP（アスペクト指向プログラミング）サポート        |
| [spring-boot-starter-data-redis](https://spring.io/projects/spring-data-redis)                                                                   | Redis データアクセスサポート              |
| [spring-boot-starter-freemarker](https://spring.io/projects/spring-boot)                                                                         | Freemarker テンプレートエンジンサポート      |
| [lombok](https://projectlombok.org/)                                                                                                             | Java オブジェクトの簡素化ツール             |
| [kotlinx-coroutines-core](https://kotlinlang.org/docs/coroutines-overview.html)                                                                  | Kotlin コルーチンコアライブラリ            |
| [kotlinx-coroutines-reactor](https://kotlinlang.org/docs/coroutines-guide.html#reactor)                                                          | Kotlin コルーチンと Reactor の統合      |
| [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using-boot-devtools)                            | 開発ツール、ホットリロード対応                |
| [postgresql](https://jdbc.postgresql.org/)                                                                                                       | PostgreSQL データベースドライバ          |
| [junit-platform-launcher](https://junit.org/junit5/docs/current/user-guide/#overview-platform-launcher)                                          | JUnit プラットフォームランチャー            |
| [spring-boot-starter-test](https://spring.io/projects/spring-boot)                                                                               | Spring Boot テストツール             |
| [kotlin-test-junit5](https://kotlinlang.org/docs/junit-5.html)                                                                                   | Kotlin の JUnit 5 テストサポート       |
| [knife4j-openapi3-jakarta-spring-boot-starter](https://knife4j.github.io/knife4j/)                                                               | OpenAPI 3 対応のドキュメント生成ツール       |
| [redisson-spring-boot-starter](https://github.com/redisson/redisson)                                                                             | Redis 用 Spring Boot スターター      |
| [transmittable-thread-local](https://github.com/alibaba/transmittable-thread-local)                                                              | スレッド間で伝播可能なスレッドローカル変数のサポート     |
| [commons-pool2](https://commons.apache.org/proper/commons-pool/)                                                                                 | Apache Commons コネクションプール       |
| [mybatis-plus-spring-boot3-starter](https://mp.baomidou.com/)                                                                                    | MyBatis 強化版の Spring Boot スターター |
| [mybatis-plus-generator](https://mp.baomidou.com/guide/generator.html)                                                                           | MyBatis-Plus コードジェネレーター        |

## 特徴

- **ユーザー認証と認可**：基本的なユーザーログインと権限付与機能を提供します。
- **分散ロック**：Redisをベースにした分散ロックを実装し、分散環境下でのリソースの安全性を確保します。
- **ミドルウェアサポート**：認証、リクエストログ、CORS処理など、よく使われるミドルウェアを内蔵しています。
- **統一された出力フォーマット**：簡単に使えるAPI Resultの統一出力方式を提供し、APIのレスポンスフォーマットを標準化して、インターフェースの一貫性を向上させます。
- **APIモジュール設計**：モジュール化されたAPI設計をサポートし、拡張と保守が容易です。
- **Swaggerドキュメント統合**：APIドキュメントを自動生成し、フロントエンド開発とテストを容易にします。

## ディレクトリ構造

```
.
├── scripts/
├── database/
├── deploy/
├── docs/
├── src/
│ ├── app/
│ ├── pkg/
├── storage/
└── README.md

```

## ローカルでの実行

```bash
# 1. プロジェクトリポジトリをクローン
git clone https://github.com/lniche/aphrodite-java.git
cd aphrodite-java

# 2. 設定ファイルを編集
edit application.yml

# 3. 依存関係を解決
# JDK21がインストールされていることを確認
./mvn clean package

# 4. データベースを初期化
db.migrate/V1.0.0__initial_schema.sql

# 5. サービスを起動
./mvn spring-boot:run
```

## リポジトリの活動

![Alt](https://repobeats.axiom.co/api/embed/77051c15d804671897e92ca568506d5a088f0dca.svg "Repobeats analytics image")

## ライセンス

このプロジェクトは MIT ライセンスに従っています。

## 感謝の意

すべてのコントリビューターとサポーターに感謝します。皆さんのご支援が私たちにとって非常に重要です！
