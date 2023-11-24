<template>
	<view class="hb-comment">
		<!-- 阅读数-start -->
		<view>
			<img style="width: 14px; height: 14px;"
				src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA0tJREFUWEftVk2IHUUQ/qp311NOksRT8JBDkARCfrxEDyLK+pe/DQkmJpBlZ7ofDwQ15iRocjYSSWAzU/OWd4hCMJiQH43xBz2YXCRivIgHQQ8SRPGUU/btfNLPnuU5O7OZlWz2kobh8aq/qvq6qrq6BEu8ZIn94wGBBUWg3W6v6vV62wFsILlGRNYA6AH4AcCPJH8mebPT6dxomtpGBJxz4wD2kny2oWHN81ybEJmXQKvVWpfn+VsAXi45vgPgj/D5rUfC91AJ54m83el0PLZy1RKw1u4GkAB4OGj+LSJXRORMkiSXq6xFUfSUiHi93SKyImB+BeBU9fMqnUoCcRy/IiIfFAoicp7kYVX9pUkKrLWrReRdkjsLvNfPsuxYWX8OAWvtOwCODACPqOrRJo7LmApbe1T17CDuPwSccy2SpwZYj2VZdr7K+fj4+IqRkZFVIjJD8ndV/asKdzcSswTiON4iIlcBLPOGROTFNE0/rTHqQ3motHdWVffU1YYx5utizxjzXJIk3te/nbDdbi/r9XpesCWAasNureV86VDVurqKRUSD7k8zMzPPT01N/dYHW2uPA3gtnDxL09TWnPwjX+F+j+Q1EfkCwC0ALwHYGnTeU9U3q/Sdc0oyDnvnVHWXOOdeIPlJEN4E8ExVPq21ywH8GXCXVHXboJM4jr8VkSe8bHp6emW32y2ws7Bg40sA68NhD3gCXZIHA2q07r4659aT9C3XL3+vi3D2BaVi26Sq31dFIY7jgyLSDXs3xFp7rci9MebJJEn8/zlrMQj00+ic20/ydAjJhTRNd9Tk/56ngOT2ogg/BjAWHB9X1TfuQxG+r6qv9wlMTEw8OjQ0dAXAY8HxYl/D68PDw6OTk5O3Z+9sq9UazfP8s+Lki9iIbpMczbLsej/tg6F2zr1K8kQhI3n/WnHhdDEfI5L7syz7cPDQlW2zHIl78RwDqCzu2oEkiqKnjTFfDbD9vwOJb9vHsiw7XHWz5h3JoijaZIzxz/PjJeVGIxnJb4wxJ9M0PVflfE4R1oGcc2Mk9wHYVYcpyS8BuKiqnbvhG03FhRHn3GaSG0muM8as9b8AjB/HRcR/35G8oKr+hWy0FkSgkcUFgh4QWPII/ANy0cUvD9WbkgAAAABJRU5ErkJggg==" />
			<span class="top-read">{{commentData.readNumer}}</span>
		</view>
		<!-- 阅读数-end -->
		<!-- 阅读数下边那条线-start -->
		<view class="seg_line_box">
			<view class="seg_line"></view>
			<view class="seg_dot"></view>
			<view class="seg_line"></view>
		</view>
		<!-- 阅读数下边那条线-end -->
		<!-- 评论主体-start -->
		<view class="comment-list" v-if="commentData.comment.length != 0">
			<!-- 评论主体-顶部数量及发表评论按钮-start -->
			<view class="comment-num">
				<view>共 {{commentData.commentSize}} 条评论</view>
				<view class="add-btn">
					<button type="primary" size="mini" @click="commentInput">发表评论</button>
				</view>
			</view>
			<!-- 评论主体-顶部数量及发表评论按钮-end -->
			<!-- 评论列表-start -->
			<view class="comment-box" v-for="(item, index) in commentData.comment">
				<view class="comment-box-item">
					<view>
						<image :src="item.avatarUrl || emptyAvatar" mode="aspectFill" class="avatar"></image>
					</view>
					<view class="comment-main">
						<!-- 父评论体-start -->
						<view class="comment-main-top">
							<view class="nick-name-box">
								<view class="comLogo com1" v-if="index == 0">沙发</view>
								<view class="comLogo com2" v-if="index == 1">板凳</view>
								<view class="comLogo com3" v-if="index == 2">地板</view>
								<view class="comLogo com4" v-if="index > 2">{{index + 1}}楼</view>
								<view class="nick-name">{{item.nickName}}</view>
							</view>
							<view class="zan-box" @click="like(item.id)">
								<span :class="item.hasLike ? 'isLike' : 'notLike'">{{item.likeNum == 0 ? '抢首赞' : item.likeNum}}</span>
								<img style="width: 14px; height: 14px;" v-if="!item.hasLike"
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAApVJREFUWEfFlz9oFEEUxr93SWEhFkIaBUljo4WgATEaiI0GwVLBQg2EzLd3hSBqLI2lRgWLY+ftSSQgggoWNv5pImLUwjQBg1goqCCIIBqwueSeTLg7Ln9Mcmdub7vdnZnvN9+8fe+toMWXtFgfDQGMjo52zM7OHgMwIyLTg4ODk41upG4A7/0OEbkPYGdFVEQuOOeuNQJRN4CqBvGw+3dm9kZEBsrCPSRf1gvRCIAFETPrjqLodZIkY2Z2SkQmnHMHUgMgOQ8fx3FvJpMZB/CDZEfqACEgi8XidwA/SW5uKkCN2AzJTYscmCTZ1VQA7/2AiNwSkefOuYNBLEmSnJnlATwgebypAKr6CMBRANdJng9i3vu8iORE5Mrc3NyTlQDM7HMul/tYO2bNX0GhUDhRKpXumtlvAPuiKJouOzBuZr117HyBU6sCqOpeAH0AhssiwyQvVwTjOO5va2s7vRaAGtCrJC+GOVUAVb1UTjDVDLd4URHJOuf8WsSWG5MkSbeZTYR3lc+4CpAkyUpWht3fI/m+UfEwL5/Pb2xvb59ZFkBV5zNchazsSBBeYPn/ABQKhZ5SqfQCwBTJXYuPoOkAqnoGwE0Ad0ieTB3Ae39bRPoBDJEcSR1AVd8C2CMifc65p6kCmJkkSfIHwAYAW0h+SxVAVXcDmDSzL1EUbasEc20eaGoQVuoIgMckj6QOoKo3AJwFMEJyqBUAIegOAegnOdYKgK8AtmYyma7aLjqVGPDebxeRDwB+AeggWUzVAVV1ABTAK5L7l+0HVPUTgM7Q7VQGhPJZe99oHagpw4dJPvsXQPixONeoyErzRGTKzB7W9hFLjiA8iOO4M7iw3hDZbLbq6pIeY73F6l1v1Zas3gXrHf8XhhNvMGSmtPYAAAAASUVORK5CYII=" />
								<img style="width: 14px; height: 14px;" v-else
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAfVJREFUWEfFl79LHEEUx7/vclr4g51TLFQQW9FNDFhFBDvjtcF0gXSp5eYuWJlUAW9NIP9BIFUEOxFtTKF2FrezKFgkkEBS3h4SAtHbJ6vu5W5d1F3cm20WZt+b7+e9mTfzlqD5Ic36SATw+OPJQP2ft+ABJwQ+VFIcJA0kEYBpVfcAehKIMqHoFISVBCI2wOS76mi9g76HxTLwZiqybzcuRGwAc7WWB/NGWIhBu440ZlIHmFitLRPzm+tC9EtJY1gjAP9VMtelEQC2kuJR6gCm5X4BsBAWImDdluJZqgBjH/4MZr3TYzB6IoRWmHnzJgDK0g+1KL4128SqgnD9x432yn5NSfE88L0VYPx9rS/jYR6EJTCPJxQNu60oKV77gw2Ai/Ly1/ZKRElx8c20XL4n0ZZpgvkbAKbl7gCYDax0ALREmjJAo2SbM9A+AKLPqmC8aNkD4bVOMwPEKNlFUdYGkAE9rUhjSxvA2YOOoaPF7t+6AH4qKUauHURt3AObSoq8NgAGyo4UJW0ABH5py9wnbQAAppq76HYfRLXOXmPg4BWdasoA7yuZm47sB0zL9Vvt0TQvI/bqc06pfzsS4KHlWgwUUgKwmWjdKRhvI1q5/0P+T8dZ9jILTjH31X9PlKuNKzppXxDMFeV/a0eUVPSuftoBzgHKR/ohZwAugwAAAABJRU5ErkJggg==" />
							</view>
						</view>
						<view class="comment-main-content">
							{{item.content.length > 60 ? item.content.slice(0, 59) : item.content}}
							<span v-if="item.content.length > 60">
								{{item.hasShowMore ? item.content.slice(59) : '...'}}
								<span class="foot-btn" @click="showMore(item.id)">
									{{item.hasShowMore ? '收起' : '展开'}}
								</span>
							</span>
						</view>
						<view class="comment-main-foot">
							<view class="foot-time">{{item.createTime}}</view>
							<view class="foot-btn" @click="reply(item.nickName,item.nickName,item.id)">回复</view>
							<view class="foot-btn" v-if="item.owner" @click="confirmDelete(item.id)">删除</view>
						</view>
						<!-- 父评论体-end -->
						<!-- 子评论列表-start -->
						<view class="comment-sub-box">
							<view class="comment-sub-item" v-for="each in item.children">
								<view>
									<image :src="each.avatarUrl || emptyAvatar" mode="aspectFill" class="avatar">
									</image>
								</view>
								<view class="comment-main">
									<view class="sub-comment-main-top">
										<view class="nick-name">{{each.nickName}}</view>
										<view class="zan-box" @click="like(each.id)">
											<span :class="each.hasLike ? 'isLike' : 'notLike'">{{each.likeNum == 0 ? '抢首赞' : each.likeNum}}</span>
											<img style="width: 14px; height: 14px;" v-if="!each.hasLike"
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAApVJREFUWEfFlz9oFEEUxr93SWEhFkIaBUljo4WgATEaiI0GwVLBQg2EzLd3hSBqLI2lRgWLY+ftSSQgggoWNv5pImLUwjQBg1goqCCIIBqwueSeTLg7Ln9Mcmdub7vdnZnvN9+8fe+toMWXtFgfDQGMjo52zM7OHgMwIyLTg4ODk41upG4A7/0OEbkPYGdFVEQuOOeuNQJRN4CqBvGw+3dm9kZEBsrCPSRf1gvRCIAFETPrjqLodZIkY2Z2SkQmnHMHUgMgOQ8fx3FvJpMZB/CDZEfqACEgi8XidwA/SW5uKkCN2AzJTYscmCTZ1VQA7/2AiNwSkefOuYNBLEmSnJnlATwgebypAKr6CMBRANdJng9i3vu8iORE5Mrc3NyTlQDM7HMul/tYO2bNX0GhUDhRKpXumtlvAPuiKJouOzBuZr117HyBU6sCqOpeAH0AhssiwyQvVwTjOO5va2s7vRaAGtCrJC+GOVUAVb1UTjDVDLd4URHJOuf8WsSWG5MkSbeZTYR3lc+4CpAkyUpWht3fI/m+UfEwL5/Pb2xvb59ZFkBV5zNchazsSBBeYPn/ABQKhZ5SqfQCwBTJXYuPoOkAqnoGwE0Ad0ieTB3Ae39bRPoBDJEcSR1AVd8C2CMifc65p6kCmJkkSfIHwAYAW0h+SxVAVXcDmDSzL1EUbasEc20eaGoQVuoIgMckj6QOoKo3AJwFMEJyqBUAIegOAegnOdYKgK8AtmYyma7aLjqVGPDebxeRDwB+AeggWUzVAVV1ABTAK5L7l+0HVPUTgM7Q7VQGhPJZe99oHagpw4dJPvsXQPixONeoyErzRGTKzB7W9hFLjiA8iOO4M7iw3hDZbLbq6pIeY73F6l1v1Zas3gXrHf8XhhNvMGSmtPYAAAAASUVORK5CYII=" />
											<img style="width: 14px; height: 14px;" v-else
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAfVJREFUWEfFl79LHEEUx7/vclr4g51TLFQQW9FNDFhFBDvjtcF0gXSp5eYuWJlUAW9NIP9BIFUEOxFtTKF2FrezKFgkkEBS3h4SAtHbJ6vu5W5d1F3cm20WZt+b7+e9mTfzlqD5Ic36SATw+OPJQP2ft+ABJwQ+VFIcJA0kEYBpVfcAehKIMqHoFISVBCI2wOS76mi9g76HxTLwZiqybzcuRGwAc7WWB/NGWIhBu440ZlIHmFitLRPzm+tC9EtJY1gjAP9VMtelEQC2kuJR6gCm5X4BsBAWImDdluJZqgBjH/4MZr3TYzB6IoRWmHnzJgDK0g+1KL4128SqgnD9x432yn5NSfE88L0VYPx9rS/jYR6EJTCPJxQNu60oKV77gw2Ai/Ly1/ZKRElx8c20XL4n0ZZpgvkbAKbl7gCYDax0ALREmjJAo2SbM9A+AKLPqmC8aNkD4bVOMwPEKNlFUdYGkAE9rUhjSxvA2YOOoaPF7t+6AH4qKUauHURt3AObSoq8NgAGyo4UJW0ABH5py9wnbQAAppq76HYfRLXOXmPg4BWdasoA7yuZm47sB0zL9Vvt0TQvI/bqc06pfzsS4KHlWgwUUgKwmWjdKRhvI1q5/0P+T8dZ9jILTjH31X9PlKuNKzppXxDMFeV/a0eUVPSuftoBzgHKR/ohZwAugwAAAABJRU5ErkJggg==" />
										</view>
									</view>
									<view class="comment-main-content">
										{{each.content.length > 60 ? each.content.slice(0, 59) : each.content}}
										<span v-if="each.content.length > 60">
											{{each.hasShowMore ? each.content.slice(59) : '...'}}
											<span class="foot-btn" @click="showMore(each.id)">
												{{each.hasShowMore ? '收起' : '展开'}}
											</span>
										</span>
									</view>
									<view class="comment-main-foot">
										<view class="foot-time">{{each.createTime}}</view>
										<view class="foot-btn" @click="reply(item.nickName,each.nickName,item.id)">
											回复</view>
										<view class="foot-btn" v-if="each.owner" @click="confirmDelete(each.id)">删除
										</view>
									</view>
								</view>
							</view>
						</view>
						<!-- 子评论列表-end -->
					</view>
				</view>
			</view>
			<!-- 评论列表-end -->
		</view>
		<!-- 评论主体-end -->
		<!-- 无评论-start -->
		<view class="comment-none" v-else>
			暂无评论，<span @click="commentInput" style="color: #007AFF;">抢沙发</span>
		</view>
		<!-- 无评论-end -->
		<!-- 新增评论-start -->
		<view class="comment-submit-box" v-if="submit" @click="closeInput">
			<!-- 下边的click.stop.prevent用于让上边的click不传下去，以防点到下边的空白处触发closeInput方法 -->
			<view class="comment-add" @click.stop.prevent="stopPrevent" :style="'bottom:' + KeyboardHeight + 'px'">
				<view class="comment-submit">
					<view class="btn-click cancel" @click="closeInput">取消</view>
					<view>
						<view class="replayTag" v-show="showTag">
							<view>回复在 {{pUser}} 的评论下</view>
							<view @click="tagClose" class="replyTagClose">×</view>
						</view>
					</view>
					<view>
						<view class="btn-click" @click="add">发布</view>
					</view>
				</view>
				<textarea class="textarea" v-model="commentReq.content" :placeholder="placeholder" :adjust-position="false" :show-confirm-bar="false"
					@blur="blur" @focus="focusOn" :focus="focus" maxlength="800"></textarea>
			</view>
		</view>
		<!-- 新增评论-end -->
	</view>
