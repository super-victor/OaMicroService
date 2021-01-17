<template>
    <view class="card_file">
        <view class="title">
            <text>我的名片夹</text>
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
export default {
    data() {
        return {
            isActive:true,
            cardList:[{
                name:'张菲燕',
                phone:'13348943243',
                email:'yu@163.com',
                company:'Sicnu',
                address:'成龙',
                cardId:0,
            },{
                name:'张菲燕',
                phone:'13348943243',
                email:'yu@163.com',
                company:'Sicnu',
                address:'成龙',
                cardId:1,
            },{
                name:'张菲燕',
                phone:'13348943243',
                email:'yu@163.com',
                company:'Sicnu',
                address:'成龙',
                cardId:2,
            },{
                name:'张菲燕',
                phone:'13348943243',
                email:'yu@163.com',
                company:'Sicnu',
                address:'成龙',
                cardId:3,
            },{
                name:'张菲燕',
                phone:'13348943243',
                email:'yu@163.com',
                company:'Sicnu',
                address:'成龙',
                cardId:4,
            }]
        }
    },
    methods: {
        async getcard() {
            const res = await this.$request({
                url:'Card',
                metod:'GET',
            })
            return res.data;
        },
        getCardList () {
            this.getCard()
            .then(res=>{
                this.cardList = res.object;
                this.isActive=false;
            })
            .catch(err=>{
                wx.showToast({
                    title:'修改失败',
                    icon:'none',
                    duration: 2500
                });
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
        text::before {
            content: '|';
            color: #5383EC;
            font-size: 50rpx;
            font-weight: bolder;
            width: 50rpx;
        }
    }
    .card_area {
        overflow-y: scroll;
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