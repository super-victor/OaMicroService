<template>
   <view class="AppointMeeting">
       <!-- <uni-list>
            <uni-list-item title="">
            </uni-list-item>
            <uni-list-item  title="列表禁用状态" ></uni-list-item>
        </uni-list>
         <view class="input">
           <view class="word">{{"会议室："}}</view>
           <input type="text" :value="roomname" disabled class="disabled">
        </view>
       <view class="input">
           <view class="word">{{"会议名称："}}</view>
           <input type="text" placeholder="请输入会议名称">
        </view>
         <view class="input">
           <view class="word">{{"参会人数："}}</view>
           <input type="text" placeholder="请输入参会人数">
        </view>
         <view class="uni-list">
            <view class="uni-list-cell">
                <view class="uni-list-cell-left">
                    当前选择
                </view>
                <view class="uni-list-cell-db">
                    <picker mode="time" :value="time" start="09:01" end="21:01" @change="bindTimeChange">
                        <view class="uni-input">{{time}}</view>
                    </picker>
                </view>
            </view>
        </view> -->
        <uni-card >
            <view class="input">
           <view class="word">{{"会议室："}}</view>
           <input type="text" :value="roomname" disabled class="disabled">
        </view>
       <view class="input">
           <view class="word">{{"会议名称："}}</view>
           <input type="text" @input="getName">
        </view>
        <view class="input">
           <view class="word">{{"参会人数："}}</view>
           <input type="text" @input="getNum">
        </view>
        <view class="input">
            <view class="word">{{"开始时间："}}</view>
            <picker mode="time" :value="startTime" start="00:01" end="23:59" @change="bindTimeChange1">
                <view class="uni-input">{{startTime}}</view>
            </picker>
         </view>
         <view class="input">
            <view class="word">{{"结束时间："}}</view>
            <picker mode="time" :value="endTime" start="00:01" end="23:59" @change="bindTimeChange2">
                <view class="uni-input">{{endTime}}</view>
            </picker>
         </view>
        <button class="getForm" @click="getForm" type="primary">提交</button>
        </uni-card>
            <ourLoading :active='isActive' text="loading..." />  
  
   </view>
</template>

<script>
import uniList from '@dcloudio/uni-ui/lib/uni-list/uni-list'
import uniListItem from '@dcloudio/uni-ui/lib/uni-list-item/uni-list-item'
import uniCard from '@dcloudio/uni-ui/lib/uni-card/uni-card'
import ourLoading from '@/components/our-loading/our-loading.vue'
export default {
    components:{
        uniList,
        uniListItem,
        uniCard,
        ourLoading
    },
    data(){
        return{
            roomname:'',
            time:'00:00',
             startTime:'00:00',
             endTime:'00:00',
             userInfo:'',
             roomList:[],
             roomId:'',
             meetingname:'',
             num:0,
             today:'',
             isActive:false
        }
    },
    onLoad(res){
        var date = new Date()
        this.today =date.toISOString().slice(0, 10)
        console.log(res.res)
        this.roomname = res.res;
        this.userInfo = wx.getStorageSync('userInfo');
        console.log(this.userInfo.employeeId)
         this.getAllMeetingRoom().then(res=>{
                this.roomList = res.object;
               this.roomId = this.roomList.filter(item=>{
                  return  this.roomname == item.name?item.meetingRoomId:''
                })
            })

    },
    methods:{
         bindTimeChange1: function(e) {
            this.startTime= e.target.value
        },
         bindTimeChange2: function(e) {
            this.endTime= e.target.value
        },
        async addOrderMeeting(info  ){
            const res = await this.$request({
                url:'/addOrderMeeting',
                method:'post',
                data:info
            })
            },
             async getAllMeetingRoom(){
            const res = await this.$request({
                url:'/getAllMeetingRoom',
                method:'get'
            })
        return res.data;
        },
        getName(event){
            this.meetingname = event.target.value;
        },
        getNum(event){
            this.num  = event.target.value;
        },
        getForm(){
            this.isActive = true
            console.log('roomId:',typeof(this.userInfo.employeeId))
            this.addOrderMeeting({
                employeeid:this.userInfo.employeeId,
                meetingroomid:this.roomId[0].meetingRoomId,
                name:this.meetingname,
                peoplenum:this.num,
                startTime:this.today +' '+this.startTime+':00',
                endtime:this.today+' '+this.endTime+':00',
                remark:''
            }).then(res=>{
                console.log("预约成功")
                console.log(res)
                wx.showToast({
                    title:'预约成功!',
                    icon:'none',
                    duration: 500
                });
                this.isActive = false
            }).catch(err=>{
                console.log("err:",err)
            })
        }
    }
}
</script>

<style scoped lang='scss'>
    .input{
         display: flex;
        flex-direction: row;;
        font-size: 32rpx;
        padding: 20rpx;
        border-bottom: 1px solid #EBEEF5;
        .input {
            font-size: 35rpx;
        }
        .word{
            line-height: 80rpx;
            width:250rpx;
            height:80rpx;
        }
        
        .disabled{
            border:none
        }
        input{
            line-height: 80rpx;
            height:80rpx;
            width:500rpx;
            border: 1rpx solid #E2E7EB;
            font-size: 35rpx;
            border-radius:20rpx;
            padding-left:15rpx;
        }
        picker{
            line-height: 80rpx;
            height:80rpx;
            width:500rpx;
            font-size: 35rpx;
            border-bottom: 1rpx solid #E2E7EB;
        }
        
    }
</style>