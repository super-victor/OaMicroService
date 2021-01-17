<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
    <view v-show="current===0">
      <wyb-table :first-line-fixed="true" header-bg-color="#8FBCFF" header-ft-color="#263D6E" content-ft-color="#303133" link-color="#9CB7F0" first-col-bg-color="#F5F7FA" ref="table" :headers="headers" :contents="contents" height="92vh" :url-col="urlCol" />
    </view>
    <view v-show="current===1">
      <uni-collapse accordion="true">
          <uni-collapse-item :showAnimation="true" title="草稿箱" :thumb="require('../../static/edit.png')">
              <view style="padding: 20rpx;">
                  <wyb-table v-if="draftsContents.length!=0" :width="700" :first-line-fixed="true" header-bg-color="#8FBCFF" header-ft-color="#263D6E" content-ft-color="#303133" link-color="#9CB7F0" first-col-bg-color="#F5F7FA" ref="table" :headers="draftsHeaders" :contents="draftsContents"/>
                  <view v-else class="nodataBox">暂无</view>
              </view>
          </uni-collapse-item>
          <uni-collapse-item :showAnimation="true" title="未过审拟稿" :thumb="require('../../static/cry.png')">
              <view style="padding: 20rpx;">
                  <wyb-table v-if="rejectContents.length!=0" :width="700" :first-line-fixed="true" header-bg-color="#8FBCFF" header-ft-color="#263D6E" content-ft-color="#303133" link-color="#9CB7F0" first-col-bg-color="#F5F7FA" ref="table" :headers="rejectHeaders" :contents="rejectContents" :url-col="rejectUrlCol"/>
                  <view v-else class="nodataBox">暂无</view>
              </view>
          </uni-collapse-item>
          <uni-collapse-item :open="open" :showAnimation="true" title="待审核拟稿" :thumb="require('../../static/history.png')">
              <view style="padding: 20rpx;">
                  <wyb-table v-if="verifyContents.length!=0" :width="700" :first-line-fixed="true" header-bg-color="#8FBCFF" header-ft-color="#263D6E" content-ft-color="#303133" link-color="#9CB7F0" first-col-bg-color="#F5F7FA" ref="table" :headers="verifyHeaders" :contents="verifyContents" :url-col="verifyUrlCol"/>
                  <view v-else class="nodataBox">暂无</view>
              </view>
          </uni-collapse-item>
      </uni-collapse>
    </view>
    <ourLoading :active='isActive' text="loading..." />
	</view>
</template>

