<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
    <view v-show="current===0">
		<MeetingManage></MeetingManage> 
    </view>
    
	</view>
</template>

<script>
import MeetingManage from "../MeetingManage/Children/MyMeeting"
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
	export default {
		components:{
			uniSegmentedControl,
			MeetingManage,
		},
		data() {
			return {
        items: ['个人会议信息'],
        current: 0,
			}
		},
		onLoad() {

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
