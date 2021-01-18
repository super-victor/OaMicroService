<template>
	<view class="content">
        <view v-if="isBinding==0">
            <view class="Title">身份绑定</view>
            <view class="Tips">请在此绑定您的客户端登录信息</view>
            <view class="inputBox">
                <input @input="onInput($event,'id')" class="input" placeholder="请输入用户名" />
                <input @input="onInput($event,'password')" style="margin-top:30rpx;" class="input" password type="text" placeholder="请输入密码" />
            </view>
            <button class="button" type="primary" @click="submit()">提交</button>
        </view>
        <view v-else>
            <view class="SuccessBox">
                <view class="icon"></view>
                <text style="margin-left:20rpx;">您已绑定成功</text>
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
        isActive:true,
        isBinding:'0',
        id:'',
        password:'',
        name:''
			}
		},
		onLoad() {
            this.bindingStatus({
              openid:wx.getStorageSync('userId')
            })
            .then(res=>{
                if(res.status===200){
                    let {name,username} = res.object;
                    this.isBinding='1';
                    this.id=username;
                    this.name=name;
                }
                this.isActive=false;
            })
		},
		methods: {
            onInput(e,name){
                if(name=='id'){
                    this.id=e.detail.value;
                }else{
                    this.password=e.detail.value;
                }
            },
            async bindingStatus(info){//绑定状态
                const res = await this.$request2({
                    url:'/wx/isbind',
                    method:'get',
                    data:info
                })
                return res.data;
            },
            async bindingInfo(info){//绑定信息
                const res = await this.$request2({
                    url:'/wx/binduser',
                    method:'POST',
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
                    openid:wx.getStorageSync('userId')
                }
               this.bindingInfo(obj)
                .then(res=>{
                    if(res.status!==200){
                        this.isActive = false;
                        wx.showToast({
                            title:res.msg,
                            icon:'none',
                            duration: 2500
                        });
                    }else{
                        wx.setStorage({
                          key:'userInfo',
                          data:res.object.userInfo
                        })
                        wx.setStorage({
                          key:'userToken',
                          data:res.object.token
                        })
                        this.isBinding=1;
                        this.name=res.object.userInfo.name;
                        this.id=res.object.userInfo.username;
                        this.isActive = false;
                        wx.showToast({
                            title:'绑定成功',
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
