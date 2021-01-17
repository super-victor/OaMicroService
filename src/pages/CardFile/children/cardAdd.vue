<template>
    <view class="card_add">
        <view class="title">
            <text>名片信息</text>
        </view>
        <view class="schedule_row">
            <text>1.姓名</text>
                <input v-model="uName" placeholder="请输入姓名" class="input"/>
        </view>
        <view class="schedule_row">
            <text>2.电话</text>
            <input v-model="uPhone" placeholder="请输入电话" class="input"/>
        </view>
        <view class="schedule_row">
            <text>3.邮箱</text>
            <input v-model="uEmail" placeholder="请输入邮箱" class="input"/>
        </view>
        <view class="schedule_row">
            <text>4.公司</text>
            <input v-model="uCompany" placeholder="请输入公司" class="input"/>
        </view>
        <view class="schedule_row">
            <text>5.地址</text>
            <input v-model="uAddress" placeholder="请输入地址" class="input"/>
        </view>
        <view class="button_row">
            <button @tap="submitAdd">提交</button>
        </view>
    </view>
</template>

<script>
export default {
    data() {
        return {
            uName:'',
            uPhone:'',
            uCompany:'',
            uAddress:'',
            uEmail:'',
        }
    },
    methods: {
        submitAdd() {
            if(this.uName==='' || this.uPhone ==='' || this.uCompany ==='' || this.uAddress ==='' || this.uEmail ===''){
                wx.showToast({
                    title:'信息未填写完整',
                    icon:'none',
                    duration: 2500
                });
            } else {
                const cardInfo = [{
                    name: this.uName,
                    phone: this.uPhone,
                    company: this.uCompany,
                    address: this.uAddress,
                    email: this.uEmail
                }]
                uni.navigateTo({
                    url:`/pages/CardFile/CardFile?item=${encodeURIComponent(JSON.stringify(cardInfo))}`
                })
            }
        }
    }
}
</script>

<style lang="scss" scoped>
.card_add {
    display: flex;
    flex-direction: column;
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
            margin-right: 20rpx;
        }
    }
    .schedule_row {
        display: flex;
        flex-direction: column;;
        font-size: 32rpx;
        padding: 20rpx;
        border-bottom: 1px solid #EBEEF5;
        .input {
            font-size: 35rpx;
        }
        .time_row {
            display: flex;
            justify-content: space-between;
            .select_time {
                color: #5383EC;
            }
        }
    }
    .button_row {
        margin-top: 100rpx;
        button {
            font-size: 32rpx;
            color: #fff;
            background-color: #578BFA;
        }
    }
}
</style>