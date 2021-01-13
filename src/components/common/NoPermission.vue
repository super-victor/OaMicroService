<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="buttonBox">
			<button type="default" plain="true" size="mini" class="button">前往登录</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: '首页'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style scoped lang='scss'>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 100vh;
		background-color: #F5F7FA;
	}
	.logo {
		height: 150rpx;
		width: 150rpx;
	}
	.buttonBox{
		height: 200rpx;
		width: 300rpx;
		display: flex;
		align-items: center;
		.button{
			background-color: #5383EC;
			border: #9CB7F0 solid 1rpx;
			color: white;
		}
	}
</style>
