<template>
	<view>
    <view class="box">
    <the-draft :fileInfo="fileInfo"></the-draft>
    </view>
    <ourLoading :active='isActive' text="loading..." />
	</view>
</template>

<script>
import ourLoading from '@/components/our-loading/our-loading.vue'
import TheDraft from './TheDraft.vue'
	export default {
		components:{
      ourLoading,
      TheDraft
		},
		data() {
			return {
        isActive:true,
        fileInfo:{}
			}
    },
		onLoad(options) {
      this.getDocumentInfo({
        sendfileId:options.sendfileId
      })
      .then(res=>{
        this.fileInfo = res.object;
        this.isActive = false;
      })
		},
		methods: {
			async getDocumentInfo(data){//获取公文详情
				const res = await this.$request({
					url:'/document',
          method:'GET',
          data
				})
				return res.data;
      },
		}
	}
</script>

<style scoped lang='scss'>
  .box{
    height: 100vh;
    width: 750rpx;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 20rpx;
    box-sizing: border-box;
  }
</style>
