<template>
    <view class="content">
        <view class="imgBox"></view>
        <view class="textBox">暂无数据</view>
    </view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		mounted() {

		},
		methods: {

		}
	}
</script>

<style scoped lang='scss'>
	.content{
        height: 800rpx;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .imgBox{
        height: 100rpx;
        width: 150rpx;
        background: url('../../static/nodata.png') no-repeat;
        background-size: 100% 100%;
    }
    .textBox{
        height: 100rpx;
        width: 200rpx;
        font-size: 30rpx;
        color: #8C96A2;
        text-align: center;
        line-height: 100rpx;
    }
</style>