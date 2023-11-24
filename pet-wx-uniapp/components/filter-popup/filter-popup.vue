<template>
    <popup
        :mask="mask"
        border-radius="50"
        v-model="acceptValue"
        mode="bottom"
        class="filter-popup"
        :height="height"
        @close="close"
        :style="{ '--color': themeColor }"
        :mask-custom-style="{ background: 'rgba(0, 0, 0, 0.7)' }"
    >
        <view class="top-title flex-row-sb" v-if="showTop">
            <view></view> <view class="popup-title flex-row-c-c">{{ title }}</view
            ><text class="saasIcon flex-row-c-c" @click="close">&#xe60c;</text>
        </view>

        <scroll-view class="select-scroll" scroll-y :style="{ height: `calc( ${height} - 120rpx - 152rpx  )` }">
            <view class="select-main">
                <view class="select-item" v-for="(item, index) in data" :key="index">
                    <view class="title"> {{ item.title }} </view>
                    <view class="tag-list" v-if="!item.children">
                        <view
                            class="tag-item"
                            :class="[acceptForm[item.key].includes(item2.value) ? 'select-on' : '']"
                            v-for="(item2, index2) in item.data"
                            :key="index2"
                            @click="selectTagBuyValueOrId(item2, item.key, item.keyValue, item.isRadio)"
                        >
                            {{ item2.title }}
                        </view>
                    </view>
                    <!-- 有childer -->
                    <view class="select-childer" v-else v-for="item2 in item.data" :key="item2.id">
                        <view class="childer-title flex-row--c">{{ item2.title }}</view>
                        <view class="tag-list">
                            <view
                                class="tag-item"
                                :class="[acceptForm[item.key].includes(item3.id) ? 'select-on' : '']"
                                v-for="item3 in item2.children"
                                :key="item3.id"
                                @click="selectTagBuyValueOrId(item3, item.key, item.keyValue, item.isRadio)"
                            >
                                {{ item3.title }}
                            </view>
                        </view>
                    </view>
                </view>
            </view>
        </scroll-view>

        <view class="filter-button flex-row-c">
            <view class="btn flex-row">
                <view class="btn-1 flex-row-c-c" @click="reset">重置</view>
                <view class="btn-2 flex-row-c-c" @click="finsh">完成</view>
            </view>
        </view>
    </popup>
</template>

