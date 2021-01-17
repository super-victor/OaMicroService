<template>
    <view class="background" v-show="dialogFormVisible">
        <view class="content">
            <view class="title">
                <view class="img"></view>
                <view class="text">申请获取您的用户信息</view>
            </view>
            <view class="buttonBox">
                <button class="button" type="primary" style="background-color: #F56C6C;" @click="closebox()">拒绝</button>
                <button class="button" type="primary" style="background-color: #67C23A;" open-type="getUserInfo" @getuserinfo="bindGetUserInfo" @click="closebox()">同意</button>
            </view>
        </view>
    </view>
</template>

<script>
	export default {
		props:["open"],
		data() {
			return {
                dialogFormVisible:false,
			}
        },
        watch:{
            open: {
                handler(newValue, oldValue) {
                    this.dialogFormVisible=newValue;
                },
                deep: true
    　　     }
        },
		onLoad() {

		},
		methods: {
            closebox(){
                this.$emit('close',false);
            },
            async setUserInfo(info){//存储信息到数据库
                const res = await this.$request2({
                    url:'/wx/updateWxInfo',
                    method:'POST',
                    data:info
                })
                return res.data;
            },
            bindGetUserInfo (e) {
                if(e.detail.errMsg==='getUserInfo:ok'){
                    this.setUserInfo({
                        nickname:e.detail.userInfo.nickName,
                        url:e.detail.userInfo.avatarUrl,
                        openid:wx.getStorageSync('userId')
                    })
                    .then(res=>{
                        if(res.status!==200){
                            wx.showToast({
                                    title:'信息保存失败',
                                    icon:'none',
                                    duration: 2500
                            });
                        }else{
                            this.$emit('refresh',{nickname:e.detail.userInfo.nickName,url:e.detail.userInfo.avatarUrl});
                        }
                    })
                    wx.setStorage({
                        key:'userUrl',
                        data:e.detail.userInfo.avatarUrl
                    })
                    wx.setStorage({
                        key:'userNickName',
                        data:e.detail.userInfo.nickName
                    })
                    this.$emit('refresh',{nickname:e.detail.userInfo.nickName,url:e.detail.userInfo.avatarUrl});
                }
            }
		}
	}
</script>

<style scoped lang='scss'>
    .background{
        top: 0;
        position: fixed;
        z-index: 10000;
        height: 100vh;
        width: 100%;
        background-color: rgba(87, 88, 87,0.5);
    }
    .content{
        position: relative;
        height: 260rpx;
        width: 600rpx;
        background-color: #fcfcfc;
        top: 340rpx;
        left: 50%;
        margin-left: -300rpx;
        box-shadow: 0 8rpx 11rpx rgba(236, 236, 236, 0.5);
        border-radius: 20rpx;
    }
    .title{
        height: 100rpx;
        width: 70%;
        margin: 30rpx 0 0 30rpx;
        display: flex;
        align-items: center;
        .img{
            height: 40%;
            width: 12%;
            background: url('../../static/document.png') no-repeat;
            background-size: 100% 100%;
            margin-left: 20rpx;
        }
        .text{
            height: 100%;
            width: 80%;
            margin-left: 20rpx;
            font-size: 30rpx;
            color: #030919;
            text-align: center;
            line-height: 100rpx;
        }
    }
    .buttonBox{
        height: 70rpx;
        width: 90%;
        margin: 40rpx auto 0;
        display: flex;
        justify-content: space-between;
        .button{
            width: 30%;
            font-size: 30rpx;
            line-height: 70rpx;
        }
    }
</style>
