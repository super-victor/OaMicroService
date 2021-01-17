<template>
	<view>
    <view class="tipBox">
      <uni-steps active-color="#68b88e" :options="[{title: '编写拟稿'}, {title: '确认拟稿'}, {title: '审核进行中'}, {title: '公文发布'}]" :active="2"></uni-steps>
    </view>
    <view class="buttonBox">
      <button type="warn" size="mini" style="width:500rpx;" @click="cancel">取消审核</button>
    </view>
    <view class="box">
    <the-draft :fileInfo="fileInfo"></the-draft>
    </view>
    <ourLoading :active='isActive' text="loading..." />
	</view>
</template>

<script>
import ourLoading from '@/components/our-loading/our-loading.vue'
import TheDraft from './TheDraft.vue'
import uniSteps from '@dcloudio/uni-ui/lib/uni-steps/uni-steps.vue'
	export default {
		components:{
      ourLoading,
      TheDraft,
      uniSteps
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
			async cancelCheck(data){//取消审稿
				const res = await this.$request({
					url:'/cancelCheck',
          method:'PUT',
          data
				})
				return res.data;
      },
      cancel(){
        let _this = this;
        uni.showModal({
            title: '提示',
            content: '确认要取消审核吗',
            success: function (res) {
                if (res.confirm) {
                    _this.cancelCheck({
                      sendfileId:_this.fileInfo.sendfileId
                    })
                    .then(res=>{
                      if(res.status!=200){
                        wx.showToast({
                            title:'取消审核失败',
                            icon:'none',
                            duration: 2500
                        });
                      }else{
                        uni.navigateBack();
                        wx.showToast({
                            title:'取消审核成功，拟稿已为您放入草稿箱',
                            icon:'none',
                            duration: 2500
                        });
                      }
                    })
                }
            }
        });
      }
		}
	}
</script>

<style scoped lang='scss'>
.tipBox{
  height: 80rpx;
  width: 750rpx;
  margin-top: 40rpx;
}
.buttonBox{
  height: 70rpx;
  width: 500rpx;
  margin-left: 125rpx;
  display: flex;
  margin-top: 30rpx;
  justify-content: center;
}
::v-deep button[size=mini]{
  line-height: 70rpx;
}
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
