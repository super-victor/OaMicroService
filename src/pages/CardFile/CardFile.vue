<template>
    <view class="card_file">
        <view class="title">
            <text>我的名片夹</text>
            <button @tap="addCard">添加</button>
        </view>
        <scroll-view scroll-y="true" class="card_area">
            <view
            v-for="item in cardList"
            :key="item.cardId"
            class="card_item">
                <view class="card_row">
                    <text class="label">姓名</text>
                    <text>{{item.name}}</text>
                </view>
                <view class="card_row">
                    <text class="label">电话</text>
                    <text>{{item.phone}}</text>
                </view>
                <view class="card_row">
                    <text class="label">邮箱</text>
                    <text>{{item.email}}</text>
                </view>
                <view class="card_row">
                    <text class="label">公司</text>
                    <text>{{item.company}}</text>
                </view>
                <view class="card_row">
                    <text class="label">地址</text>
                    <text>{{item.address}}</text>
                </view>
            </view>
        </scroll-view>
        <ourLoading :active='isActive' text="loading..." />
    </view>
</template>

<script>
import ourLoading from '../../components/our-loading/our-loading.vue'
export default {
    components: {
        ourLoading
    },
    data() {
        return {
            isActive:true,
            cardList:[]
        }
    },
    onLoad(option) {
        this.getCardList();
        if (JSON.stringify(option) !== '{}') {
            const item = JSON.parse(decodeURIComponent(option.item));
            console.log(item[0]);
            this.cardList.unshift(item[0]);
        }
    },
    methods: {
        async getCard() {
            const res = await this.$request({
                url:'/w/Card',
                metod:'GET',
            })
            return res.data;
        },
        getCardList () {
            this.getCard()
            .then(res=>{
                res.object.forEach(element => {
                    this.cardList.push(element);
                });
                this.isActive=false;
            })
            .catch(err=>{
                wx.showToast({
                    title:'获取失败',
                    icon:'none',
                    duration: 2500
                });
            })
        },
        addCard() {
            uni.navigateTo({
                url:'/pages/CardFile/children/cardAdd'
            })
        }
    }
}
</script>

<style lang="scss" scoped>
.card_file {
    padding: 20rpx;
    .title {
        font-size: 40rpx;
        margin-bottom: 30rpx;
        display: flex;
        justify-content: space-between;
        height: 50rpx;
        text::before {
            content: '|';
            color: #5383EC;
            font-size: 50rpx;
            font-weight: bolder;
            width: 50rpx;
            margin-right: 20rpx;
        }
        button {
            font-size: 25rpx;
            height: 60rpx;
            margin: 0;
            background-color: #9CB7F0;
            color: #fff;
            margin-right: 20rpx;
            margin-top: 5rpx;
        }
    }
    .card_area {
        overflow-y: scroll;
        height: auto;
        padding: 20rpx;
        .card_item {
            background-color: #EFEFEE;
            border: 1px solid #EBEEF5;
            padding: 20rpx 40rpx;
            margin-right: 40rpx;
            font-size: $uni-font-size-base;
            color: #606266;
            margin-bottom: 30rpx;
            .card_row {
                height: 50rpx;
                .label {
                    color: #303133;
                    margin-right: 50rpx;
                }
            }
        }
    }
}
</style>