</template>

<script>
	export default {
		name: 'hb-comment',
		props: {
			cmData: {
				type: Object,
				default: () => {
					return null;
				}
			},
			deleteTip: {
				type: String,
				default: () => {
					return '操作不可逆，如果评论下有子评论，也将被一并删除，确认？';
				}
			},
		},
		watch: {
			cmData: {
				handler: function(newVal, oldVal) {
					this.init(newVal);
				},
				immediate: true
			}
		},
		data() {
			return {
				"emptyAvatar": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAABF5JREFUWEfFl11oHFUUx//nbmKwxdJ9qFL7YPEhRJC2gljjF60WG0WsoNkWfSnZ7Jy7FVPF0AoKpmBFqyIG3Jk7G1iIQXHbBz+KbbGtRfBbMe2LseKjiVDoiqIhwZ0jV3fb3cnM7kQCuU+7M+ec/+9+nHPPEJZ50DLrY1EApVJp9fz8/BYRuZ2INgDYWJvAWRE5R0RnZmZmPh4ZGZlPOrFEAMVi8e4gCPYSUZ+IXGGFgiCYIaJpKyQi1yql1orIFgAXARxRSvm5XO67diBtAYwxRQCDAE4RUUkpdWxwcNCKLBiu665TSj0kIpqIbgTgMzO3gmgJYIz5CMB9AIaZ+bXGQMVi8RoRuZeI/lZKHc1ms3/U34+Pj6+cnZ3dC+AggGPMfH8cRCyAMUZqThlmPlwPUCgU0qlUyq7Kww1BrbjHzPsahYwx/QDK9hkzR2pFPjTGnAWwIexkjOkRkRIR3Rozo5Miskdr/VMIxE7mHDPXD+2l1wsA6nseBMHmfD7/dSjQOwB2tTlYC/bddd1blFJfARhj5lyjfxNA7bSfitpz3/d3iYgFaDuUUjeHM8AY8zSAV5VS9+RyudP1IE0Axpj3Aaxk5m1hFc/zPiWiO9uq/2dwiJn3h22NMScB/MnMOxYAjI6Orurq6rpgU0hrXYpw/hFAd0KAD5n5wbCt7/t7ROT1ubm5NUNDQ7/b95dWwHXd7Uqp452dnVcPDAxciACwDlclAbAFynGcdRGr2EtEnwdB0JfP5080ARhjDhLRbY7jbI0SMcZUAKxOAgDgN2ZOx8SxGfEiMz/bBOD7/lgQBCu01o/GOH4PYFNCgElmvinK1vf9X2xxchzHVtfLW2CMOQpgipmHYwBeBtBUaOJgRKSgtX48Js63AH5l5geaADzPe1cpddFxnHyM42YAXyZYAXt+epn557iVFJHzWuudYYDnlFJ9juPcESfi+35JRHa3gdjPzIfibIwxNtPe0Fq/EAZ4hIhcZl4T5+y67nql1CcA1kfZENFnrSZQ6ycqItKvtT4SBthIRJMdHR092WzW5nzk8H1/WEReiQHY4TjOB3G+nuf9qyEim7TW9r65fAjL5fKVlUrlPICXmPnNFquwWym1oFBZ+yAItubz+TMtAJ4gon3pdLo7k8nMNgHYP7ZeE5EWkbuYeaYx0NjY2HXValUDeApAV4zIJIADzPxe+H2hULg+lUp9U6sBl3qLprugXC6nKpXKF0R02nGcZ2wQ3/e3ichOEckQ0aoEWQDbsgF4a3p6eqLeH3qeN0FE3el0ujeTyVTrcaKuY1uIjIg8CaCfiLYnEW1hY4WPi8gEgMeY+e1G27iGxHYxtptZynGYmTPhgK1asqWEiBRfcAjDdMaYpYCIFW8LUMuM54nIsb3/YvbDXskiYtuzA6382n4X1CDWAnCSgNSFa98ETakcWbwWMytjzAoAPUEQ3JBKpXrs75r/VLVanVJK/VC7Uf9KGjfRCiQN9n/slh3gHz9i4jC+FVL5AAAAAElFTkSuQmCC",
				"commentData": null,
				"placeholder": "请输入评论",
				"commentReq": {
					"pId": null, // 评论父id
					"content": null // 评论内容
				},
				"pUser": null, // 标签-回复人
				"showTag": false, // 标签展示与否
				"focus": false, // 输入框自动聚焦
				"submit": false, // 弹出评论
				"KeyboardHeight": 0 // 键盘高度
			};
		},
		mounted: function() {
			uni.onKeyboardHeightChange(res => {
				this.KeyboardHeight = res.height;
			})
		},
		methods: {
			// 初始化评论
			init(cmData) {
				// for (var i in cmData.comment) {
				// 	cmData.comment[i].hasShowMore = false;
				// 	for (var j in cmData.comment[i].children) {
				// 		cmData.comment[i].children[j].hasShowMore = false;
				// 	}
				// }
				this.commentData = cmData;
			},
			// 没用的方法，但不要删
			stopPrevent() {},
			// 回复评论
			reply(pUser, reUser, pId) {
				this.pUser = pUser;
				this.commentReq.pId = pId;
				if (reUser) {
					this.commentReq.content = '@' + reUser + ' ';
				} else {
					this.commentReq.content = '';
				}
				this.showTag = true;
				this.commentInput();
			},
			// 删除评论前确认
			confirmDelete(commentId) {
				var that = this;
				uni.showModal({
					title: '警告',
					content: that.deleteTip,
					confirmText: '确认删除',
					success: function(res) {
						if (res.confirm) {
							that.$emit('del', commentId);
						}
					}
				});
			},
			// 新增评论
			add() {
				if (this.commentReq.content == null || this.commentReq.content.length < 2) {
					uni.showToast({
						title: '评论内容过短',
						duration: 2000
					});
					return
				}
				this.$emit('add', this.commentReq);
			},
			// 点赞评论
			like(commentId) {
				this.$emit('like', commentId);
			},
			// 新增完成
			addComplete() {
				this.commentReq.content = null;
				this.tagClose();
				this.closeInput();
			},
			// 点赞完成-本地修改点赞结果
			likeComplete(commentId) {
				for (var i in this.commentData.comment) {
					if (this.commentData.comment[i].id == commentId) {
						this.commentData.comment[i].hasLike ? this.commentData.comment[i].likeNum-- : this.commentData
							.comment[i].likeNum++;
						this.commentData.comment[i].hasLike = !this.commentData.comment[i].hasLike;
						return
					}
					for (var j in this.commentData.comment[i].children) {
						if (this.commentData.comment[i].children[j].id == commentId) {
							this.commentData.comment[i].children[j].hasLike ? this.commentData.comment[i].children[j]
								.likeNum-- : this.commentData.comment[i].children[j].likeNum++;
							this.commentData.comment[i].children[j].hasLike = !this.commentData.comment[i].children[j]
								.hasLike;
							return
						}
					}
				}
			},
			// 删除完成-本地删除评论
			deleteComplete(commentId) {
				for (var i in this.commentData.comment) {
					for (var j in this.commentData.comment[i].children) {
						if (this.commentData.comment[i].children[j].id == commentId) {
							this.commentData.comment[i].children.splice(Number(j), 1);
							return
						}
					}
					if (this.commentData.comment[i].id == commentId) {
						this.commentData.comment.splice(Number(i), 1);
						return
					}
				}
			},
			// 展开评论
			showMore(commentId) {
				for (var i in this.commentData.comment) {
					if (this.commentData.comment[i].id == commentId) {
						this.commentData.comment[i].hasShowMore = !this.commentData.comment[i].hasShowMore;
						this.$forceUpdate();
						return
					}
					for (var j in this.commentData.comment[i].children) {
						if (this.commentData.comment[i].children[j].id == commentId) {
							this.commentData.comment[i].children[j].hasShowMore = !this.commentData.comment[i].children[j]
								.hasShowMore;
							this.$forceUpdate();
							return
						}
					}
				}
			},
			// 输入框失去焦点
			blur() {
				this.focus = false;
			},
			// 输入框聚焦
			focusOn() {
				this.$emit('focusOn');
			},
			// 标签关闭
			tagClose() {
				this.showTag = false;
				this.pUser = null;
				this.commentReq.pId = null;
			},
			// 输入评论
			commentInput() {
				// TODO 调起键盘方法
				this.submit = true;
				setTimeout(() => {
					this.focus = true;
				}, 50)
			},
			// 关闭输入评论
			closeInput() {
				this.focus = false;
				this.submit = false;
			}
		}
	};