<script>
import wybTable from '../../components/wyb-table/wyb-table'
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
import uniCollapse from '@dcloudio/uni-ui/lib/uni-collapse/uni-collapse.vue'
import uniCollapseItem from '@dcloudio/uni-ui/lib/uni-collapse-item/uni-collapse-item.vue'
import ourLoading from '@/components/our-loading/our-loading.vue'
	export default {
		components:{
      wybTable,
      uniSegmentedControl,
      uniCollapse,
      uniCollapseItem,
      ourLoading
		},
		data() {
			return {
        items: ['公文一览','查询拟稿'],
        open:false,
        isActive:false,
        current: 0,
            headers: [{
                label: '操作',
                key: 'url'
            }, {
                label: '公文标题',
                key: 'title',
                width: 230
            }, {
                label: '公文种类',
                key: 'type',
                width: 150
            }, {
                label: '紧急程度',
                key: 'urgent',
                width: 150
            }, {
                label: '签发人',
                key: 'senderName',
                width: 180
            }, {
                label: '主题词',
                key: 'subject',
                width: 200
            }, {
                label: '发布时间',
                key: 'updateTime',
                width: 320
            }],
            contents: [],
            urlCol: [{
                type: 'route',
                key: 'url'
            }],
            draftsHeaders:[{
                label: '草稿标题',
                key: 'title',
                width: 360
            },{
                label: '最近修改时间',
                key: 'updateTime',
                width: 360
            },],
            draftsContents:[],
            rejectHeaders:[{
                label: '拟稿标题',
                key: 'title',
                width: 240
            },{
                label: '回复时间',
                key: 'updateTime',
                width: 240
            },{
                label: '操作',
                key: 'url',
                width: 240
            }],
            rejectUrlCol: [{
                type: 'route',
                key: 'url'
            }],
            rejectContents:[],
            verifyHeaders:[{
                label: '拟稿标题',
                key: 'title',
                width: 240
            },{
                label: '提交审核时间',
                key: 'updateTime',
                width: 240
            },{
                label: '操作',
                key: 'url',
                width: 240
            }],
            verifyUrlCol: [{
                type: 'route',
                key: 'url'
            }],
            verifyContents:[]
			}
    },
    onLoad(option){
      if(option.id){
        this.current = 1;
        this.open = true;
      }
    },
		onShow() {
      this.isActive = true;
      this.getDocumentInfo()
      .then(res=>{
        this.contents = res.object.map((item)=>{
          let obj = {
            senderName:item.senderDetail.name,
            title:item.title || '未命名',
            updateTime:new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, ''),
            url:['查看详情', '/pages/DocumentCirculation/children/DraftDetail' , {sendfileId:item.sendfileId}]
          }
          return {...item,...obj};
        })
        Promise.all([this.getDraftDocumentInfo(),this.getRejectDocumentInfo(),this.getVerifyDocumentInfo()])
        .then(res=>{
          this.draftsContents = res[0].object.map(item=>{
            item.title = item.title || '未命名';
            item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
            return item;
          });
          this.rejectContents = res[1].object.map(item=>{
            item.title = item.title || '未命名';
            item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
            item.url = ['查看详情', '/pages/DocumentCirculation/children/RejectDraftDetail' , {sendfileId:item.sendfileId}];
            return item;
          });
          this.verifyContents = res[2].object.map(item=>{
            item.title = item.title || '未命名';
            item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
            item.url = ['查看详情', '/pages/DocumentCirculation/children/VerifyDraftDetail' , {sendfileId:item.sendfileId}];
            return item;
          });
          this.isActive = false;
        })
      })
    },
    onPullDownRefresh(){
      setTimeout(()=>{
        this.getDocumentInfo()
        .then(res=>{
          this.contents = res.object.map((item)=>{
            let obj = {
              senderName:item.senderDetail.name,
              title:item.title || '未命名',
              updateTime:new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, ''),
              url:['查看详情', '/pages/DocumentCirculation/children/DraftDetail' , {sendfileId:item.sendfileId}]
            }
            return {...item,...obj};
          })
          Promise.all([this.getDraftDocumentInfo(),this.getRejectDocumentInfo(),this.getVerifyDocumentInfo()])
          .then(res=>{
            this.draftsContents = res[0].object.map(item=>{
              item.title = item.title || '未命名';
              item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
              return item;
            });
            this.rejectContents = res[1].object.map(item=>{
              item.title = item.title || '未命名';
              item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
              item.url = ['查看详情', '/pages/DocumentCirculation/children/RejectDraftDetail' , {sendfileId:item.sendfileId}];
              return item;
            });
            this.verifyContents = res[2].object.map(item=>{
              item.title = item.title || '未命名';
              item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
              item.url = ['查看详情', '/pages/DocumentCirculation/children/VerifyDraftDetail' , {sendfileId:item.sendfileId}];
              return item;
            });
          })
        })
      },300)
    },
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
      },
			async getDocumentInfo(){//获取所有公文
				const res = await this.$request({
					url:'/allPublishDocument',
					method:'GET'
				})
				return res.data;
      },
			async getDraftDocumentInfo(){//获取草稿箱
				const res = await this.$request({
					url:'/draftbox',
					method:'GET'
				})
				return res.data;
      },
			async getRejectDocumentInfo(){//获取未过审
				const res = await this.$request({
					url:'/documementNotPassed',
					method:'GET'
				})
				return res.data;
      },
			async getVerifyDocumentInfo(){//获取待审核
				const res = await this.$request({
					url:'/documentInCheck',
					method:'GET'
				})
				return res.data;
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
  .nodataBox{
    height: 50rpx;
    width: 750rpx;
    line-height: 50rpx;
    font-size: 30rpx;
    text-align: center;
    color: #303133;
  }
</style>
