<template>
	<view>
        <uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
        <view class="messageBox">
            <view v-if="current === 0">
				<no-data v-if="receivedList.length===0"></no-data>
				<uni-swipe-action v-else>
					<uni-swipe-action-item
					 :options="options" 
					 v-for="(item,index) in receivedList"
					 @click="onClick($event,item.id,item.url,item.name,item.text,item.time,item.msgid,index)"
					 :key="item.msgid">
						<view class="item">
							<img :src="item.url==''?'../../static/nologin.png':item.url" class="img" />
							<view class="word">
								<view class="topBox">
									<view class="title" :style="{color:item.id=='xsb00000001'?'#fca106':'#030919'}">{{item.name}}</view>
									<view class="time">{{item.time}}</view>
								</view>
								<view class="text">{{item.text}}</view>
							</view>
						</view>
					</uni-swipe-action-item>
				</uni-swipe-action>
            </view>
            <view v-if="current === 1">
				<no-data v-if="sendList.length===0"></no-data>
				<uni-swipe-action v-else>
					<uni-swipe-action-item
					 :options="options" 
					 v-for="(item,index) in sendList"
					 @click="onClick($event,item.id,item.url,item.name,item.text,item.time,item.msgid,index)"
					 :key="item.msgid">
						<view class="item">
							<img :src="item.url==''?'../../static/nologin.png':item.url" class="img" />
							<view class="word">
								<view class="topBox">
									<view class="title">{{item.name}}</view>
									<view class="time">{{item.time}}</view>
								</view>
								<view class="text">{{item.text}}</view>
							</view>
						</view>
					</uni-swipe-action-item>
				</uni-swipe-action>
            </view>
        </view>
	</view>
</template>

<script>
	import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
	import uniSwipeAction from '@dcloudio/uni-ui/lib/uni-swipe-action/uni-swipe-action.vue';
	import uniSwipeActionItem from '@dcloudio/uni-ui/lib/uni-swipe-action-item/uni-swipe-action-item.vue'
	import noData from '../../components/common/noData'
	export default {
		components:{
			uniSegmentedControl,
			uniSwipeAction,
			uniSwipeActionItem,
			noData
		},
		data() {
			return {
				isFirst:true,
				items: ['未读','已读'],
				current: 0,
				options:[
					{
						text: '查看',
						style: {
							backgroundColor: '#4B7DA6'
						}
					}, {
						text: '删除',
						style: {
							backgroundColor: '#dd524d'
						}
					}
				],
				//收到的信息
				receivedList:[],
				//发送的信息
				sendList:[]
			}
		},
		onLoad() {
			uni.startPullDownRefresh();
		},
		onShow(){
			// this.updateInfo()
			// .then(res=>{
			// 	if(!this.isFirst){
			// 		wx.showNavigationBarLoading();
			// 		this.getInfo()
			// 		.then(res=>{
			// 			if(res.error!==0){
			// 				wx.showToast({
			// 					title:'获取信息失败',
			// 					icon:'none',
			// 					duration: 2500
			// 				});
			// 			}else{
			// 				this.receivedList=res.result[0];
			// 				this.sendList=res.result[1];
			// 			}
			// 			wx.hideNavigationBarLoading();
			// 		})
			// 	}
			// })
		},
		//监听手动下拉刷新
		onPullDownRefresh() {
			setTimeout(()=>{
				this.getInfo()
				.then(res=>{
					if(res.error!==0){
						wx.showToast({
							title:'获取信息失败',
							icon:'none',
							duration: 2500
						});
					}else{
						this.receivedList=res.result[0];
						this.sendList=res.result[1];
						this.isFirst=false;
					}
					uni.stopPullDownRefresh();
				})
			}, 600);
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			onClick(e,...info){
				if(e.index===0){
					wx.navigateTo({
						url: `/pages/message/detailedMessage?id=${info[0]}&url=${info[1]}&name=${info[2]}&text=${info[3]}&time=${info[4]}&index=${this.current}`
					})
				}else{
					let obj = {
						current:this.current,
						msgid:info[5]
					}
					this.deleteMsg(obj)
					.then(res=>{
						if(res.error!==0){
							wx.showToast({
								title:'删除失败',
								icon:'none',
								duration: 2500
							});
						}else{
							if(this.current==0){
								this.receivedList.splice(info[6],1);
							}else{
								this.sendList.splice(info[6],1);
							}
						}
					})
				}
			},
            async getInfo(){//获取信息
                const res = await this.$myRequest({
                    url:'/messages/gainInfo',
					method:'POST',
                    user:true,
                    check:true,
                })
                return res.data;
			},
            async deleteMsg(info){//删除消息
                const res = await this.$myRequest({
                    url:'/messages/delete',
					method:'POST',
					check:true,
					data:info
                })
                return res.data;
			},
            async updateInfo(){//更新信息
                const res = await this.$myRequest({
                    url:'/details/updateInfo',
                    method:'POST',
					check:true,
					user:true
                })
                return res.data;
            },
		}
	}
</script>

<style scoped lang='scss'>
	.messageBox{
		margin-top: 20rpx;
	}
	.item{
		height: 160rpx;
		width: 100%;
		display: flex;
		align-items: center;
	}
	.img{
		width: 100rpx;
		height: 100rpx;
		flex-shrink: 0;
		border-radius: 50%;
		overflow: hidden;
		margin-left: 20rpx;
	}
	.word{
		margin-left: 30rpx;
		height: 100rpx;
		width: 580rpx;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	.topBox{
		height: 50rpx;
		width: 100%;
		display: flex;
		justify-content: space-between;
	}
	.title{
		height: 100%;
		width: 50%;
		font-size: 38rpx;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		line-height: 50rpx;
		color: #030919;
	}
	.time{
		height: 100%;
		width: 40%;
		font-size: 23rpx;
		text-align: right;
		line-height: 50rpx;
		color: #777777;
	}
	.text{
		height: 40rpx;
		width: 100%;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		color: #777777;
		line-height: 40rpx;
	}
  ::v-deep .segmented-control__text{
    color: #303133 !important;
    font-size: 29rpx !important;
  }
  ::v-deep .segmented-control{
    height: 85rpx !important;
  }
</style>
