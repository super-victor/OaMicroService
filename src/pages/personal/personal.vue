<template>
	<view class="content">
		<view class="topBox">
			<view class="userBox">
				<img :src="url==''?'../../static/login.png':url" class="img" @tap="openBox()" />
				<view class="name">{{name}}</view>
			</view>
		</view>
		<view class="helpBox">
			<view class="contentBox">
				<view class="contents" @tap="clickList('/pages/MeetingManage/MeetingManage?id=1',1)">
					<image class="icon" src="../../static/icon/wait-meeting.png"></image>
					<view class="text">待审会议</view>
				</view>
			</view>
			<view class="contentBox">
				<view class="contents" @tap="clickList('/pages/DocumentCirculation/DocumentCirculation?id=1',1)">
					<image class="icon" src="../../static/icon/wait-document.png"></image>
					<view class="text">待审公文</view>
				</view>
			</view>
		</view>
		<view class="listBox">
			<uni-list>
				<uni-list-item :clickable="true" title="个人信息" @click="clickList('/pages/personal/children/personalInfo',1)"></uni-list-item>
				<uni-list-item :clickable="true" title="身份绑定" @click="clickList('/pages/personal/children/identityBinding',0)"></uni-list-item>
			</uni-list>
		</view>
		<get-user-info-button :open='change' @close='closeBox' @refresh='updatePage' ></get-user-info-button>
	</view>
</template>

<script>
	import uniList from "@dcloudio/uni-ui/lib/uni-list/uni-list.vue"
	import uniListItem from "@dcloudio/uni-ui/lib/uni-list-item/uni-list-item.vue"
	import getUserInfoButton from '../../components/common/getUserInfoButton'
	export default {
		components:{
			uniList,
			uniListItem,
			getUserInfoButton
		},
		data() {
			return {
				url:"",
				name:"未登录",
				canlogin:true,
				change:false,
			}
		},
		onLoad() {
		},
		onShow() {
			this.change = false;
			wx.showNavigationBarLoading();
			let _this = this;
			//已经获取了用户信息
			if(this.url!=''){
        wx.getSetting({
            success: res => {
                if(res.authSetting['scope.userInfo']){
                    wx.getUserInfo({
                        lang:'zh_CN',
                        withCredentials:false,
                        success:(res)=>{
													_this.name = res.userInfo.nickName;
													_this.url = res.userInfo.avatarUrl;
                        }
                    })
                }else{
									uni.showModal({
										title:'提示',
										content:'为了更好地为您提供服务，平台仅会获取您的头像、昵称等非敏感信息，如您允许我们使用信息，请前往修改页面打开权限许可',
										cancelText:'暂不修改',
										confirmText:'前往修改',
										success: (res) => {
											if (res.confirm) {
											wx.openSetting({
												success: (res) => {
													if(res.authSetting["scope.userInfo"]){
														wx.getUserInfo({
															lang:'zh_CN',
															withCredentials:false,
															success:(res)=>{
																	_this.setUserInfo({
																			nickname:res.userInfo.nickName,
																			url:res.userInfo.avatarUrl,
																			openid:wx.getStorageSync('userId')
																	})
																	.then(res=>{
																			if(res.status!==200){
																					wx.showToast({
																									title:'信息保存失败',
																									icon:'none',
																									duration: 2500
																					});
																			}
																	})
															}
														})
													}
												}
											})
											}
										}
									})
                }
            }
        })
				wx.hideNavigationBarLoading();
			}else{
					if(wx.getStorageSync('userUrl')!=''){
						this.name = wx.getStorageSync('userNickName');
						this.url = wx.getStorageSync('userUrl');
						this.canlogin = false;
						wx.hideNavigationBarLoading();
					}else{
						//没有取到数据
						this.canlogin=true;
						wx.hideNavigationBarLoading();
					}
			}
		},
		methods: {
			async setUserInfo(info){//存储信息到数据库
					const res = await this.$request2({
							url:'/wx/updateWxInfo',
							method:'POST',
							data:info
					})
					return res.data;
			},
			closeBox(msg){
				this.change=msg;
			},
			openBox(){
				if(this.canlogin===''){
						return;
				}
				if(this.canlogin){
					this.change=true;
				}
			},
			updatePage(msg){
				let {nickname,url} = msg;
				this.name = nickname;
				this.url = url;
				this.canlogin = false;
			},
			clickList(pageUrl,islogin){
				if(islogin!==0){
					if(this.canlogin===''){
						return;
					}
					if(this.canlogin){
						this.change=true;
						return;
					}else{
						if(wx.getStorageSync('userToken')==''){
								wx.showToast({
										title:'请先进行账号绑定',
										icon:'none',
										duration: 500
								});
								return;
						}
						wx.navigateTo({
							url: pageUrl
						})
					}
				}else{
					wx.navigateTo({
						url: pageUrl
					})
				}
			}
		}
	}
</script>

<style scoped lang='scss'>
	.content {
		height: 100vh;
		background-color: #F1F5F6;
	}
	.topBox{
		height: 300rpx;
		width: 100%;
		background-color: #5383EC;
		.userBox{
			height: 200rpx;
			width: 30%;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			align-items: center;
			.img{
				margin-top: 30rpx;
				height: 90rpx;
				width: 90rpx;
                flex-shrink: 0;
                border-radius: 50%;
                overflow: hidden;
			}
			.name{
				margin-top: 20rpx;
				height: 40rpx;
				width: 140rpx;
				font-size: 27rpx;
				color: #303133;
				text-align: center;
				line-height: 40rpx;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
		}
	}
	.helpBox{
		height: 140rpx;
		width: 90%;
		background-color: white;
		margin: -70rpx auto 0;
		border-radius: 10rpx;
		box-shadow: 0 5rpx 8rpx rgba(236, 236, 236, 0.5);
		display: flex;
		justify-content: space-around;
		align-items: center;
		.contentBox{
			height: 65%;
			width: 45%;
			display: flex;
			justify-content: center;
			.contents{
				height: 100%;
				width: 40%;
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				align-items: center;
				.icon{
					height: 57%;
					width: 47%;
					background-size: 100% 100%;
					background-repeat: no-repeat;
				}
				.text{
					height: 30%;
					width: 100%;
					color: #565865;
					font-size: 29rpx;
					line-height: 27.3rpx;
					text-align: center;
				}
			}
		}
	}
	.listBox{
		min-height: 450rpx;
		width: 90%;
		margin: 7vh auto 0;
		border-radius: 10rpx;
		overflow: hidden;
	}
</style>
