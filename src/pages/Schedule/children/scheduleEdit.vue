<template>
    <view class="edit_schedule">
        <view class="title">
            <text>日程信息</text>
        </view>
        <view class="schedule_row">
            <text>1.日程类型</text>
                <input v-model="scheduleType" disabled/>
        </view>
        <view class="schedule_row">
            <text>2.开始时间</text>
            <view class="time_row" @click.stop="openCalendar('start')">
                <input v-model="startTime" disabled/>
                <view class="select_time">选择</view>
            </view>
        </view>
        <view class="schedule_row">
            <text>3.结束时间</text>
            <view class="time_row" @click.stop="openCalendar('end')">
                <input v-model="endTime" disabled/>
                <view class="select_time">选择</view>
            </view>
        </view>
        <view class="schedule_row">
            <text>4.日程信息</text>
            <input v-model="content" placeholder="请输入日程信息" class="input"/>
        </view>
        <view class="schedule_row">
            <text>5.日程地点</text>
            <input v-model="location" placeholder="请输入日程地点" class="input"/>
        </view>
        <view class="button_row">
            <button @tap="submitUpdate">提交</button>
        </view>
        <date-picker
        ref='calendarPicker' 
        :level='level'
        :isLunar='isLunar' 
        :currentTime="currentTime"
        :isShowChinaTime="isShowChinaTime"
        @changecalendar='changeCalendar'
        @confirm='confirmCalendar'
        ></date-picker>
    </view>
</template>

<script>
import datePicker from '../../../components/datePicker/bory-datePicker/index';
import {getDateTime,chinaHours} from '../../../components/datePicker/utils/util.js'
export default {
    components: {
        datePicker
    },
    data () {
        return {
            chinaHours,
            chinaTime: '',
            isLunar: false,
            isShowChinaTime:true,
            time: '请选择时间',
            currentTime:'',
            level: '6',
            startTime:'',
            endTime:'',
            content: '',
            location: '',
            scheduleType: '个人日程',
            scheduleId:0,
            timeType:'',
        }
    },
    onLoad(option) {
        const item = JSON.parse(decodeURIComponent(option.item));
        this.startTime = item.startTime;
        this.endTime = item.endTime;
        this.location = item.location;
        this.content = item.content;
        this.scheduleId = item.scheduleId;
        var date = new Date().toLocaleString();
        const timeArray = date.split(' ');
        const timebe = timeArray[0].replace(/\//g,'-');
        this.currentTime = `${timebe} 00:00:00`;
        if(this.isShowChinaTime){
            if(this.chinaTime.length){
                const startTime = this.chinaHours.find(item=>item.name == this.chinaTime);
                const timeArray = this.currentTime.split(' ');
                this.currentTime = `${timeArray[0]} ${startTime}`;
                console.log(this.currentTime);
            }
        }
        // console.log(this.currentTime);
    },
    methods: {
        openCalendar(type){
            this.$refs['calendarPicker'].show();
            if (type==='start') {
                this.currentTime=this.startTime;
                this.timeType = 'start';
            } else {
                this.timeType = 'end';
                this.currentTime = this.endTime;
            }
        },
        changeCalendar(value){
            this.isLunar = value;
        },
        //确定选择日期
        confirmCalendar(value){
            // console.log('确认后数据：',value)
            const {date,time,chinaTime,gzYear,IMonthCn,IDayCn,lYear} = value;
            const dateTime = `${date} ${time}`;
            this.currentTime = dateTime;
            if (this.timeType==='start') {
                this.startTime = dateTime;
            } else {
                this.endTime = dateTime;
            }
            if(this.isLunar){
                if(this.isShowChinaTime){
                    this.time = `${lYear}年${IMonthCn}${IDayCn} ${chinaTime}`;
                }else{
                    this.time = `${lYear}年${IMonthCn}${IDayCn} ${time}`;
                }
                
            }else{
                this.time = dateTime;
            }
        },
        async updateSelfSchedule(data){
            const res = await this.$request({
                url:'/SelfSchedule',
                method:'put',
                // throttle:true,
                data
            })
            return res.data;
        },
        submitUpdate() {
            this.updateSelfSchedule({
                content:this.content,
                startTime: this.startTime,
                endTime: this.endTime,
                location: this.location,
                scheduleId: this.scheduleId,
            })
            .then(res=>{
                if (res.status === 200) {
                    uni.navigateTo({
                        url:'/pages/Schedule/Schedule'
                    })
                }
            })
            .catch(err=>{
                wx.showToast({
                    title:'修改失败',
                    icon:'none',
                    duration: 2500
                });
            })
        }
    },
}
</script>

<style lang="scss" scoped>
.edit_schedule {
    display: flex;
    flex-direction: column;
    padding: 20rpx;
    .title {
        font-size: 40rpx;
        margin-bottom: 30rpx;
        text::before {
            content: '|';
            color: #5383EC;
            font-size: 50rpx;
            font-weight: bolder;
            width: 50rpx;
        }
    }
    .schedule_row {
        display: flex;
        flex-direction: column;;
        font-size: 32rpx;
        padding: 20rpx;
        border-bottom: 1px solid #EBEEF5;
        .input {
            font-size: 35rpx;
        }
        .time_row {
            display: flex;
            justify-content: space-between;
            .select_time {
                color: #5383EC;
            }
        }
    }
    .button_row {
        margin-top: 100rpx;
        button {
            font-size: 32rpx;
            color: #fff;
            background-color: #578BFA;
        }
    }
}
</style>