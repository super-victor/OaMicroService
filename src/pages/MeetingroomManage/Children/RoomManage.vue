<template>
    <view class="RoomManage">
        

        <!-- <view class="content">
		<view class="current_time" @click.stop="openCalendar">{{time}}</view>
		<calendar-picker 
						ref='calendarPicker' 
						:level='level'
						:isLunar='isLunar' 
						:currentTime="currentTime"
						:isShowChinaTime="isShowChinaTime"
						@changecalendar='changeCalendar'
						@confirm='confirmCalendar'
						></calendar-picker> 
 	</view> -->

        <!-- <view class="date">
            <uni-calendar 
            :insert="false"
            :lunar="false" 
            @change="change"
            ref="calendar"
            ></uni-calendar>      
        </view> -->
        <uni-grid class="uniGrid" :column="3" :show-border="true"  :square="true">
            <uni-grid-item v-for="i in roomList" :key="i">
                <text class="text" @click="handleClick(i)">{{i.name}}</text>
            </uni-grid-item>
        </uni-grid>
        <uni-card title="今日会议"  >
            <uni-list class="list">
                <uni-list-item  :title="i.name" v-for="i in meetinglist" :key="i" 
                :note="i.meetingRoomName" :rightText="'开会时间：'+i.startTime.substr(11)"></uni-list-item>
            </uni-list>
        </uni-card>
         <!-- <view>
            <uni-fab
                :pattern="pattern"
                :content="content"
                horizontal="left"
                vertical="bottom"
                direction="vertical"
                @trigger="trigger"
            ></uni-fab>
        </view> -->
        <ourLoading :active='isActive' text="loading..." />
    </view> 
    
</template>

<script>
// import uniCalendar from '@dcloudio/uni-ui/lib/uni-calendar/uni-calendar'
import uniGrid from "@dcloudio/uni-ui/lib/uni-grid/uni-grid"
import uniGridItem from "@dcloudio/uni-ui/lib/uni-grid-item/uni-grid-item"
	// import CalendarPicker from '../../../components/datePicker/bory-datePicker';
	// import {getDateTime,chinaHours} from '../../../components/datePicker/utils/util.js'
import uniFab from "@dcloudio/uni-ui/lib/uni-fab/uni-fab"
import uniIcons from "@dcloudio/uni-ui/lib/uni-icons/uni-icons"
import uniCard from "@dcloudio/uni-ui/lib/uni-card/uni-card"
import uniList from "@dcloudio/uni-ui/lib/uni-list/uni-list"
import uniListItem from "@dcloudio/uni-ui/lib/uni-list-item/uni-list-item"
import ourLoading from '@/components/our-loading/our-loading.vue'

export default {
    components:{
   //         CalendarPicker,
            uniCard,
            uniGrid,
            uniGridItem,
            // uniCalendar,
            uniFab,
            uniIcons,
            uniList,
            uniListItem,
            ourLoading,
		},
    data(){
        return{
         //   	chinaHours,
				// chinaTime: '亥时',
				// isLunar: false,
				// isShowChinaTime:true,
				// time: '请选择时间',
				// currentTime:'',
                // level: '6'
                pattern:{
                    buttonColor:'#8791eb',
                    selectedColor:'#8791eb',
                    color:'#3c3e49'
                },
                content:[{
                    text:'预约会议',
                },{
                    text:'234',
                }],
                roomList:[],
                meetinglist:[],
                isActive:true,
                 formData:{
                name:'',
                age:''
            },
            hobby: [{
                text: '足球',
                value: 0
            }, {
                text: '篮球',
                value: 1
            }, {
                text: '游泳',
                value: 2
            }]
        }
    },
    onLoad() {
			// this.currentTime = '2020-05-25 15:40:10';
			// if(this.isShowChinaTime){
			// 	if(this.chinaTime.length){
			// 		const { startTime } = chinaHours.find(item=>item.name == this.chinaTime);
			// 		const timeArray = this.currentTime.split(' ');
			// 		this.currentTime = `${timeArray[0]} ${startTime}`;
			// 	}
            // }
       
			
		},
    created(){
        var date = new Date()
             this.getAllMeetingRoom().then(res=>{
                this.roomList = res.object;
                 this.getAllMeetings().then(res=>{
                console.log(res.object)
                this.isActive = false
                this.meetinglist = res.object.filter(item=>{
                    return item.startTime.substr(0,10) == date.toISOString().slice(0, 10) && item.appoinmentStatus!=0?'item':''
                })
                console.log(this.meetinglist)
            })
            })
          
			
    },
    methods:{
        	// //打开日期选择器
			// openCalendar(){
			// 	this.$refs['calendarPicker'].show();
			// },
			// //切换阴阳历
			// changeCalendar(value){
			// 	this.isLunar = value;
			// },
			// //确定选择日期
			// confirmCalendar(value){
			// 	console.log('确认后数据：',value)
			// 	const {date,time,chinaTime,gzYear,IMonthCn,IDayCn,lYear} = value;
			// 	const dateTime = `${date} ${time}`;
			// 	this.currentTime = dateTime;
			// 	if(this.isLunar){
			// 		if(this.isShowChinaTime){
			// 			this.time = `${lYear}年${IMonthCn}${IDayCn} ${chinaTime}`;
			// 		}else{
			// 			this.time = `${lYear}年${IMonthCn}${IDayCn} ${time}`;
			// 		}
					
			// 	}else{
			// 		this.time = dateTime;
			// 	}
			// },
          open(){
            this.$refs.calendar.open();
        },
        trigger(res){
            console.log('trigger',res)
        },
        //获取会议室
        async getAllMeetingRoom(){
            const res = await this.$request({
                url:'/getAllMeetingRoom',
                method:'get'
            })
        return res.data;
        },
        //获取会议信息
        async getAllMeetings() {
            const res = await this.$request({
            url: '/getAllMeetings',
            method: 'get',
            })
            return res.data;
        },
        submitForm(form) {
            // 手动提交表单
            this.$refs.form.submit().then((res)=>{
                console.log('表单返回得值：', res)
            })
        },
        handleClick(res){
            console.log('res:',res)
             wx.navigateTo({
                url: `/pages/MeetingroomManage/Children/AppointMeeting?res=${res.name}`
            })
        }
    }
}
</script>

<style  scoped lang='scss'>
    .RoomManage{
        .uniGrid{
            .text{
                text-align: center;
                line-height: 250rpx;
               font-size: 30rpx;
            }
        }
    }
   
</style>