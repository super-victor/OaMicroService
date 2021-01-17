<template>
    <view class="background" v-show="isClick">
        <view class="content">
            <uni-card

                    :title="isClickList.name" 
                    :is-shadow="true" 
                    :extra="isClickList.startTime" 
                    note="true"
                    >
                    <view class="messAge">{{"所在会议室："+isClickList.meetingRoomName}}</view>
                    <view class="messAge">{{"参会人数："+isClickList.peopleNum}}</view>
                    <view class="messAge">{{"预计会议结束时间："+isClickList.endTime}}</view>
                    <template v-slot:footer>
                        <view class="footer-box">
                            <view class="reset" @click="reset()" >收起</view>
                        </view>
                    </template>
                </uni-card> 
        </view>
    </view>
</template>

<script>
import uniCard from '@dcloudio/uni-ui/lib/uni-card/uni-card'
	export default {
        components:{
            uniCard
        },
		props:{
            open:Boolean,
            isClick:Boolean,
            isClickList:Object
        },
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
            async setUserInfo(msg){//存储信息到数据库
                const res = await this.request({
                    url:'/user/setBasicInfo',
                    method:'POST',
                    data:{
                        nickname:msg.nickName,
                        url:msg.avatarUrl
                    }
                })
                return res.data;
            },
            bindGetUserInfo (e) {
                if(e.detail.errMsg==='getUserInfo:ok'){
                    // this.setUserInfo(e.detail.userInfo)
                    // .then(res=>{
                    //     if(res.error===114){
                    //         console.log("权限认证错误");
                    //     }else{
                    //         this.$emit('refresh',{nickname:e.detail.userInfo.nickName,url:e.detail.userInfo.avatarUrl});
                    //     }
                    // })
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
            },
            reset(){
                this.$emit('getIsClick')
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

        top: 340rpx;
        left: 50%;
        margin-left: -300rpx;
 
          .messAge{
                font-size:30rpx;
            }
            .footer-box{
                .reset{
                    color:#5383EC;
                    width:60rpx;
                    font-size:30rpx;
                    border-bottom: 1rpx solid #5383EC;
                }
            }   
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
            background: url('../../../static/document.png') no-repeat;
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