</script>

<style lang="scss" scoped>
	.hb-comment {
		padding: 10rpx;
	}

	.top-read {
		font-size: 28rpx;
		padding-left: 10rpx;
		color: #999999;
	}

	.seg_line_box {
		display: flex;
		height: 5rpx;
		justify-content: space-between;
		margin: 5rpx 0;
	}

	.seg_line {
		width: 45%;
		border-bottom: 1rpx solid #e1e1e1;
	}

	.seg_dot {
		width: 8%;
		border-bottom: 5rpx dotted #dbdbdb;
	}

	.comment-num {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 20rpx 0;
	}

	.comment-box {
		padding: 10rpx 0;
	}

	.comment-box-item {
		display: flex;
	}

	.comment-main {
		padding-left: 20rpx;
	}

	.comment-main-top {
		width: 600rpx;
		padding-top: 6rpx;
		display: flex;
		justify-content: space-between;
	}

	.sub-comment-main-top {
		width: 510rpx;
		padding-top: 6rpx;
		display: flex;
		justify-content: space-between;
	}

	.avatar {
		width: 70rpx;
		height: 70rpx;
		border-radius: 50%;
	}

	.nick-name-box {
		display: flex;
		align-items: center;
	}

	.comLogo {
		margin-right: 18rpx;
		font-size: 22rpx;
		border-radius: 10rpx;
		padding: 5rpx 15rpx;
		color: #FFFFFF;
	}

	.com1 {
		background-color: #d218b1;
	}

	.com2 {
		background-color: #f19c0b;
	}

	.com3 {
		background-color: #c8da85;
	}

	.com4 {
		background-color: #bfd0da;
	}

	.nick-name {
		color: #2d8cf0;
	}

	.isLike {
		font-size: 28rpx;
		padding-right: 10rpx;
		color: #2d8cf0;
	}

	.notLike {
		font-size: 28rpx;
		padding-right: 10rpx;
		color: #999999;
	}

	.comment-main-content {
		padding: 10rpx 10rpx 10rpx 0;
	}

	.comment-main-foot {
		display: flex;
		font-size: 22rpx;
	}

	.replayTag {
		color: #909399;
		margin-bottom: 5px;
		border: 1px solid #c8c9cc;
		background-color: #f4f4f5;
		border-radius: 3px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 16rpx;
		padding: 5px 10px;
	}

	.replyTagClose {
		font-size: 20px;
		line-height: 12px;
		padding: 0 0 2px 5px;
	}

	.foot-btn {
		padding-left: 10rpx;
		color: #007AFF;
	}

	.comment-sub-box {
		padding: 20rpx 0;
	}

	.comment-sub-item {
		display: flex;
	}

	.comment-none {
		padding: 20rpx;
		width: 100%;
		text-align: center;
		color: #999999;
	}

	.comment-submit-box {
		position: fixed;
		display: flex;
		align-items: flex-end;
		z-index: 9900;
		left: 0;
		top: var(--window-top);
		bottom: 0;
		background-color: rgba($color: #000000, $alpha: 0.5);
		width: 100%;
	}

	.comment-add {
		position: fixed;
		background-color: #FFFFFF;
		width: 100%;
		padding: 5rpx;
		border: 1px solid #ddd;
		transition: .3s;
		-webkit-transition: .3s;
	}

	.btn-click {
		color: #007AFF;
		font-size: 28rpx;
		padding: 10rpx;
	}

	.cancel {
		color: #606266;
	}

	.textarea {
		height: 100px;
		padding: 16rpx;
		width: 100%;
	}

	.comment-submit {
		padding: 5rpx 20rpx 0 20rpx;
		border-bottom: 1px dashed #ddd;
		width: calc(100% - 40rpx);
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
</style>
