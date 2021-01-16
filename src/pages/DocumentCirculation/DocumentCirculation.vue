<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="text" active-color="#5383EC"></uni-segmented-control>
    <view v-show="current===0">
      <wyb-table :first-line-fixed="true" header-bg-color="#8FBCFF" header-ft-color="#263D6E" content-ft-color="#303133" link-color="#9CB7F0" first-col-bg-color="#F5F7FA" ref="table" :headers="headers" :contents="contents" height="92vh" :url-col="urlCol" />
    </view>
    <view v-show="current===1">
      2
    </view>
	</view>
</template>

<script>
import wybTable from '../../components/wyb-table/wyb-table'
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control.vue"
	export default {
		components:{
      wybTable,
			uniSegmentedControl
		},
		data() {
        const theUrl = '/pages/demos/noticeBar-demo/more' // 本地的某个页面
        const httpUrl = 'https://ext.dcloud.net.cn/plugin?id=2667' // 某个网址
			return {
        items: ['公文一览','查询拟稿'],
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
            contents: [{
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02 13:02:20',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            },{
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            },{
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            },{
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }, {
                title: '1232',
                type: '公告',
                urgent: '加急',
                senderName: '111',
                subject: '122 334',
                updateTime: '2018-11-02',
                url: ['查看详情', '/']
            }],
            urlCol: [{
                type: 'route',
                key: 'url'
            }]
			}
		},
		onLoad() {
      this.getDocumentInfo()
      .then(res=>{
        this.contents = res.object.map((item)=>{
          let obj = {
            senderName:item.senderDetail.name,
            title:item.title || '未命名',
            updateTime:new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, ''),
            url:['查看详情', item.sendfileId]
          }
          return {...item,...obj};
        })
      })
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
