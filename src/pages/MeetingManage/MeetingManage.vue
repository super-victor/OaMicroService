<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
   <MeetingManage :current="current"></MeetingManage>
    
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
        items: ['待进行会议','历史会议','待审批会议'],
        current:0,
			}
		},
		onLoad(id) {
			console.log(id.id)
			console.log('id:',id.id == 1)
			if( id.id == 1) this.current = 2
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
		},
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
