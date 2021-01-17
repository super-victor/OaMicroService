<template>
	<view>
        <uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
        <view class="messageBox">
            <view v-if="current === 0">
				<no-data v-if="notReadList.length===0"></no-data>
				<uni-swipe-action v-else>
					<uni-swipe-action-item
					 :right-options="options"
					 v-for="(item,index) in notReadList"
					 @click="onClick($event,item.messageId,index,item)"
					 :key="item.messageId">
						<view class="item">
							<view class="tag" :style="{'background-color':item.type==0?'#919398':(item.type==1?'#7EC050':(item.type==2?'#5A9CF8':'#DCA550'))}">{{item.type==0?'危险':(item.type==1?'成功':(item.type==2?'提示':'警告'))}}</view>
							<view class="word">
								<view class="topBox">
									<view class="title">{{item.title}}</view>
									<view class="time">{{item.createtime}}</view>
								</view>
								<view class="text">{{item.content}}</view>
							</view>
						</view>
					</uni-swipe-action-item>
				</uni-swipe-action>
            </view>
            <view v-if="current === 1">
				<no-data v-if="readList.length===0"></no-data>
				<view v-else>
					<view
					 v-for="(item,index) in readList"
					 @click="onClick($event,item.messageId,index,item)"
					 :key="item.messageId">
						<view class="item">
							<view class="tag" :style="{'background-color':item.type==0?'#919398':(item.type==1?'#7EC050':(item.type==2?'#5A9CF8':'#DCA550'))}">{{item.type==0?'危险':(item.type==1?'成功':(item.type==2?'提示':'警告'))}}</view>
							<view class="word">
								<view class="topBox">
									<view class="title">{{item.title}}</view>
									<view class="time">{{item.createtime}}</view>
								</view>
								<view class="text">{{item.content}}</view>
							</view>
						</view>
					</view>
				</view>
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
						text: '置为已读',
						style: {
							backgroundColor: '#4B7DA6'
						}
					}
				],
				//未读的信息
				notReadList:[],
				//已读的信息
				readList:[]
			}
		},
		onLoad() {
		},
		onShow(){
			if(wx.getStorageSync('userToken')){
				if(this.isFirst) uni.startPullDownRefresh();
			}else{
				wx.showToast({
					title:'请先进行账号绑定',
					icon:'none',
					duration: 2500
				});
			}
			if(!this.isFirst){
				wx.showNavigationBarLoading();
				this.getInfo()
				.then(res=>{
					if(res.status!==200){
						wx.showToast({
							title:'获取信息失败',
							icon:'none',
							duration: 2500
						});
					}else{
						this.readList=[];
						this.notReadList=[];
						for(let item of res.object){
							if(item.isRead===1){
								this.readList.push(item);
							}else{
								this.notReadList.push(item);
							}
						}
					}
					wx.hideNavigationBarLoading();
				})
			}
		},
		//监听手动下拉刷新
		onPullDownRefresh() {
			if(wx.getStorageSync('userToken')){
				setTimeout(()=>{
					this.getInfo()
					.then(res=>{
						if(res.status!==200){
							wx.showToast({
								title:'获取信息失败',
								icon:'none',
								duration: 2500
							});
						}else{
							this.readList=[];
							this.notReadList=[];
							for(let item of res.object){
								if(item.isRead===1){
									this.readList.push(item);
								}else{
									this.notReadList.push(item);
								}
							}
						}
						uni.stopPullDownRefresh();
					})
				}, 600);
			}else{
				wx.showToast({
					title:'请先进行账号绑定',
					icon:'none',
					duration: 2500
				});
			}
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			onClick(e,id,index,item){
				let obj = {
					messageId:id
				}
				this.readMsg(obj)
				.then(res=>{
					if(res.status!==200){
						wx.showToast({
							title:'操作失败',
							icon:'none',
							duration: 2500
						});
					}else{
						this.notReadList.splice(index,1);
						this.readList.unshift(item);
					}
				})
			},
			async getInfo(){//获取信息
				const res = await this.$request({
					url:'/message',
					method:'GET'
				})
				return res.data;
			},
			async readMsg(info){//读消息
				const res = await this.$request({
					url:'/readMessage',
					method:'PUT',
					data:info
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
	.tag{
		height: 45rpx;
		width: 90rpx;
		margin-left: 30rpx;
		border-radius: 10rpx;
		font-size: 23rpx;
		text-align: center;
		line-height: 45rpx;
		color: white;
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
		font-size: 30rpx;
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
		font-size: 25rpx;
	}
  ::v-deep .segmented-control__text{
    color: #303133 !important;
    font-size: 29rpx !important;
  }
  ::v-deep .segmented-control{
    height: 85rpx !important;
  }
</style>
