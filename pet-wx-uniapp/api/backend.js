import http from '@/common/http.js'

module.exports = {
	// 获取用户信息
	login: function(param) {
		return http.post('/user/login', param);
	},
	// 获取用户手机号
	getPhoneNumber: function(param) {
		return http.post('/user/getPhoneNumber', param)
	},
	// 发布宠物领养
	addPetAdopt: function(param) {
		return http.post('/adopt/add', param)
	},
	// 查询宠物领养列表
	queryPetAdopts: function(param) {
		return http.post('/adopt/queryList', param)
	},
	// 上传图片
	uploadFile: function(param) {
		return http.uploadFile('/file/uploadPic', param)
	},
	// 删除图片
	deletePic: function(param) {
		return http.get('/file/deletePic', param)
	},
	// 查询宠物领养信息
	getPetAdopt: function(param) {
		return http.get("/adopt/getById", param)
	},
	// 发布寻宠信息
	addPetFind: function(param) {
		return http.post('/petFind/add', param)
	},
	// 查询寻宠列表
	queryPetFinds: function(param) {
		return http.post('/petFind/queryList', param)
	},
	// 查询寻宠信息
	getPetFind: function(param) {
		return http.get("/petFind/getById", param)
	},
	// 新增宠物圈
	addPetCircle: function(param) {
		return http.post('/petCircle/add', param)
	},
	// 查询宠物圈列表
	queryPetCircles: function(param) {
		return http.post('/petCircle/queryList', param)
	},
	// 查询宠物圈
	getPetcircle: function(param) {
		return http.get('/petCircle/getById', param)
	},
	// 查询宠物科普列表
	queryPetCyclopedias: function(param) {
		return http.post('/petCyclopedia/queryList', param)
	},
	// 查询宠物科普
	getPetCyclopedia: function(param) {
		return http.get('/petCyclopedia/getById', param)
	},
	// 查询积分
	getCredits: function(param) {
		return http.get('/credits/getCredits', param)
	},
	// 更新积分/签到天数
	updateCredits: function(param) {
		return http.get('/credits/updateCredits', param)
	},
	// 查询用户信息
	getUserInfo: function(param) {
		return http.get('/user/getUserInfo', param)
	},
	// 查询领养/寻宠总数
	queryPetCount: function(param) {
		return http.get('/adopt/queryCount', param)
	},
	// 新增评论
	addComment: function(param) {
		return http.post('/comment/addComment', param)
	},
	// 查询评论列表 By FromId
	queryComments: function(param) {
		return http.get('/comment/queryComments', param)
	},
	// 查询评论总数 By FromId
	queryCommentCount: function(param) {
		return http.get('/comment/queryCount', param)
	},
	// 点赞
	addLove: function(param) {
		return http.get('/comment/addLove', param)
	},
	// 取消点赞
	cutLove: function(param) {
		return http.get('/comment/cutLove', param)
	},
	// 删除评论
	deleteComment: function(param) {
		return http.get('/comment/deleteComment', param)
	},
	// 查询文章列表
	queryArticles: function(param) {
		return http.post('/article/queryArticles', param)
	},
	// 新增聊天消息
	addChat: function(param) {
		return http.post('/chat/add', param)
	},
	// 查询消息数量
	queryChatCount: function(param) {
		return http.get('/chat/queryCount', param)
	},
	// 查询聊天内容
	queryChatContent: function(param) {
		return http.get('/chat/queryChatContent', param)
	},
	// 查询未读消息总数
	queryUnreadTotal: function(param) {
		return http.get('/chat/queryUnreadTotal', param)
	},
	// 添加用户关注
	addUserFollow: function(param) {
		return http.post('/userFollow/addUserFollow', param)
	},
	// 取消关注
	unUserFollow: function(param) {
		return http.post('/userFollow/unUserFollow', param)
	},
	// 查询是否关注
	isFollow: function(param) {
		return http.get('/userFollow/isFollow', param)
	},
	// 查询用户关注列表
	queryUserFollows: function(param) {
		return http.get('/userFollow/queryUserFollows', param)
	},
	// 手机号登录
	phoneLogin: function(param) {
		return http.get('/user/phoneLogin', param)
	},
	// getFlag
	getFlag: function() {
		return http.get('/index/getFlag')
	},
	// 查询题目
	getAnswer: function(param) {
		return http.get('/answer/getAnswerPrize', param)
	},
	// 用户答题 添加记录
	addAnswerRecord: function(param) {
		return http.post('/answer/addAnswerRecord', param)
	},
	// 今日是否已答题
	isFinishToday: function(param) {
		return http.get('/answer/isFinishToday', param)
	},
	// 查询答题记录
	queryAnswerRecords: function(param) {
		return http.post('/answer/queryAnswerRecords', param)
	},
	// 添加收藏
	addCollect: function(param) {
		return http.post('/collect/addCollect', param)
	},
	// 取消收藏
	removeCollect: function(param) {
		return http.post('/collect/removeCollect', param)
	},
	// 是否收藏
	isCollect: function(param) {
		return http.post('/collect/isCollect', param)
	},
	// 查询收藏
	queryCollect: function(param) {
		return http.post('/collect/queryCollect', param)
	},
	// 实名认证
	realname: function(param) {
		return http.post('/user/auth', param)
	},
	// 查询收藏
	report: function(param) {
		return http.post('/report/add', param)
	},
	adoptQueryCount: function(param) {
		return http.get('/adopt/queryCount', param)
	},
	adoptQueryCount: function(param) {
		return http.get('/adopt/queryCount', param)
	},
	addApply: function(param) {
		return http.post('/apply/add', param)
	},
	putApply: function(param,id) {
		return http.put('/apply/'+id, param)
	},
	queryMyApplyList: function(param) {
		return http.post('/apply/queryMyList', param)
	},
	queryApplyList: function(param) {
		return http.post('/apply/queryList', param)
	},
	getApplyById: function(id) {
		return http.get(`/apply/${id}`)
	},
	checkApply: function(id,param) {
		return http.put(`/apply/${id}`,param)
	},
	
	
	



}
