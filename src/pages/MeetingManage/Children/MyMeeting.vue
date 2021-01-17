<template>
    <view class="MyMeeting">
        <view class="content">
            <view v-if="current === 0">
            <view  :key="i" v-for="i in CollapsePendingList">
                <uni-list>
                    <uni-list-item :title="i.name" :note="i.meetingRoomName" clickable @click="getDetails(i)"
                     :rightText="i.startTime">
                     </uni-list-item>
                </uni-list>
                <!-- <uni-card 
                    :title="isClickList.name" 
                    :is-shadow="true" 
                    :extra="isClickList.startTime" 
                    note="true"
                    v-if="isClick[i.meetingId]">
                    <view class="messAge">{{"所在会议室："+isClickList.meetingRoomName}}</view>
                    <view class="messAge">{{"参会人数："+isClickList.peopleNum}}</view>
                    <view class="messAge">{{"预计会议结束时间："+isClickList.endTime}}</view>
                    <template v-slot:footer>
                        <view class="footer-box">
                            <view class="reset" @click="test()" >收起</view>
                        </view>
                    </template>
                </uni-card> -->
            </view>
                <noData v-if="CollapsePendingList.length == 0"></noData>
            <Mesdisplay :isClick="isClick" :isClickList="isClickList" @getIsClick = "getisClick"></Mesdisplay>
            </view>
            <view v-if="current === 1">
               <uni-collapse accordion="true">
                    <uni-collapse-item :title="i.name" :open="true" :key="i" v-for="i in CollapseRecordList">
                        <view style="padding: 30rpx;">
                            <view class="message">{{"会议室："+i.meetingRoomName}}</view>
                            <view class="message">{{"开始时间："+i.startTime}}</view>
                            <view class="message">{{"结束时间："+i.endTime}}</view>
                            <view class="message">{{"参会人数："+i.peopleNum}}</view>
                        </view>
                    </uni-collapse-item>                  
                </uni-collapse>
                <noData v-if="CollapseRecordList.length == 0"></noData>
            </view>
            <view v-if="current === 2">
                 <uni-collapse accordion="true">
                    <uni-collapse-item :title="i.name" :open="true" :key="i" v-for="i in  CollapseAppointmentList">
                        <view style="padding: 30rpx;">
                            <view class="message">{{"会议室："+i.meetingRoomName}}</view>
                            <view class="message">{{"开始时间："+i.startTime}}</view>
                            <view class="message">{{"结束时间："+i.endTime}}</view>
                            <view class="message">{{"参会人数："+i.peopleNum}}</view>
                        </view>
                    </uni-collapse-item>
                    
                </uni-collapse>
                <noData v-if="CollapseAppointmentList.length == 0"></noData>
            </view>
        </view>
          <ourLoading :active='isActive' text="loading..." />
    </view>
</template>

<script>
import uniSegmentedControl from "@dcloudio/uni-ui/lib/uni-segmented-control/uni-segmented-control"
import uniCollapse from '@dcloudio/uni-ui/lib/uni-collapse/uni-collapse'
import uniCollapseItem from '@dcloudio/uni-ui/lib/uni-collapse-item/uni-collapse-item'
import noData from '../../../components/common/noData'
import uniList from '@dcloudio/uni-ui/lib/uni-list/uni-list'
import uniListItem from '@dcloudio/uni-ui/lib/uni-list-item/uni-list-item'
import Mesdisplay from '../Components/Mesdisplay'
import ourLoading from '@/components/our-loading/our-loading.vue'

export default {
    components:{
        uniCollapse,
        uniCollapseItem,
        uniSegmentedControl,
        noData,
        uniList,
        uniListItem,
        Mesdisplay,
        ourLoading
        
    },
    data() {
 
        return {
            isActive:true,
            userInfo:'',
            CollapseList:[],
            CollapsePendingList:[],
            CollapseAppointmentList:[],
            CollapseRecordList:[],
            isClickList:{},
            isClick:false,
        }
    },
    created(){
        this.userInfo = wx.getStorageSync('userInfo');
        // console.log(this.userInfo)
        this.getAllMeetings().then(res=>{
            this.isActive = false
            console.log(res.object);
            this.CollapseList = res.object.filter(item=>{
            return this.userInfo.employeeId == item.employeeId?item:''
          })
          this.CollapsePendingList = res.object.filter(item=>{
            return item.appoinmentStatus == 1 && this.userInfo.employeeId == item.employeeId?item:''
          })
          this.CollapseAppointmentList = res.object.filter(item=>{
            return item.appoinmentStatus == 0 && this.userInfo.employeeId == item.employeeId?item:''
          })
          this.CollapseRecordList = res.object.filter(item=>{
            return item.appoinmentStatus == 2 && this.userInfo.employeeId == item.employeeId?item:''
          })
            console.log(" this.CollapsePendingList:", this.CollapsePendingList)
            console.log(" this.CollapseAppointmentList:", this.CollapseAppointmentList)
            console.log(" this.CollapseRecordList:", this.CollapseRecordList)
        })
    },
    methods:{
        // onClickItem(e) {
        //     if (this.current !== e.currentIndex) {
        //         this.current = e.currentIndex;
        //     }
        // },
        async getAllMeetings() {
            const res = await this.$request({
            url: '/getAllMeetings',
            method: 'get',
            })
            return res.data;
        },
        getDetails(res){
            console.log(res,'isClick');
            this.isClickList = res;
            this.isClick = true;
        },
        getisClick(){
            this.isClick = false;
        }
     
    },
    	props:{
			current:Number,
		}
}
</script>

<style scoped lang="scss">
    .MyMeeting{
        .content{
            .messAge{
                font-size:30rpx;
            }
            .footer-box{
                .reset{
                    color:#5383EC;
                    width:60rpx;
                    font-size:30rpx;
                    border-bottom: 1rpx solid #5383EC;
                }
            }
            .message{
                font-size:30rpx;
            }
        }
    }
    
</style>