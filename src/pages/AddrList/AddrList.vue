<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
    <view v-show="current===0">
		<AddrListSelect></AddrListSelect>
    </view>
    
	</view>
</template>

<script>
import AddrListSelect from "../AddrList/Children/AddrListSelect.vue"
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
	export default {
		components:{
			uniSegmentedControl,
			AddrListSelect,
		},
		data() {
			return {
        items: ['通讯录'],
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
