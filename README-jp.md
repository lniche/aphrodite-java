# Aphrodite Spring Boot 3 API Scaffold

[English](README.md) | [简体中文](README-zh.md) | [日本語](README-jp.md)

Aphrodite は Spring Boot 3
をベースに開発されたテンプレートプロジェクトであり、開発者が素早く始め、フレームワークの使用プロセスを深く理解するのを支援することを目的としています。このプロジェクトでは、一般的な開発シーンを網羅する包括的なサンプルコードと設定が提供されており、学習と実践が容易になります。さらに、Aphrodite
にはコンテナデプロイメントテンプレートも含まれているため、プロジェクトは現代のクラウド環境で簡単にデプロイされ、管理することができ、開発者がアプリケーションを効率的に構築し、公開するのを支援します。

## 技術スタック

| 技術                                                                                                                                               | 説明                                 |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------|
| [spring-boot-starter](https://spring.io/projects/spring-boot)                                                                                    | Spring Boot の基本的な依存性               |
| [kotlin-reflect](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/)                                                                   | Kotlin のリフレクションライブラリ               |
| [spring-boot-configuration-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-configuration-processor.html) | Spring Boot の設定プロセッサ               |
| [spring-boot-starter-web](https://spring.io/projects/spring-boot)                                                                                | Webアプリケーション開発に必要な依存性               |
| [spring-boot-starter-undertow](https://spring.io/projects/spring-boot)                                                                           | 埋め込み型のWebサーバー（Undertow）            |
| [spring-boot-starter-validation](https://spring.io/projects/spring-boot)                                                                         | データ検証のサポート                         |
| [spring-boot-starter-aop](https://spring.io/projects/spring-boot)                                                                                | AOP（アスペクト指向プログラミング）のサポート           |
| [spring-boot-starter-data-redis](https://spring.io/projects/spring-data-redis)                                                                   | Redisデータアクセスのサポート                  |
| [spring-boot-starter-freemarker](https://spring.io/projects/spring-boot)                                                                         | Freemarker テンプレートエンジンのサポート         |
| [lombok](https://projectlombok.org/)                                                                                                             | Javaオブジェクトの簡略化ツール                  |
| [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using-boot-devtools)                            | 開発時のツール（ホットリロードをサポート）              |
| [postgresql](https://jdbc.postgresql.org/)                                                                                                       | PostgreSQLデータベースドライバー              |
| [spring-boot-starter-test](https://spring.io/projects/spring-boot)                                                                               | Spring Boot のテストツール                |
| [knife4j-openapi3-jakarta-spring-boot-starter](https://knife4j.github.io/knife4j/)                                                               | OpenAPI 3 のサポートされたドキュメント生成ツール      |
| [redisson-spring-boot-starter](https://github.com/redisson/redisson)                                                                             | Redis の Spring Boot スターター          |
| [transmittable-thread-local](https://github.com/alibaba/transmittable-thread-local)                                                              | クロススレッドで伝播するスレッドローカル変数のサポート        |
| [hutool-all](https://github.com/dromara/hutool)                                                                                                  | Java ツールライブラリ（複数の一般的な機能を提供）        |
| [sa-token-spring-boot3-starter](https://github.com/dromara/sa-token)                                                                             | 権限認証フレームワークの Spring Boot スターター     |
| [sa-token-redis-jackson](https://github.com/dromara/sa-token)                                                                                    | Sa-Token の Redis と Jackson の統合サポート |
| [commons-pool2](https://commons.apache.org/proper/commons-pool/)                                                                                 | Apache Commons コネクションプール           |
| [mybatis-plus-spring-boot3-starter](https://mp.baomidou.com/)                                                                                    | MyBatis の強化版 Spring Boot スターター     |
| [mybatis-plus-generator](https://mp.baomidou.com/guide/generator.html)                                                                           | MyBatis-Plus コードジェネレーター            |

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
├── bin/            # 実行可能スクリプト
├── database/       # データベース関連
├── deploy/         # デプロイ関連ファイル
├── docs/           # プロジェクトドキュメント
├── src/            # コアディレクトリ
│ ├── app/          # アプリケーションのコアコード
│ ├── pkg/          # 共通モジュール
├── storage/        # ファイルストレージ
└── README.md       # プロジェクト説明

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
db/init.sql

# 5. サービスを起動
./mvn spring-boot:run
```

## リポジトリの活動

![Alt](https://repobeats.axiom.co/api/embed/77051c15d804671897e92ca568506d5a088f0dca.svg "Repobeats analytics image")

## ライセンス

このプロジェクトは MIT ライセンスに従っています。

## 感謝の意

すべてのコントリビューターとサポーターに感謝します。皆さんのご支援が私たちにとって非常に重要です！
