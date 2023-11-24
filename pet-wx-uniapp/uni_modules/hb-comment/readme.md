## 接口使用，包括点赞回复评论删除逻辑，建议参考示例项目进行二次封装；

## 本插件设计之初就是为小型项目设计，不支持分页，评论只有两级。如需改为多级或者需要支持分页，需要进行相当程度的改造。

直接使用

```html
<hb-comment ref="hbComment" @add="add" @del="del" @like="like" @focusOn="focusOn" :deleteTip="'确认删除？'"
	:cmData="commentData" v-if="commentData"></hb-comment>
```

后端返回数据格式(给到前端后前端整合成树)：

```js
{
	"readNumer": 193,
	"commentList": [{
			"id": 1, // 唯一主键
			"owner": false, // 是否是拥有者，为true则可以删除，管理员全部为true
			"hasLike": false, // 是否点赞
			"likeNum": 2, // 点赞数量
			"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0", // 评论者头像地址
			"nickName": "超长昵称超长...", // 评论者昵称，昵称过长请在后端截断
			"content": "啦啦啦啦", // 评论内容
			"parentId": null, // 所属评论的唯一主键
			"createTime": "2021-07-02 16:32:07" // 创建时间
		},
		{
			"id": 2,
			"owner": false,
			"hasLike": false,
			"likeNum": 2,
			"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797761970/0",
			"nickName": "寂寞无敌",
			"content": "我是评论的评论",
			"parentId": 1,
			"createTime": "2021-07-02 17:05:50"
		},
		{
			"id": 4,
			"owner": true,
			"hasLike": true,
			"likeNum": 1,
			"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797763270/0",
			"nickName": "name111",
			"content": "评论啦啦啦啦啦啦啦啦啦啦",
			"parentId": null,
			"createTime": "2021-07-13 09:37:50"
		},
		{
			"id": 5,
			"owner": false,
			"hasLike": false,
			"likeNum": 0,
			"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0",
			"nickName": "超长昵称超长...",
			"content": "超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论",
			"parentId": null,
			"createTime": "2021-07-13 16:04:35"
		},
		{
			"id": 13,
			"owner": false,
			"hasLike": false,
			"likeNum": 0,
			"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0",
			"nickName": "超长昵称超长...",
			"content": "@寂寞无敌 你怕不是个大聪明",
			"parentId": 1,
			"createTime": "2021-07-14 11:01:23"
		}
	]
}
```

假设后端返回数据为`res`，则`commentData`可以这样得到

```js
this.commentData = {
    "readNumer": res.readNumer,
    "commentSize": res.commentList.length,
    "comment": this.getTree(res.commentList)
}
```

其中`getTree`方法如下

```js
getTree(data) {
    let result = [];
    let map = {};
    data.forEach(item => {
        map[item.id] = item;
    });
    data.forEach(item => {
        let parent = map[item.parentId];
        if (parent) {
            (parent.children || (parent.children = [])).push(item);
        } else {
            result.push(item);
        }
    });
    return result;
}
```

