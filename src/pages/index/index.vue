<template>
	<view>
		<view class="homeBox">
			<view class="topBox">
				<view class="titleBox">
					<view class="titleImg"></view>
					<view class="titleText">协同办公平台</view>
				</view>
			</view>
			<swiper style="height:270rpx;"></swiper>
			<view class="menuBox">
				<view class="menuRow">
					<view 
					class="menuItem"
					v-for="item in menuList[0]"
					:key="item.name"
					@tap="goto(item.route)"
					>
						<image :src="item.src" class="img"></image>
						<view class="text">{{item.name}}</view>
					</view>
				</view>
				<view class="menuRow">
					<view 
					class="menuItem"
					v-for="item in menuList[1]"
					:key="item.name"
					@tap="goto(item.route)"
					>
						<image :src="item.src" class="img"></image>
						<view class="text">{{item.name}}</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import NoPermission from '../../components/common/NoPermission.vue'
import Swiper from '../../components/index/swiper.vue'
	export default {
		components:{
			NoPermission,
			Swiper
		},
		data() {
			return {
				title : '首页',
				canGetIn:true,
				canUpdate:true,
				isFirst:true,
				menuList:[[
					{
						name:'名片夹',
						src:'../../static/menu/card_holder_opt.png',
						route:'/pages/CardFile/CardFile'
					},
					{
						name:'日程安排',
						src:'../../static/menu/schedule_opt.png',
						route:'/pages/Schedule/Schedule'
					},
					{
						name:'通讯录',
						src:'../../static/menu/address_book_opt.png',
						route:'/pages/AddrList/AddrList'
					}
				],[
					{
						name:'会议室管理',
						src:'../../static/menu/meetingroom_opt.png',
						route:'/pages/MeetingroomManage/MeetingroomManage'
					},
					{
						name:'会议管理',
						src:'../../static/menu/meeting_opt.png',
						route:'/pages/MeetingManage/MeetingManage'
					},
					{
						name:'公文流转',
						src:'../../static/menu/send_file_opt.png',
						route:'/pages/DocumentCirculation/DocumentCirculation'
					}
				]]
			}
		},
		onLoad(){
		},
		onShow() {
			// /*
			if(wx.getStorageSync('userToken')!='' && wx.getStorageSync('userUrl')!=''){
				this.canGetIn = true;
				this.canUpdate = false;
			}
			wx.showNavigationBarLoading();
			let _this = this;
			if(_this.canUpdate) _this.canGetIn = false;
			wx.login({
				success (res) {
					if (res.code) {
						//发起网络请求
						_this.login({
							code: res.code
						})
						.then(res=>{
							if(res.status!=200){
								wx.showToast({
										title:'获取登录态失败',
										icon:'none',
										duration: 2500
								});
								wx.hideNavigationBarLoading();
							}else{
								let {openId,token,wxInfo} = res.object;
								if(wxInfo){
									wx.setStorage({
											key:'userUrl',
											data:wxInfo.url
									})
									wx.setStorage({
											key:'userNickName',
											data:wxInfo.nickname
									})
								}
								if(token){
										wx.setStorage({
											key:"userToken",
											data:token
										})
										_this.canGetIn = true;
								}
								if(openId){
									wx.setStorage({
										key:"userId",
										data:openId
									})
								}
								if(_this.isFirst){
									wx.showToast({
											title:'更新登录态',
											icon:'none',
											duration: 2500
									});
									_this.isFirst = false;
								}
								wx.hideNavigationBarLoading();
							}
						})
					} else {
							wx.showToast({
									title:'获取登录态失败',
									icon:'none',
									duration: 2500
							});
							wx.hideNavigationBarLoading();
					}
				}
			})
			// */
		},
		methods: {
			goto(route){
				if(!this.canGetIn){
					uni.showModal({
							title:'提示',
							content:'使用服务前，请先前往 [我的]-[身份绑定] 绑定客户端账号，并点击头像授权系统获取您的基础信息',
							cancelText:'暂不前往',
							confirmText:'立即前往',
							success: (res) => {
									if (res.confirm) {
											wx.switchTab({
													url: '/pages/personal/personal'
											});
									}
							}
					})
				}else{
					wx.navigateTo({
							url: route
					});
				}
			},
			async login(info){//登录
					const res = await this.$request2({
							url:'/wx/auth',
							method:'GET',
							data:info
					})
					return res.data;
			}
		}
	}
</script>

<style scoped lang='scss'>
	.homeBox{
		height: 100vh;
		width: 100%;
		background-color: #F5F7FA;
	}
	.topBox{
		height: 140rpx;
		background-color: #5383EC;
		display: flex;
		justify-content: center;
		.titleBox{
			margin-top: 30rpx;
			height: 65rpx;
			width: 70%;
			display: flex;
			align-items: center;
			background-color: #bbcbee;
			border-radius: 32.5rpx;
			.titleImg{
				margin-left: 18%;
				height: 70%;
				width: 10%;
				background: url('../../static/document.png') no-repeat;
				background-size: 100% 100%;
			}
			.titleText{
				margin-left: 6%;
				height: 100%;
				width: 61%;
				font-size: 40rpx;
				line-height: 65rpx;
				color: #263D6E;
			}
		}
	}
	.topBox:after{
		position: absolute;
		left: 0;
		right: 0;
		top: 140rpx;
		z-index: o;
		content: ' ';
		height: 120rpx;
		width: 100%;
		border-radius: 0 0 50% 50%;
		background-color: #5383EC;
	}
	.menuBox{
		height: 280rpx;
		margin-top: 40rpx;
		width: 90%;
		margin-left: 5%;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		.menuRow{
			height: 130rpx;
			width: 100%;
			display: flex;
			justify-content: space-around;
			.menuItem{
				height: 100%;
				width: 120rpx;
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				align-items: center;
				.img{
					height: 70rpx;
					width: 70rpx;
				}
				.text{
					width: 100%;
					color: #2d4c8f;
					font-size: 25rpx;
					height: 40rpx;
					text-align: center;
				}
			}
		}
	}
</style>
