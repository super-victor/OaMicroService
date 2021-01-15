<template>
	<view class="content">
        <view v-if="isBinding==0">
            <view class="Title">身份认证</view>
            <view class="Tips">请在此绑定您的客户端登录信息</view>
            <view class="inputBox">
                <input @input="onInput($event,'id')" class="input" type="number" maxlength="10" placeholder="请输入用户名" />
                <input @input="onInput($event,'password')" style="margin-top:30rpx;" class="input" password type="text" placeholder="请输入密码" />
            </view>
            <button class="button" type="primary" @click="submit()">提交认证</button>
        </view>
        <view v-else>
            <view class="SuccessBox">
                <view class="icon"></view>
                <text style="margin-left:20rpx;">您已认证成功</text>
            </view>
            <view class="inputBox">
                <view class="text">用户名：{{id}}</view>
                <view class="text">姓名：{{name}}</view>
            </view>
        </view>
        <ourLoading :active='isActive' text="loading..." />
	</view>
</template>

<script>
import ourLoading from '@/components/our-loading/our-loading.vue'
	export default {
		components:{
            ourLoading
		},
		data() {
			return {
        isActive:false,
        isBinding:'0',
        id:'',
        password:'',
        name:''
			}
		},
		onLoad() {
            // this.bindingStatus()
            // .then(res=>{
            //     if(res.error===0){
            //         this.isBinding=res.binding+'';
            //         if(res.binding==1){
            //             this.id=res.id;
            //             this.name=res.username;
            //         }
            //         this.isActive=false;
            //     }
            // })
		},
		methods: {
            onInput(e,name){
                if(name=='id'){
                    this.id=e.detail.value;
                }else{
                    this.password=e.detail.value;
                }
            },
            async bindingStatus(){//绑定状态
                const res = await this.$myRequest({
                    url:'/user/bindingStatus',
                    method:'POST',
                    user:true,
                })
                return res.data;
            },
            async bindingInfo(info){//绑定信息
                const res = await this.$request({
                    url:'/login',
                    method:'POST',
                    // check:true,
                    // user:true,
                    data:info
                })
                return res.data;
            },
            submit(){
                if(this.id==''||this.password==''){
                    wx.showToast({
                        title:'认证信息不能为空',
                        icon:'none',
                        duration: 2500
                    });
                    return;
                }
                this.isActive = true;
                let obj = {
                    username:this.id,
                    password:this.password,
                }
                console.log(obj)
               this.bindingInfo(obj)
                .then(res=>{
                  console.log(res)
                    if(res.status!==200){
                        this.isActive = false;
                        wx.showToast({
                            title:res.msg,
                            icon:'none',
                            duration: 2500
                        });
                    }else{
                        this.isBinding=1;
                        // this.name=res.username;
                        this.isActive = false;
                        wx.showToast({
                            title:'认证成功',
                            icon:'none',
                            duration: 2500
                        });
                    }
                })
            }
		}
	}
</script>

<style scoped lang='scss'>
    .content{
        padding: 1px;
        height: 1200rpx;
        width: 100%;
    }
    .Title{
        height: 100rpx;
        width: 80%;
        margin: 120rpx auto 0;
        font-size: 60rpx;
        line-height: 100rpx;
		color: #030919;
    }
    .Tips{
        height: 60rpx;
        width: 80%;
        margin: 0 auto;
        font-size: 40rpx;
        line-height: 60rpx;
        color: #8A8E95;
    }
    .inputBox{
        margin: 50rpx auto 0;
        height: 150rpx;
        width: 80%;
        .input{
            height: 70rpx;
            border-bottom: 1rpx solid #E2E7EB;
            font-size: 35rpx;
        }
        .text{
            height: 70rpx;
            font-size: 35rpx;
            color: #030919;
            line-height: 70rpx;
            width: 100%;
        }
    }
    .button{
        margin: 100rpx auto 0;
        width: 80%;
        background-color: #5383EC;
    }
    .SuccessBox{
        height: 100rpx;
        width: 80%;
        margin: 120rpx auto 0;
        font-size: 60rpx;
        line-height: 100rpx;
        color: #030919;
        display: flex;
        .icon{
            height: 100rpx;
            width: 100rpx;
            background: url('../../../static/success.png') no-repeat;
            background-size: 100% 100%;
        }
    }
</style>
