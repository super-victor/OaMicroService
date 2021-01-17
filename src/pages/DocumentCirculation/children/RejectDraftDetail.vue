<template>
	<view>
    <view class="tipBox">
      <uni-steps active-color="#F56C6C" :options="[{title: '编写拟稿'}, {title: '确认拟稿'}, {title: '未通过审核'}, {title: '修改/删除拟稿'}]" :active="2"></uni-steps>
    </view>
    <view class="messageBox">
      <view class="title">发布失败理由：</view>
      <view class="text">{{fileInfo.cersorOpinion}}</view>
    </view>
    <view class="buttonBox">
      <button size="mini" style="width:300rpx;" @click="putBack">放回草稿箱</button>
      <button type="warn" size="mini" style="width:150rpx;" @click="deleteDraft">删除</button>
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
			async putDocumentNotPassIntoDraftbox(data){//放回草稿箱
				const res = await this.$request({
					url:'/putDocumentNotPassIntoDraftbox',
          method:'PUT',
          data
				})
				return res.data;
      },
			async deleteDocument(data){//删除
				const res = await this.$request({
					url:'/document',
          method:'DELETE',
          data
				})
				return res.data;
      },
      putBack(){
        let _this = this;
        uni.showModal({
            title: '提示',
            content: '确认要将拟稿放回草稿箱吗',
            success: function (res) {
                if (res.confirm) {
                    _this.putDocumentNotPassIntoDraftbox({
                      sendfileId:_this.fileInfo.sendfileId
                    })
                    .then(res=>{
                      if(res.status!=200){
                        wx.showToast({
                            title:'操作失败',
                            icon:'none',
                            duration: 2500
                        });
                      }else{
                        uni.navigateBack();
                        wx.showToast({
                            title:'拟稿已为您放入草稿箱',
                            icon:'none',
                            duration: 2500
                        });
                      }
                    })
                }
            }
        });
      },
      deleteDraft(){
        let _this = this;
        uni.showModal({
            title: '提示',
            content: '确认要删除该拟稿吗，该操作不可逆',
            success: function (res) {
                if (res.confirm) {
                    _this.deleteDocument({
                      sendfileId:_this.fileInfo.sendfileId
                    })
                    .then(res=>{
                      if(res.status!=200){
                        wx.showToast({
                            title:'拟稿删除失败',
                            icon:'none',
                            duration: 2500
                        });
                      }else{
                        uni.navigateBack();
                        wx.showToast({
                            title:'拟稿删除成功',
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
.messageBox{
  min-height: 130rpx;
  width: 750rpx;
  margin-top: 20rpx;
  .title{
    height: 50rpx;
    width: 700rpx;
    line-height: 50rpx;
    font-size: 30rpx;
    font-weight: bolder;
    color: #303133;
    margin-left: 50rpx;
  }
  .text{
    min-height: 70rpx;
    width: 650rpx;
    margin-left: 50rpx;
    margin-top: 10rpx;
    box-sizing: border-box;
    padding: 5rpx 20rpx;
    font-size: 25rpx;
    color: #606266;
    word-break:break-all;
  }
}
.buttonBox{
  height: 70rpx;
  width: 600rpx;
  margin-left: 75rpx;
  display: flex;
  margin-top: 30rpx;
  justify-content: space-between;
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
