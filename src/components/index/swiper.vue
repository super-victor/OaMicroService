<template>
    <view class="swiperBox">
        <swiper class="swiper" indicator-dots="true" autoplay="true" interval="5000" duration="700" circular="true">
            <swiper-item
            v-for="(item,index) in swiper_list"
            :key="index"
            >
                <!-- <image class="swiper-item" :src="item.url" @tap="goRouter(item.router)"></image> -->
								<view class="swiper-item">
									<view class="noDataBox" v-if="item==null">
										<img src="../../static/noSchedule.png" alt="" class="img">
										<view class="text">{{`暂无即将开始的${index==0?'个人':'公司'}日程`}}</view>
									</view>
									<view class="scheduleBox" v-else>
										<view class="titleBox">
											<view class="smallTitle"><text style="background-color:#9CB7F0;padding:3px;">{{`${index==0?'个人':'公司'}日程`}}</text></view>
											<view class="titleText">
												<text style="font-size:35rpx;height:60rpx;line-height:60rpx;color:#303133;">{{item.content}}</text>
											</view>
										</view>
										<view style="height:3%;width:100%;border-bottom: 1px solid #C0C4CC;margin-bottom:10rpx;"></view>
										<view style="display:flex;">
											<image src="../../static/icon/s1.png" class="icon"></image><view class="location"> {{item.location}}</view>
										</view>
										<view style="display:flex;">
											<image src="../../static/icon/s2.png" class="icon"></image><view class="remark"> {{item.remark}}</view>
										</view>
										<view style="display:flex;">
											<image src="../../static/icon/s3.png" class="icon"></image><view class="timeBox"> {{item.startTime}} - {{item.endTime}}</view>
										</view>
									</view>
								</view>
            </swiper-item>
        </swiper>
    </view>
</template>

<script>
	export default {
		data() {
			return {
				swiper_list:[
					{
						content:'12223',
						location:'222',
						remark:'3333',
						startTime:'2019-32012',
						endTime:'12320013'
					},
					{
						content:'12223',
						location:'222',
						remark:'3333',
						startTime:'2019-32012',
						endTime:'12320013'
					}
				]
			}
		},
		mounted() {
		},
		methods: {
            goRouter(router){
                wx.navigateTo({
                    url: router
                });
            }
		}
	}
</script>

<style scoped lang='scss'>
	.swiperBox{
		height: 260rpx;
		width: 80%;
		margin: 0 auto;
		border-radius: 13rpx;
		overflow: hidden;
		-webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
	}
	.swiper{
		height: 100%;
		width: 100%;
	}
	.swiper-item{
		height: 100%;
		width: 100%;
		background-color: #f1f3f5;
	}
	.noDataBox{
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		.img{
			height: 130rpx;
			width: 130rpx;
			margin-top: 20rpx;
		}
		.text{
			height: 50rpx;
			width: 100%;
			color: #d8d8d8;
			font-size: 30rpx;
			line-height:50rpx;
			text-align: center;
		}
	}
	.scheduleBox{
		height: 100%;
		width: 100%;
		padding: 5rpx 15rpx;
		box-sizing: border-box;
		border-radius: 4rpx;
		.titleBox{
			height: 70rpx;
			width: 100%;
			display: flex;
			.smallTitle{
				height: 100%;
				width: 25%;
				font-size: 30rpx;
				padding-left: 10rpx;
				box-sizing: border-box;
				color: #4A76D4;
				line-height: 70rpx;
				user-select: none;
				font-family:'jdzhonyuanjian2510280c4f22004';
				overflow: hidden;
				text-overflow:ellipsis;
				white-space: nowrap;
			}
			.titleText{
				height: 100%;
				width: 75%;
				display: flex;
				align-items: center;
			}
		}
		.icon{
			height: 35rpx;
			width: 35rpx;
			margin-left: 15rpx;
		}
		.location{
			height: 45rpx;
			width: 100%;
			font-size: 25rpx;
			padding-left: 10rpx;
			box-sizing: border-box;
			line-height: 45rpx;
			color: #606266;
		}
		.remark{
			height: 45rpx;
			width: 100%;
			font-size: 25rpx;
			padding-left: 10rpx;
			box-sizing: border-box;
			line-height: 45rpx;
			color: #606266;
		}
		.timeBox{
			height: 45rpx;
			width: 100%;
			font-size: 25rpx;
			padding-left: 10rpx;
			box-sizing: border-box;
			line-height: 45rpx;
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
			color: #606266;
		}
	}
</style>