发布弹框借鉴了[@tenniswill](https://ext.dcloud.net.cn/publisher?id=74739)先生[评论内容发布组件](https://ext.dcloud.net.cn/plugin?id=1302)的思路，同时参照其评论区对代码作了优化

## 后端的Python实现核心代码，可以参考

* mysql结构，还有一张userlist表没有列出，请自己发挥

```sql
-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属文章id',
  `comment_user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '评论人id',
  `parent_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '所属评论id，主评论为null',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论内容',
  `like` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '点赞(存储点赞人id数组)',
  `status` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '状态，0-未审核，1-展现，2-审核驳回，3-已删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_read
-- ----------------------------
DROP TABLE IF EXISTS `article_read`;
CREATE TABLE `article_read`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属文章id',
  `read_num` int(10) UNSIGNED NULL DEFAULT 1 COMMENT '阅读数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unionKey`(`article_id`) USING BTREE COMMENT '文章唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '阅读数' ROW_FORMAT = Compact;
```

* python核心代码，其中`CS`为项目中封装的一个通用方法，请自己发挥并替换

```python
# 提交评论
def comment_article(articleId, userId, pId, content):
    if userId is None:
        raise CS.CustomException("请先登录", 500)
    if pId == 'null':
        pId = None
    conn = CS.getCoon()
    cursor = conn.cursor()
    cursor.execute("insert into comment(article_id,comment_user_id,parent_id,content,status,`like`) values(%s,%s,%s,%s,1,'[]')",
                   (articleId, userId, pId, content))
    conn.commit()
    cursor.close()
    conn.close()
    return CS.ResultMap(None)


# 点赞评论
def like_comment(commentId, userId):
    if userId is None:
        raise CS.CustomException("请先登录", 500)
    conn = CS.getCoon()
    cursor = conn.cursor()
    cursor.execute("select `like` from comment where id = %s", (commentId))
    currentLike_json = cursor.fetchall()[0][0]
    currentLike = json.loads(currentLike_json)
    if userId in currentLike:
        currentLike.remove(userId)
    else:
        currentLike.append(userId)
    currentLike_json = json.dumps(currentLike)
    cursor.execute("update comment set `like` = %s where id = %s", (currentLike_json, commentId))
    conn.commit()
    cursor.close()
    conn.close()
    return CS.ResultMap(None)


# 删除评论
def delete_comment(commentId, userId):
    if userId is None:
        raise CS.CustomException("请先登录", 500)
    conn = CS.getCoon()
    cursor = conn.cursor()
    # userId = 1为超级管理员
    if userId != 1:
        cursor.execute("select comment_user_id from comment where id = %s and status = 1",(commentId))
        commentUserIdRst = cursor.fetchall()
        if str(commentUserIdRst) == '()':
            raise CS.CustomException("评论不存在或已删除", 500)
        if userId != commentUserIdRst[0][0]:
            cursor.close()
            conn.close()
            raise CS.CustomException("无删除权限", 500)
    cursor.execute("update comment set status=3 where id = %s or parent_id = %s", (commentId, commentId))
    conn.commit()
    cursor.close()
    conn.close()
    return CS.ResultMap(None)


# 获取评论列表
def get_article_comment(articleId, userId):
    conn = CS.getCoon()
    cursor = conn.cursor()
    cursor.execute(
        '''select 
            c.id,
            c.comment_user_id,
            u.avatar_url,
            u.nick_name,
            c.content,
            c.like,
            c.parent_id,
            c.create_time
        from comment c
            left join userlist u on c.comment_user_id = u.id
        where article_id = %s and status = 1''', (articleId))
    commentList = cursor.fetchall()
    cursor.execute("insert into article_read(article_id) values(%s) on DUPLICATE KEY UPDATE read_num=read_num+1", (articleId))
    conn.commit()
    cursor.execute("select read_num from article_read where article_id = %s", (articleId))
    readNum = cursor.fetchall()[0][0]
    cursor.close()
    conn.close()
    commentResult = []
    for comment in commentList:
        like = json.loads(comment[5])
        commentEach = {
            "id": comment[0],
            "owner": False,
            "hasLike": False,
            "likeNum": len(like),
            "avatarUrl": comment[2],
            "nickName": "用户" if comment[3] == None else comment[3],
            "content": comment[4],
            "parentId": comment[6],
            "createTime": time.strftime("%Y-%m-%d %H:%M:%S", datetime.datetime.timetuple(comment[7]))
        }
        if len(commentEach['nickName']) > 7:
            commentEach['nickName'] = commentEach['nickName'][0:6] + '...'
        if userId is not None:
			# userId = 1为超级管理员
            if userId == comment[1] or userId == 1:
                commentEach["owner"] = True
            if userId in like:
                commentEach["hasLike"] = True
        commentResult.append(commentEach)
    result = {
        "readNumer": readNum,
        "commentList": commentResult
    }
    return CS.ResultMap(result)
```
