<template>
	<view class="content">
        <view class="checkBox">
            <view class="checkText">
                <text>编辑个人信息</text>
            </view>
            <view class="saveBox" @click="save()">
                <view class="saveIcon"></view>
                <view class="saveText">保存</view>
            </view>
        </view>
        <view class="imgBox">
            <img :src="userInfo.url" class="img" />
        </view>
        <view class="infoBox">
            <view class="name">电子邮箱</view>
            <input :value="userInfo.email" @input="emailInput" class="input" type="text" maxlength='50' placeholder="请输入电子邮箱" placeholder-style="color:#bfbfbf" />
        </view>
        <view class="infoBox">
            <view class="name">家庭住址</view>
            <input :value="userInfo.homeAddress" @input="homeAddressInput" class="input" type="text" maxlength='50' placeholder="请输入家庭住址" placeholder-style="color:#bfbfbf" />
        </view>
        <view class="infoBox">
            <view class="name">电话号码</view>
            <input :value="userInfo.phone" @input="phoneInput" class="input" type="text" maxlength='11' placeholder="请输入电话号码" placeholder-style="color:#bfbfbf" />
        </view>
        <view class="infoBox">
            <view class="name">用户名</view>
            <input :value="userInfo.username" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">姓名</view>
            <input :value="userInfo.name" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">性别</view>
            <input :value="userInfo.sex" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">身份证号</view>
            <input :value="userInfo.idCard" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">生日</view>
            <input :value="userInfo.birthday" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">部门</view>
            <input :value="userInfo.departmentName" disabled class="input" style="color:#C1C4CB" type="text"/>
        </view>
        <view class="infoBox">
            <view class="name">职位</view>
            <input :value="userInfo.position" disabled class="input" style="color:#C1C4CB" type="text"/>
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
                userInfo:{}
			}
		},
		onLoad() {
            let _this = this;
            wx.getStorage({
                key: 'userInfo',
                success (res) {
                    _this.userInfo = res.data;
                    _this.userInfo.url = wx.getStorageSync('userUrl');
                },
                fail (err) {
                    wx.showToast({
                        title:'获取用户信息失败',
                        icon:'none',
                        duration: 500
                    });
                }
            })
		},
		methods: {
            emailInput(e){
                this.userInfo.email=e.detail.value;
            },
            homeAddressInput(e){
                this.userInfo.homeAddress=e.detail.value;
            },
            phoneInput(e){
                this.userInfo.phone=e.detail.value;
            },
            async setInfo(info){//提交信息
                const res = await this.$request({
                    url:'/selfprofile',
                    method:'PUT',
                    data:info
                })
                return res.data;
            },
            save(){
                if(this.userInfo.phone==''||this.userInfo.email==''||this.userInfo.homeAddress==''){
                    wx.showToast({
                        title:'需补全信息不能为空',
                        icon:'none',
                        duration: 2500
                    });
                    return;
                }
                if(!(/^[1][3,4,5,7,8][0-9]{9}$/.test(this.userInfo.phone))){
                    wx.showToast({
                        title:'手机号码格式有误',
                        icon:'none',
                        duration: 2500
                    });
                    return;
                }
                if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.userInfo.email))){
                    wx.showToast({
                        title:'邮箱格式有误',
                        icon:'none',
                        duration: 2500
                    });
                    return;
                }
                this.isActive = true;
                let obj = {
                    phone:this.userInfo.phone,
                    email:this.userInfo.email,
                    homeAddress:this.userInfo.homeAddress
                }
                this.setInfo(obj)
                .then(res=>{
                    if(res.status!==200){
                        this.isActive = false;
                        wx.showToast({
                            title:'保存失败',
                            icon:'none',
                            duration: 2500
                        });
                    }else{
                        wx.setStorageSync('userInfo',this.userInfo);
                        wx.navigateBack({
                            success:()=>{
                                this.isActive = false;
                                wx.showToast({
                                    title:'保存成功',
                                    icon:'none',
                                    duration: 2500
                                });
                            }
                        })
                    }
                })
		    }
        }
    }
</script>

<style scoped lang='scss'>
    .content{
        height: 100vh;
        width: 100%;
        background-color: #ECF1F6;
    }
    .checkBox{
        height: 100rpx;
        width: 100%;
        background-color: white;
        display: flex;
        justify-content: space-between;
        .checkText{
            height: 100%;
            width: 320rpx;
            text{
                font-size: 35rpx;
                line-height: 100rpx;
                margin-left: 30rpx;
                color: #030919;
            }
        }
        .saveBox{
            height: 100%;
            width: 150rpx;
            display: flex;
            align-items: center;
            justify-content: space-between;
            .saveIcon{
                height: 40rpx;
                width: 40rpx;
                background: url('../../../static/success2.png') no-repeat;
                background-size: 100% 100%;
            }
            .saveText{
                height: 100%;
                width: 100rpx;
                font-size: 35rpx;
                line-height: 100rpx;
                color: #2F3032;
            }
        }
    }
    .imgBox{
        height: 160rpx;
        width: 100%;
        background-color: #F8F9FA;
        display: flex;
        align-items: center;
        justify-content: center;
        .img{
            height: 120rpx;
            width: 120rpx;
            flex-shrink: 0;
            border-radius: 50%;
            overflow: hidden;
        }
    }
    .infoBox{
        margin-top: 16rpx;
        height: 76rpx;
        width: 100%;
        background-color: #F8F9FA;
        display: flex;
        align-items: center;
        .name{
            height: 100%;
            width: 35%;
            font-size: 31rpx;
            line-height: 80rpx;
            color: #9A9B9C;
            text-align: center;
        }
        .input{
            width: 55%;
            font-size: 31rpx;
            color: #303133;
        }
    }
</style>
