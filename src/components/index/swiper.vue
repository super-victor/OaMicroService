<template>
    <view class="swiperBox">
        <swiper class="swiper" indicator-dots="true" autoplay="true" interval="5000" duration="700" circular="true">
            <swiper-item
            v-for="(item,index) in swiper_list"
            :key="index"
            >
                <image class="swiper-item" :src="item.url" @tap="goRouter(item.router)"></image>
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
                        url:'https://mmbiz.qpic.cn/mmbiz_jpg/gsQM61GSzIP728VzIZ4dP5TcFI3AIWAh95dc714n8FibhN3os4sWQSBjrciaJ5BN6VOT9drNvQBicfpgoXHOFZwzA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1',
                        router:''
                    },
                    {
                        url:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1211%2F21%2Fc1%2F15954767_1353492149857.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613136316&t=f5e920696f21ff6a46110e03e1156c61',
                        router:''
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
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}
</style>
