## Twitter clone ##
### 基本機能
> - 会員登録できること
> - ログイン/ログアウトできること
> - 140文字以下でコメントを書けること（140文字でなくても文字数の制限をかける）
> - フォローが出来ること
> - 会員情報を編集できること
> - 最低限のセキュリティは担保する（SQLインジェクション等）
> 
> ### その他
> - イチオシの機能を一つ実装する  
> （技術的に難易度が高い機能、サービスのアピールポイントになる機能等)  
> 
> ### 言語・フレームワーク等
> Scala 2.11.x  
> Play2.5.x  
> 
> ### 動作環境
> AWS EC2 1インスタンス => 2インスタンス ELBで負荷分散
> 
> ### 納期
> - 三週間（アプリチームはべつ）
> - 発表会を8月22日か23日に行う。
> 
> ソースレビューはチーム内で行う  
> 発表会ではプレゼン資料を用意しデモも行う

### TODO ###
基本要件を再優先
- [x] slickでmysqlの連携をする｡
- [ ] play2-authでログイン・ログアウトを実装する｡
- [ ] データベースの設計

### 技術選定
- データベースはmysql,O/Rマッパーはslickを用いる  
( elasticserchは適切な部分があれば用いる)
- フロント部分の使用技術は未定(ペースに余裕があれば､react?)
