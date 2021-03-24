# Services by Lifecycle

問題設定
https://scrapbox.io/kawasima/%E3%82%BD%E3%83%95%E3%83%88%E3%82%A6%E3%82%A7%E3%82%A2%E8%A8%AD%E8%A8%88%E7%B7%B4%E7%BF%92%E5%B8%B3

Michael Nygardの[Service by lifecycle](https://www.michaelnygard.com/blog/2018/01/services-by-lifecycle/)にしたがって、設計してみる教材です。
おおまかなクラス設計とレイヤリングは、[Get Your Hands Dirty on Clean Architecture](https://github.com/thombergs/buckpal)にしたがいます。


## Get started

### Elasticsearch

9200ポートで起動します。

```shell
docker pull elasticsearch
```

```shell
docker run --name elasticsearch -dit -p target=9200,published=9200 -p target=9300,published=9300 -e "discovery.type=single-node" elasticsearch
```

### Spring Application

net.unit8.examples.Applicationを起動します。

### ブラウザからの動作確認

http://localhost:8080/ にアクセスすると、簡易メニューが表示されます。

### テスト用の案件(プロジェクト)の作成

src/test/java/integration/ProjectCreator を実行すると、Selenideで自動的に3件のProject登録します。