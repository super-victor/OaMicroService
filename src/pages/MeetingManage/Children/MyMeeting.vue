<template>
    <view class="MyMeeting">
        <uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="button" active-color="#8791eb"></uni-segmented-control>
        <view class="content">
            <view v-if="current === 0">
                <uni-collapse accordion="true">
                    <uni-collapse-item title="会议名" :open="true">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                    <uni-collapse-item title="会议名">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                    <uni-collapse-item title="会议名">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                </uni-collapse>
            </view>
            <view v-if="current === 1">
                <uni-collapse accordion="true">
                    <uni-collapse-item title="会议名">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                    <uni-collapse-item title="会议名">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                    <uni-collapse-item title="会议名">
                        <view style="padding: 30rpx;">
                            手风琴效果
                        </view>
                    </uni-collapse-item>
                </uni-collapse>
            </view>
            <view v-if="current === 2">
                123
            </view>
        </view>
    </view>
</template>

<script>
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control"
import uniCollapse from '@dcloudio/uni-ui/lib/uni-collapse/uni-collapse'
import uniCollapseItem from '@dcloudio/uni-ui/lib/uni-collapse-item/uni-collapse-item'
export default {
    components:{
        uniCollapse,
        uniCollapseItem,
        uniSegmentedControl
    },
    data() {
        return {
            items: ['待进行会议','历史会议','待审批会议'],
            current: 0,
            CollapseList:[],
            CollapsePendingList:[],
            CollapseAppointmentList:[],
            CollapseRecordList:[],

        }
    },
    created(){
        this.getAllMeetings().then(res=>{
            console.log(res.object);
            this.CollapseList = res.object.filter(item=>{
            return this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapsePendingList = res.object.filter(item=>{
            return item.appoinmentStatus == 1 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapseAppointmentList = res.object.filter(item=>{
            return item.appoinmentStatus == 0 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapseRecordList = res.object.filter(item=>{
            return item.appoinmentStatus == 2 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
        })
    },
    methods:{
        onClickItem(e) {
            if (this.current !== e.currentIndex) {
                this.current = e.currentIndex;
            }
        },
        async getAllMeetings() {
            const res = await this.$request({
            url: '/getAllMeetings',
            method: 'get',
            })
            return res.data;
        },
    }
}
</script>

<style>
</style>