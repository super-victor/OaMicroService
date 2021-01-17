<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
    <view v-show="current===0">
		<RoomManage></RoomManage>
    </view>
    
	</view>
</template>

<script>
import RoomManage from "../MeetingroomManage/Children/RoomManage"
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
	export default {
		components:{
			uniSegmentedControl,
			RoomManage,
		},
		data() {
			return {
				items: ['会议室信息'],
				current: 0,
			}
		},
		onLoad() {
			var date = new Date()
			this.items = ['会议室信息 '+date.toISOString().slice(0, 10)]
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
		}
	}
</script>

<style scoped lang='scss'>
  ::v-deep .segmented-control__text{
    color: #303133 !important;
    font-size: 29rpx !important;
  }
  ::v-deep .segmented-control{
    height: 85rpx !important;
  }
</style>