<script>
import Popup from './components/popup.vue';
export default {
    components:{
        Popup
    },
    name: "filter-popup",
    props: {
        //选中的表单
        form: {
            type: Object,
            default: () => {},
        },
        //主题颜色
        themeColor: {
            type: String,
            default: "#0066ff",
        },
        //渲染数据
        data: {
            type: Array,
            default: () => [],
        },
        //标题
        title: {
            type: String,
            default: "请选择",
        },
        value: {
            type: Boolean,
            default: false,
        },
        mask: {
            type: Boolean,
            default: true,
        },
        height: {
            type: String,
            default: "600rpx",
        },
        //是否显示退出按钮
        showTop: {
            type: Boolean,
            default: true,
        },
    },
    computed: {
        // 接收表单
        acceptForm: {
            get() {
                if (Object.keys(this.form).length) {
                    return this.form;
                } else {
                    let obj = {};
                    this.data.forEach((item) => {
                        obj[item.key] = [];
                    });
                    return obj;
                }
            },
            set(nval) {
                // console.log("set Form :>> ", nval);
                this.$emit("update:form", nval);
            },
        },
        acceptValue: {
            get() {
                return this.value;
            },
            set(nval) {
                this.$emit("input", nval);
            },
        },
    },
    methods: {
        //选择存value 还是id
        selectTagBuyValueOrId(item, key, keyValue, isRadio) {
            //如果是单选
            if (isRadio) {
                if (keyValue == "value") {
                    if (this.acceptForm[key].some((value) => value == item.value)) {
                        this.acceptForm[key] = this.acceptForm[key].filter((value) => value !== item.value);
                        return;
                    }
                    this.acceptForm[key] = [];
                    this.acceptForm[key].push(item.value);
                } else {
                    if (this.acceptForm[key].some((id) => id == item.id)) {
                        this.acceptForm[key] = this.acceptForm[key].filter((id) => id !== item.id);
                        return;
                    }
                    this.acceptForm[key] = [];
                    this.acceptForm[key].push(item.id);
                }
            } else {
                if (keyValue == "value") {
                    this.acceptForm[key].some((value) => value == item.value)
                        ? (this.acceptForm[key] = this.acceptForm[key].filter((value) => value !== item.value))
                        : this.acceptForm[key].push(item.value);
                } else {
                    this.acceptForm[key].some((id) => id == item.id)
                        ? (this.acceptForm[key] = this.acceptForm[key].filter((id) => id !== item.id))
                        : this.acceptForm[key].push(item.id);
                }
            }

            this.acceptForm = this.acceptForm;
        },
        //  点击完成
        finsh() {
            this.$emit("finsh", this.acceptForm);
            this.$emit("input", false);
        },
        close() {
            this.$emit("input", false);
            this.$emit("close");
        },
        //重置
        reset() {
            this.acceptForm = {};
        },
    },
};
</script>
<style lang="less" scoped>
@font-face {
  font-family: 'iconfont';  /* Project id 2729410 */
  src: url('https://at.alicdn.com/t/font_2729410_3nhq3ibbcqg.woff2?t=1628330097695') format('woff2'),
       url('https://at.alicdn.com/t/font_2729410_3nhq3ibbcqg.woff?t=1628330097695') format('woff'),
       url('https://at.alicdn.com/t/font_2729410_3nhq3ibbcqg.ttf?t=1628330097695') format('truetype');
}
.saasIcon {
    font-family: "iconfont" !important;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -webkit-text-stroke-width: 0.2px;
    -moz-osx-font-smoothing: grayscale;
}
.filter-popup {
    color: #000000;
    .top-title {
        font-weight: bold;
        height: 90rpx;
        margin-left: 70rpx;
        font-size: 34rpx;
        margin-top: 20rpx;
        .popup-title {
            height: 100%;
        }
        .saasIcon {
            // width: 150rpx;
            height: 100%;
            padding-right: 70rpx;
        }
    }
    .select-scroll {
    }
    .select-main {
        padding: 0 32rpx;
        .select-item {
            .title {
                font-weight: bold;
                font-size: 30rpx;
                color: #000000;
                padding-top: 30rpx;
            }
            .tag-list {
                display: flex;
                flex-wrap: wrap;
                margin-left: -12rpx;
                .tag-item {
                    margin-top: 20rpx;
                    padding: 22rpx 54rpx;
                    background: #f5f5f5;
                    color: #484848;
                    border-radius: 36rpx;
                    margin-left: 12rpx;
                    &.select-on {
                        background: var(--color);
                        color: #fff;
                    }
                }
            }
        }
        .select-childer {
            .childer-title {
                color: #484848;
                font-size: 28rpx;
                border-bottom: 1px solid #f5f5f5;
                height: 80rpx;
            }
        }
    }
    .filter-button {
        width: 100%;
        height: 152rpx;
        background: #ffffff;
        box-shadow: 0px -3px 12px rgba(0, 0, 0, 0.06);
        position: fixed;
        bottom: 0;
        .btn {
            border-radius: 100rpx;
            margin-top: 26rpx;
            width: 680rpx;
            height: 80rpx;
            color: #ffffff;
            font-size: 28rpx;
            overflow: hidden;
            .btn-1 {
                flex: 1;
                background: linear-gradient(271deg, #2698fb 0%, #84c6ff 100%);
            }
            .btn-2 {
                flex: 1;
                background: linear-gradient(90deg, #0066ff 0%, #1371ff 100%);
            }
        }
    }

    // 自定义css
    .flex-row {
        display: flex;
    }

    .flex-row-c {
        display: flex;
        justify-content: center;
    }

    .flex-row--c {
        display: flex;
        align-items: center;
    }

    .flex-row-c-c {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .flex-row-sb-c {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .flex-row-sb-t {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }

    .flex-row-sb-b {
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
    }

    .flex-row-c-sb {
        display: flex;
        justify-content: center;
        align-items: space-between;
    }

    .flex-row-sb {
        display: flex;
        justify-content: space-between;
    }

    .flex-row-l {
        display: flex;
        justify-content: flex-start;
    }

    .flex-row-l-c {
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .flex-row-c-t {
        display: flex;
        justify-content: center;
        align-items: flex-start;
    }

    .flex-row-r-c {
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }

    .flex-row-r {
        display: flex;
        justify-content: flex-end;
    }

    .flex-row--b {
        display: flex;
        align-items: flex-end;
    }

    .flex-col {
        display: flex;
        flex-direction: column;
    }

    .flex-col-c {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .flex-col--c {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .flex-col-c-c {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .flex-col-sb-c {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
    }

    .flex-col-c-sb {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: space-between;
    }

    .flex-col-sb {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .flex-col-t-c {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
    }

    .flex-col-c-l {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
    }

    .flex-col-t {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

    .flex-col-b {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
    }

    .flex-col-b-c {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        align-items: center;
    }

    .flex-col-c-l {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-end;
    }

    .flex-col--l {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }

    .flex-col--r {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
    }
}
</style>
